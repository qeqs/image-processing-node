package ipn.services;

import ipn.executors.Executor;
import ipn.model.OperationType;
import ipn.model.transport.GranulationData;
import ipn.operations.base.morph.MedianFilterOperation;
import ipn.utils.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import org.opencv.core.Mat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Vadim Lygin on 9/26/2017.
 */
@Service
@Slf4j
public class PreprocessingService {

    private final List<Executor> executors;
    @Autowired
    private MedianFilterOperation filter;

    @Autowired
    public PreprocessingService(List<Executor> executors) {
        this.executors = executors;
    }


    public GranulationData preprocess(MultipartFile[] files, Integer step, OperationType type) {

        GranulationData data = new GranulationData();
        List<List<Double>> dataList = new ArrayList<>();

        for (MultipartFile image : Arrays.asList(files)) {
            List<Double> dataPart = preprocess(image, step, type);
            dataList.add(dataPart);
        }
        return data;
    }

    public List<Double> preprocess(MultipartFile image, Integer step, OperationType type) {
        List<Double> dataList = new ArrayList<>();
        File file = new File("temp.png");
        if (file.exists()) {
            file.delete();
        }
        try (FileOutputStream fs = new FileOutputStream(file)) {
            fs.write(image.getBytes());
        } catch (IOException e) {
            log.error("Error with saving picture ", e);
        }

        executors.stream()
                .filter(executor -> executor.isApplicableFor(type))
                .collect(Collectors.toList())
                .forEach(executor -> {
                    Mat img = filter.execute(FileUtils.readFile(file.getAbsolutePath()),null);

                    List<List<Double>> list = executor.process(img,
                            Collections.singletonMap("steps", step));
                    list.forEach(item -> dataList.addAll(item));
                });
        return dataList;
    }

}

package ipn.controllers;

import io.swagger.annotations.Api;
import ipn.model.OperationType;
import ipn.model.transport.GranulationData;
import ipn.services.PreprocessingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

@Api
@RequestMapping("/operations/granulation")
@RestController
@Slf4j
public class Controller {

    PreprocessingService preprocessingService;

    @Autowired
    public Controller(PreprocessingService preprocessingService) {
        this.preprocessingService = preprocessingService;
    }

    @RequestMapping(value = "/preprocessing/granulation", method = RequestMethod.POST)
    public ResponseEntity<GranulationData> process(@RequestParam("uploadingFiles") MultipartFile[] uploadingFile,
                                                   @RequestParam("steps") Integer steps) throws IOException {
        return new ResponseEntity<>(
                preprocessingService.preprocess(uploadingFile, steps, OperationType.GRANULATION),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/preprocessing/granulation/single", method = RequestMethod.POST)
    public ResponseEntity<GranulationData> processSingle(@RequestParam("uploadingFile") MultipartFile uploadingFile,
                                                         @RequestParam("steps") Integer steps) throws IOException {
        GranulationData data = new GranulationData();
        data.setData(Collections.singletonList(
                preprocessingService.preprocess(uploadingFile, steps, OperationType.GRANULATION)));
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

}

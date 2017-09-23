package ipn.operations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.springframework.stereotype.Component;

@Component
public class StatisticOperations {

    private HashMap<Integer, Double> chart = new HashMap<>();
    private int steps = 21;
    private int type = Imgproc.MORPH_RECT;
    private MorphOperations morphOperations = new MorphOperations();

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double scalar() {
        double mat = 0.0;
        for (Map.Entry<Integer, Double> entry : chart.entrySet()) {
            Double value = entry.getValue();
            mat += value;
        }
        return mat;
    }

    public void mathWaitOpening(Mat image) {
        chart.clear();

        Mat res = new Mat();
        Mat tempPrev = new Mat();
        image.copyTo(tempPrev);
        Mat tempNext;
        for (int i = 1; i < 2 * steps; i += 2) {
            tempNext = morphOperations.opening(tempPrev, morphOperations.getStructElement(i, i, type));

            Core.subtract(tempPrev, tempNext, res);
            chart.put(i, Core.norm(res) / (res.height() * res.width()));

            tempNext.copyTo(tempPrev);
        }
    }

    public void deviationOpeninig(Mat image) {

        mathWaitOpening(image);
        double mat = scalar();
        chart.clear();
        
        Mat res = new Mat();
        Mat tempPrev = new Mat();
        image.copyTo(tempPrev);
        Mat tempNext;
        for (int i = 1; i < 2 * steps; i += 2) {
            tempNext = morphOperations.opening(tempPrev, morphOperations.getStructElement(i, i, type));

            Core.subtract(tempPrev, tempNext, res);

            chart.put(i, Math.sqrt(
                    Math.pow(Core.norm(res) - mat, 2.0) / (res.height() * res.width())
            ));

            tempNext.copyTo(tempPrev);
        }

    }

    public void mathWaitGrad(Mat image) {
        chart.clear();

        Mat res = new Mat();
        Mat tempPrev = new Mat();
        image.copyTo(tempPrev);
        Mat tempNext;
        for (int i = 1; i < 2 * steps; i += 2) {
            tempNext = morphOperations.grad(tempPrev, morphOperations.getStructElement(i, i, type));

            Core.subtract(tempPrev, tempNext, res);
            if(i == 1) continue;//todo: ask about it
            chart.put(i, Core.norm(res) / (res.height() * res.width()));

            tempNext.copyTo(tempPrev);
        }
    }

    public void deviationGrad(Mat image) {

        mathWaitGrad(image);
        double mat = scalar();        
        chart.clear();
        
        Mat res = new Mat();
        Mat tempPrev = new Mat();
        image.copyTo(tempPrev);
        Mat tempNext;
        for (int i = 1; i < 2 * steps; i += 2) {
            tempNext = morphOperations.grad(tempPrev, morphOperations.getStructElement(i, i, type));

            Core.subtract(tempPrev, tempNext, res);
            if(i == 1) continue;
            chart.put(i, Math.sqrt(
                    Math.pow(Core.norm(res) - mat, 2.0) / (res.height() * res.width())
            ));

            tempNext.copyTo(tempPrev);
        }

    }

    public List<Double> objectSize(Mat image) {
        Mat tmp = morphOperations.getBinaryMat(image);
        Mat img = morphOperations.getBinaryMat(image);
        ArrayList<Double> sizes = new ArrayList<>();
        int i = 1;
        int prev = 0;
        do {
            Core.subtract(img, morphOperations.opening(tmp, morphOperations.getStructElement(i, i, Imgproc.MORPH_RECT)), tmp);
            sizes.add(Core.norm(tmp));
        } while (i++ < image.width() || prev != 0);
        return sizes;
    }

    public HashMap<Integer, Double> getChart() {
        return chart;
    }

}

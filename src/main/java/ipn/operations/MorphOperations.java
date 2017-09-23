package ipn.operations;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.springframework.stereotype.Component;

@Component
public class MorphOperations {

    private Mat primitive;

    public MorphOperations() {
        primitive = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(2, 2));
    }

    public Mat grad(Mat img, Mat prim) {
        Mat dilate = new Mat(), erode = new Mat();
        Imgproc.erode(img, erode, prim);
        Imgproc.dilate(img, dilate, prim);
        Core.subtract(dilate, erode, dilate);
        return dilate;
    }

    public Mat opening(Mat img, Mat primitive) {
        Mat dilated = new Mat(), eroded = new Mat();

        Imgproc.erode(img, eroded, primitive);
        Imgproc.dilate(eroded, dilated, primitive);

        return dilated;

    }

    public Mat closing(Mat img, Mat primitive) {
        Mat dilated = new Mat(), eroded = new Mat();

        Imgproc.dilate(img, dilated, primitive);
        Imgproc.erode(dilated, eroded, primitive);

        return eroded;

    }


    public Mat getBinaryMat(Mat img) {
        Mat dst = new Mat();
        Imgproc.threshold(img, dst, 50, 250, 0);
        return dst;
    }

    public Mat getStructElement(int w, int h, int type) {
        return Imgproc.getStructuringElement(type, new Size(w, h));
    }

    public void setPrimitive(Mat elem) {
        primitive = elem;
    }

    public Mat getPrimitive() {
        return primitive;
    }

}

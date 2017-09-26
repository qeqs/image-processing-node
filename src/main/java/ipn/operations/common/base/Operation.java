package ipn.operations.common.base;

import org.opencv.core.Mat;

/**
 * Created by Vadim Lygin on 9/26/2017.
 */
public interface Operation<T> {
  T execute(Mat image, Mat prim);
}

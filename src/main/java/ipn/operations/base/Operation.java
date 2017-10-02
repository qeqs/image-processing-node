package ipn.operations.base;

import java.util.Map;
import org.opencv.core.Mat;

/**
 * Created by Vadim Lygin on 9/26/2017.
 */
public interface Operation<T> {
  T execute(Mat image, Map<String, Object> metadata);
}

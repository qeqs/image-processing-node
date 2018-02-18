package ipn.executors;

import ipn.model.OperationType;
import org.opencv.core.Mat;

import java.util.List;
import java.util.Map;

/**
 * Created by Vadim Lygin on 9/28/2017.
 */
public interface Executor<T> {
  List<T> process(Mat picture, Map<String, Object> metadata);
  boolean isApplicableFor(OperationType type);
}

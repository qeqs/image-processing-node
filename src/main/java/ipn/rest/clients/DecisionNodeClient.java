package ipn.rest.clients;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.springframework.stereotype.Component;

/**
 * Created by Vadim Lygin on 10/3/2017.
 */
@Component
public interface DecisionNodeClient {

  INDArray predict(INDArray input);

  void train(DataSet data);

}

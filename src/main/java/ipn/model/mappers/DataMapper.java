package ipn.model.mappers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Created by Vadim Lygin on 9/25/2017.
 */

@Mapper
public abstract class DataMapper {

  public static DataMapper MAPPER = Mappers.getMapper(DataMapper.class);

  public List<Double> mapData(HashMap<Integer, Double> matWaitOpeningChart,
      HashMap<Integer, Double> deviationOpeningChart,
      HashMap<Integer, Double> matWaitGradChart,
      HashMap<Integer, Double> deviationGradChart,
      double mathWaitOpening,
      double deviationOpening,
      double mathWaitGrad,
      double deviationGrad) {
    ArrayList<Double> data = new ArrayList<>();
    data.addAll(matWaitGradChart.values());
    data.addAll(matWaitOpeningChart.values());
    data.addAll(deviationOpeningChart.values());
    data.addAll(deviationGradChart.values());
    data.add(mathWaitGrad);
    data.add(mathWaitOpening);
    data.add(deviationGrad);
    data.add(deviationOpening);
    return data;
  }

  public abstract HashMap<String, Double> toStringKey(HashMap<Integer, Double> map);

}

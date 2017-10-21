package ipn.model.mappers;

import java.util.HashMap;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Created by Vadim Lygin on 9/25/2017.
 */

@Mapper
public abstract class DataMapper {

  public static DataMapper MAPPER = Mappers.getMapper(DataMapper.class);

  public abstract HashMap<String, Double> toStringKey(HashMap<Integer, Double> map);

}

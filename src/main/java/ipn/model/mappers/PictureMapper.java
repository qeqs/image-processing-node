package ipn.model.mappers;

import ipn.model.Picture;
import ipn.model.PictureTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Created by Vadim Lygin on 9/25/2017.
 */
@Mapper(uses = DataMapper.class)
public interface PictureMapper {

  PictureMapper MAPPER = Mappers.getMapper(PictureMapper.class);


  @Mapping(target = "data",
      expression =
          "java(DataMapper.MAPPER.mapData(picture.getMatWaitOpeningChart(),"
              + "picture.getDeviationOpeningChart(),"
              + "picture.getMatWaitGradChart(),"
              + "picture.getDeviationGradChart(),"
              + "picture.getMathWaitOpening(),"
              + "picture.getDeviationOpening(),"
              + "picture.getMathWaitGrad(),"
              + "picture.getDeviationGrad()))")
  PictureTO pictureToTransport(Picture picture);


}

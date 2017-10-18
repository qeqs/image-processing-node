package ipn.model.mappers;

import ipn.model.Picture;
import ipn.model.PictureEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Created by Vadim Lygin on 10/13/2017.
 */
@Mapper
public interface EntityMapper {

  static EntityMapper MAPPER = Mappers.getMapper(EntityMapper.class);

  @Mapping(target = "imageMat", expression = "java(pictureEntity.getName()!=null? FileUtils.readFile(name):null)")
  Picture toTransport(PictureEntity pictureEntity);

  PictureEntity toEntity(Picture picture);

  List<Picture> toTransport(List<PictureEntity> pictureEntity);

  List<PictureEntity> toEntity(List<Picture> picture);

}

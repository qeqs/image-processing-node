package ipn.model.mappers;

import ipn.model.transport.Picture;
import ipn.model.PictureEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * Created by Vadim Lygin on 10/13/2017.
 */
@Mapper
public interface EntityMapper {

  EntityMapper MAPPER = Mappers.getMapper(EntityMapper.class);

  @Mappings({
      @Mapping(target = "imageMat", expression = "java(pictureEntity.getName()!=null?ipn.utils.FileUtils.readFile(pictureEntity.getName()):null)"),
      @Mapping(target = "processInfo",
          expression = "java(pictureEntity.getOperationType()!=null?new ipn.model.transport.ProcessInfo(pictureEntity.getOperationType(), pictureEntity.getMetadata()):null)")
  })
  Picture toTransport(PictureEntity pictureEntity);

  PictureEntity toEntity(Picture picture);

  List<Picture> toTransport(List<PictureEntity> pictureEntity);

  List<PictureEntity> toEntity(List<Picture> picture);

}

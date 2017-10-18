package ipn.services;

import ipn.model.Picture;
import ipn.model.PictureEntity;
import ipn.model.mappers.EntityMapper;
import ipn.model.repositories.PictureRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Vadim Lygin on 10/2/2017.
 */
@Service
@Transactional
@Slf4j
public class PictureService {

  private final PictureRepository repository;

  @Autowired
  public PictureService(PictureRepository repository) {
    this.repository = repository;
  }

  public Picture getPicture(String id) {
    return EntityMapper.MAPPER.toTransport(repository.findOne(id));
  }

  public Picture save(Picture picture) {
    return EntityMapper.MAPPER.toTransport(repository.save(EntityMapper.MAPPER.toEntity(picture)));
  }

  public List<Picture> getAllPictures() {
    return EntityMapper.MAPPER.toTransport((List<PictureEntity>) repository.findAll());
  }

  public void remove(String id){
    PictureEntity entity = repository.findOne(id);
    try {
      Files.delete(Paths.get(entity.getName()));
    } catch (IOException e) {
      log.error("Error in file deletion ", e.getMessage(), e);
    }
    repository.delete(id);
  }

  public void removeAll(){
    Iterable<PictureEntity> entities = repository.findAll();
    entities.forEach(ent -> {
      try {
        Files.delete(Paths.get(ent.getName()));
      } catch (IOException e) {
        log.error("Error in bulk file deletion ", e.getMessage(), e);
      }
    });
    repository.deleteAll();
  }


}

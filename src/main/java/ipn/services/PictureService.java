package ipn.services;

import ipn.model.Picture;
import ipn.model.PictureEntity;
import ipn.model.mappers.EntityMapper;
import ipn.model.repositories.PictureRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Vadim Lygin on 10/2/2017.
 */
@Service
@Transactional
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
}

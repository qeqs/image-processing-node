package ipn.services;

import ipn.model.Picture;
import ipn.model.repositories.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Vadim Lygin on 10/2/2017.
 */
@Service
public class PictureService {

  private final PictureRepository repository;

  @Autowired
  public PictureService(PictureRepository repository) {
    this.repository = repository;
  }

  public Picture getPicture(String id){
    return repository.findOne(id);
  }

  public Picture save(Picture picture){
    return repository.save(picture);
  }

}

package ipn.services;

import ipn.model.repositories.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Vadim Lygin on 10/2/2017.
 */
@Service
public class PictureService {

  @Autowired
  PictureRepository repository;

}

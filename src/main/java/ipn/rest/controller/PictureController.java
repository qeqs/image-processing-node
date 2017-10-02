package ipn.rest.controller;

import ipn.model.Picture;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Vadim Lygin on 10/2/2017.
 */
@RequestMapping("/api/v1/preprocessing")
@RestController
public class PictureController {

  public ResponseEntity<Picture> getPicture(String id){
    return null;
  }

}

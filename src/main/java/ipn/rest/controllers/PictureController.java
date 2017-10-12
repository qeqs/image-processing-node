package ipn.rest.controllers;

import ipn.model.Picture;
import ipn.model.transport.ProcessInfo;
import ipn.services.PictureHandler;
import ipn.services.PictureService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Vadim Lygin on 10/2/2017.
 */
@RequestMapping("/api/v1/pictures")
@RestController
public class PictureController {

  private final PictureService pictureService;
  private final PictureHandler handler;

  @Autowired
  public PictureController(PictureService pictureService, PictureHandler handler) {
    this.pictureService = pictureService;
    this.handler = handler;
  }


  @GetMapping("/{id}")
  public ResponseEntity<Picture> getPicture(@PathVariable String id) {
    return new ResponseEntity<>(pictureService.getPicture(id), HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<List<Picture>> getPictures() {
    return new ResponseEntity<>(pictureService.getAllPictures(), HttpStatus.OK);
  }


  @PostMapping
  public ResponseEntity loadPictures(@RequestParam("files") List<MultipartFile> image,
      @RequestBody List<ProcessInfo> info) {

    image.forEach(img -> handler.handle(img));

    return ResponseEntity.accepted().build();
  }


}

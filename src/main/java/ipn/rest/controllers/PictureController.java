package ipn.rest.controllers;

import io.swagger.annotations.Api;
import ipn.model.transport.Picture;
import ipn.services.PictureHandler;
import ipn.services.PictureService;
import ipn.utils.FileUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Vadim Lygin on 10/2/2017.
 */
@Api
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

  @GetMapping("/{id}/download")
  public ResponseEntity<byte[]> downloadPicture(@PathVariable String id){
    Picture picture = pictureService.getPicture(id);
    return new ResponseEntity<>(FileUtils.readBytes(picture.getName()), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity loadPictures(@RequestParam("files") List<MultipartFile> image) {
    image.forEach(img -> handler.handle(img));
    return ResponseEntity.accepted().build();
  }

  @PutMapping
  public ResponseEntity<Picture> loadPicture(@RequestParam("file") MultipartFile image) {
    Picture picture = handler.handle(image);
    return new ResponseEntity<Picture>(picture, HttpStatus.ACCEPTED);
  }


  @DeleteMapping("/{id}")
  public ResponseEntity remove(@PathVariable String id){
    pictureService.remove(id);
    return ResponseEntity.accepted().build();
  }

  @DeleteMapping
  public ResponseEntity removeAll(){
    pictureService.removeAll();
    return ResponseEntity.accepted().build();
  }


}

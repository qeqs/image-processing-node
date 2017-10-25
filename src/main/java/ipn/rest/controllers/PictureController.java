package ipn.rest.controllers;

import io.swagger.annotations.Api;
import ipn.model.transport.Picture;
import ipn.services.PictureHandler;
import ipn.services.PictureService;
import ipn.utils.FileUtils;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
@Slf4j
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
    log.info("GET picture info by id {}", id);
    return new ResponseEntity<>(pictureService.getPicture(id), HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<List<Picture>> getPictures() {
    log.info("GET all pictures info");
    return new ResponseEntity<>(pictureService.getAllPictures(), HttpStatus.OK);
  }

  @GetMapping(value = "/{id}/download", produces = MediaType.IMAGE_PNG_VALUE)
  public ResponseEntity<byte[]> downloadPicture(@PathVariable String id){
    log.info("GET download picture by id {}", id);
    Picture picture = pictureService.getPicture(id);
    return new ResponseEntity<>(FileUtils.readBytes(picture.getName()), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Picture> loadPicture(@RequestParam("file") MultipartFile image, @RequestParam("filename") String filename) {
    log.info("POST load picture = {}", filename);
    Picture picture = handler.handlePicture(image, filename);
    return new ResponseEntity<>(picture, HttpStatus.ACCEPTED);
  }


  @DeleteMapping("/{id}")
  public ResponseEntity remove(@PathVariable String id){
    log.info("DELETE picture by id {}", id);
    pictureService.remove(id);
    return ResponseEntity.accepted().build();
  }

  @DeleteMapping
  public ResponseEntity removeAll(@RequestParam(required = false) List<String> ids ){
    log.info("DELETE all pictures {}", ids);
    pictureService.removeAll();//todo delete by ids
    return ResponseEntity.accepted().build();
  }


}

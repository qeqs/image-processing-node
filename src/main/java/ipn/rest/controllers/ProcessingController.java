package ipn.rest.controllers;

import ipn.model.Picture;
import ipn.model.transport.PictureInfo;
import ipn.services.PictureHandler;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Vadim Lygin on 10/3/2017.
 */
@RequestMapping("/api/v1/process")
@RestController
public class ProcessingController {

  private final PictureHandler handler;

  @Autowired
  public ProcessingController(PictureHandler handler) {
    this.handler = handler;
  }

  @PostMapping("/predict")
  public String loadPicture(@RequestParam("file") MultipartFile image,
      @RequestBody PictureInfo info) {
    Picture picture = handler.handle(image, info);
    return picture.getId();
  }

  @PostMapping("/train")
  public String loadPictures(@RequestParam("files") List<MultipartFile> image,
      @RequestBody List<PictureInfo> info) {
    return "ok";
  }


}

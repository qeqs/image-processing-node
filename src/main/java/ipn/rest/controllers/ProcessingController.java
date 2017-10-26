package ipn.rest.controllers;

import ipn.model.transport.Picture;
import ipn.model.transport.ProcessInfo;
import ipn.services.PictureHandler;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Vadim Lygin on 10/3/2017.
 */
@RequestMapping("/api/v1/process")
@RestController
@Slf4j
public class ProcessingController {

  private final PictureHandler handler;

  @Autowired
  public ProcessingController(PictureHandler handler) {
    this.handler = handler;
  }

  @PostMapping("/predict")
  public String predict(@RequestBody ProcessInfo info) {
    return "ok";
  }

  @PostMapping("/train")
  public String train(@RequestBody Map<String, List<ProcessInfo>> info) {
    return "ok";
  }

  @PostMapping("/{picture_id}")
  public Picture process(@PathVariable String picture_id, @RequestBody ProcessInfo info) {
    log.info("POST Started processing {} with process info {}",picture_id, info);
    return handler.handleProcess(info,picture_id);
  }


}

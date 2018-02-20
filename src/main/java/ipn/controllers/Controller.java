package ipn.controllers;

import static org.springframework.http.MediaType.APPLICATION_OCTET_STREAM_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;
import static org.springframework.http.MediaType.TEXT_PLAIN;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

import io.swagger.annotations.Api;
import ipn.model.OperationType;
import ipn.model.transport.GranulationData;
import ipn.services.PreprocessingService;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

@Api
@RequestMapping("/operations")
@RestController
@Slf4j
public class Controller {

  PreprocessingService preprocessingService;

  @Autowired
  public Controller(PreprocessingService preprocessingService) {
    this.preprocessingService = preprocessingService;
  }

  @RequestMapping(value = "/preprocessing/granulation", method = RequestMethod.POST)
  public ResponseEntity<GranulationData> process(
      @RequestParam("uploadingFiles") MultipartFile[] uploadingFile,
      @RequestParam("steps") String steps) throws IOException {

    return new ResponseEntity<>(
        preprocessingService
            .preprocess(uploadingFile, Integer.valueOf(steps), OperationType.GRANULATION),
        HttpStatus.OK);
  }

  @RequestMapping(value = "/preprocessing/granulation/single", method = RequestMethod.POST)
  public ResponseEntity<GranulationData> processSingle(
      @RequestPart(value = "uploadingFile", required = false) MultipartFile uploadingFile,
      @RequestPart(value = "steps", required = false) String steps,
      HttpServletResponse response) throws IOException {
    log.info("Received response: {}" ,response);
    GranulationData data = new GranulationData();
    data.setData(Collections.singletonList(
        preprocessingService.preprocess(uploadingFile, Integer.valueOf(steps), OperationType.GRANULATION)));
    return new ResponseEntity<>(data, HttpStatus.OK);
  }

}

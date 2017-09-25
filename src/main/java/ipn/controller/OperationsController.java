package ipn.controller;

import ipn.model.Picture;
import ipn.model.PictureTO;
import ipn.model.mappers.PictureMapper;
import java.util.HashMap;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/")
@RestController
public class OperationsController {

  @GetMapping
  public String index() {
    return "image-processing-node";
  }

  @RequestMapping(value = "/payload")
  @GetMapping
  public Picture picturePayload() {
    return Picture.builder()
        .deviationGrad(2.0)
        .deviationGradChart(new HashMap<Integer, Double>() {{
          put(1, 2.0);
        }})
        .deviationOpening(2.0)
        .deviationOpeningChart(new HashMap<Integer, Double>() {{
          put(1, 2.0);
        }})
        .mathWaitGrad(2.0)
        .matWaitGradChart(new HashMap<Integer, Double>() {{
          put(1, 2.0);
        }})
        .mathWaitOpening(2.0)
        .matWaitOpeningChart(new HashMap<Integer, Double>() {{
          put(1, 2.0);
        }})
        .name("test")
        .imageMat(null)
        .build();
  }

  @RequestMapping(value = "/payloadTransport", produces = MediaType.APPLICATION_JSON_VALUE)
  public PictureTO pictureTO() {
    return PictureMapper.MAPPER.pictureToTransport(picturePayload());
  }
}

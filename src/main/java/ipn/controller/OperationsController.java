package ipn.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/")
@RestController
public class OperationsController {

  @GetMapping
  public String index() {
    return "image-processing-node";
  }
}

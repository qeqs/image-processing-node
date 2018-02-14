package ipn.rest.controllers;

import java.io.IOException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Vadim Lygin on 2/14/2018.
 */
@RestController
public class Controller {
  public static final String uploadingdir = System.getProperty("user.dir") + "/uploadingdir/";

  @RequestMapping(value = "/", method = RequestMethod.POST)
  public String train(@RequestParam("uploadingFiles") MultipartFile[] uploadingFiles) throws IOException {



    return "redirect:/";
  }
}

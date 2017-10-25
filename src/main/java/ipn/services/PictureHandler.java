package ipn.services;

import ipn.model.transport.Picture;
import ipn.model.transport.ProcessInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Vadim Lygin on 10/2/2017.
 */
@Service
public class PictureHandler {

  private final PreprocessingService preprocessingService;
  private final FeatureBuilder featureBuilder;
  private final PictureService service;

  @Autowired
  public PictureHandler(PreprocessingService preprocessingService,
      FeatureBuilder featureBuilder, PictureService service) {
    this.preprocessingService = preprocessingService;
    this.featureBuilder = featureBuilder;
    this.service = service;
  }


  public Picture handlePicture(MultipartFile file, String filename){
    Picture picture = preprocessingService.preprocess(file, filename);
    return service.save(picture);
  }

  public Picture handleProcess(ProcessInfo processInfo, String pictureId){
    return null;
  }

}

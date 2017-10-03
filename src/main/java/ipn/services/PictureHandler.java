package ipn.services;

import ipn.model.Picture;
import ipn.model.transport.PictureInfo;
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


  public Picture handle(MultipartFile file, PictureInfo info){
    Picture picture = preprocessingService.preprocess(file);
    picture.setMetadata(info.getMetadata());
    picture.setName(info.getName());
    picture.setOperationType(info.getType());
    featureBuilder.buildFeature(picture);
    //send csv to decitionNode
    return service.save(picture);
  }

}

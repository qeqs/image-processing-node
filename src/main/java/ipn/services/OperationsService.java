package ipn.services;

import ipn.model.Picture;
import ipn.operations.FileOperations;
import ipn.operations.StatisticOperations;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperationsService {

  private final FileOperations fileOperations;
  private final StatisticOperations statisticOperations;
  @Getter
  @Setter
  private Integer progress = 0;

  @Autowired
  public OperationsService(FileOperations fileOperations,
      StatisticOperations statisticOperations) {
    this.fileOperations = fileOperations;
    this.statisticOperations = statisticOperations;
  }

  public List<Picture> readPictures(List<File> images) throws IOException {
    ArrayList<Picture> pictures = new ArrayList<>();
    for (File image : images) {

      fileOperations.readImage(image.getAbsolutePath());

      Picture picture = new Picture();

      picture.setName(image.getName());
      picture.setImageMat(fileOperations.getPicture());
      picture.setImage(fileOperations.convertImage(picture.getImageMat()));

      pictures.add(picture);
      progress++;
    }
    progress = 0;
    return pictures;
  }

  public void calcStatistics(List<Picture> pictures) {
    for (Picture picture : pictures) {
      calcStatistics(picture);
      progress++;
    }
    progress = 0;
  }

  public void calcStatistics(Picture picture) {
    statisticOperations.mathWaitOpening(picture.getImageMat());
    picture.setMatWaitOpeningChart(statisticOperations.getChart());
    picture.setMathWaitOpening(statisticOperations.scalar());

    statisticOperations.deviationOpeninig(picture.getImageMat());
    picture.setDeviationOpeningChart(statisticOperations.getChart());
    picture.setDeviationOpening(statisticOperations.scalar());

    statisticOperations.mathWaitGrad(picture.getImageMat());
    picture.setMatWaitGradChart(statisticOperations.getChart());
    picture.setMathWaitGrad(statisticOperations.scalar());

    statisticOperations.deviationGrad(picture.getImageMat());
    picture.setDeviationGradChart(statisticOperations.getChart());
    picture.setDeviationGrad(statisticOperations.scalar());
  }

  public void setSteps(int steps) {
    statisticOperations.setSteps(steps);
  }

  public void setStructElementType(int type) {
    statisticOperations.setType(type);
  }

  public int getSteps() {
    return statisticOperations.getSteps();
  }

  public int getStructElementType() {
    return statisticOperations.getType();
  }

}

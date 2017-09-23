package ipn.model;

import java.util.HashMap;
import javafx.scene.image.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.opencv.core.Mat;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Picture {

  private HashMap<Integer, Double> matWaitOpeningChart = new HashMap<>();
  private HashMap<Integer, Double> deviationOpeningChart = new HashMap<>();
  private HashMap<Integer, Double> matWaitGradChart = new HashMap<>();
  private HashMap<Integer, Double> deviationGradChart = new HashMap<>();

  private Mat imageMat;
  private Image image;
  private String name;

  private double mathWaitOpening;
  private double deviationOpening;
  private double mathWaitGrad;
  private double deviationGrad;

  @Override
  public String toString() {
    return name;
  }

  public Picture setMatWaitOpeningChart(HashMap<Integer, Double> matWaitOpeningChart) {
    this.matWaitOpeningChart = (HashMap<Integer, Double>) matWaitOpeningChart.clone();
    return Picture.this;
  }

  public Picture setDeviationOpeningChart(HashMap<Integer, Double> deviationOpeningChart) {
    this.deviationOpeningChart = (HashMap<Integer, Double>) deviationOpeningChart.clone();
    return Picture.this;
  }

  public Picture setMatWaitGradChart(HashMap<Integer, Double> matWaitGradChart) {
    this.matWaitGradChart = (HashMap<Integer, Double>) matWaitGradChart.clone();
    return Picture.this;
  }

  public Picture setDeviationGradChart(HashMap<Integer, Double> deviationGradChart) {
    this.deviationGradChart = (HashMap<Integer, Double>) deviationGradChart.clone();
    return Picture.this;
  }
}

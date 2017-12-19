package ipn.model.transport;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ipn.utils.FileUtils;
import java.util.HashMap;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.opencv.core.Mat;

@Getter
@Setter
@NoArgsConstructor
@Data
@ToString(of = {"name"})
public class Picture {

  private String id;
  private HashMap<String, Object> data;
  private HashMap<String, Double> scalar;
  private ProcessInfo processInfo;

  @JsonIgnore
  private Mat imageMat;
  private String name;

  {
    data = new HashMap<>();
    scalar = new HashMap<>();
    loadImage();

  }

  public void loadImage() {
    imageMat = name != null ? FileUtils.readFile(name) : null;
  }

}

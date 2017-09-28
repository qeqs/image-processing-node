package ipn.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashMap;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.opencv.core.Mat;

@Getter
@Setter
@NoArgsConstructor
@Builder
@Data
@ToString(of = {"name"})
public class Picture {
  private HashMap<String, HashMap<Integer, Double>> data;
  private HashMap<String, Double> scalar;
  private HashMap<String, Object> metadata;

  @JsonIgnore
  private Mat imageMat;
  private String name;
  private OperationType operationType;

  {
    data = new HashMap<>();
    scalar = new HashMap<>();
    metadata = new HashMap<>();
  }

}

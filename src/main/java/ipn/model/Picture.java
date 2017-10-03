package ipn.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ipn.model.OperationType;
import java.util.HashMap;
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
@Data
@ToString(of = {"name"})
public class Picture {

  private String id;
  private HashMap<String, Object> data;
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

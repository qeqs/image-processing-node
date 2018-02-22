package ipn.model.transport;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RootTO {
  @JsonProperty
  FileTO fileTO;
}

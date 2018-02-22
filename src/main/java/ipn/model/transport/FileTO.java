package ipn.model.transport;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class FileTO {
  @JsonProperty
  private List<MultipartFile> files;
  @JsonProperty
  private Integer steps;
}

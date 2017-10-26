package ipn.model.transport;

import ipn.model.OperationType;
import java.util.HashMap;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Vadim Lygin on 10/3/2017.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProcessInfo {

  private OperationType type;
  private HashMap<String, Object> metadata;

}

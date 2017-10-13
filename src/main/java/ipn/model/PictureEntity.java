package ipn.model;

import java.util.HashMap;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@Table(name = "pictures")
public class PictureEntity {

  @Id
  private String id;
  private HashMap<String, Object> data;
  private HashMap<String, Double> scalar;
  private HashMap<String, Object> metadata;

  private String name;
  @Enumerated(EnumType.STRING)
  private OperationType operationType;

}

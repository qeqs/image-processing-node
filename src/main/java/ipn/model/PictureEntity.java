package ipn.model;

import java.util.HashMap;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@Getter
@Setter
@Table(name = "pictures")
public class PictureEntity {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;
  private HashMap<String, Object> data;
  private HashMap<String, Double> scalar;
  private HashMap<String, Object> metadata;

  private String name;
  @Enumerated(EnumType.STRING)
  private OperationType operationType;

  {
    id = UUID.randomUUID().toString();
  }
}

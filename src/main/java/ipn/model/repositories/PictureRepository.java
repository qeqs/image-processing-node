package ipn.model.repositories;

import ipn.model.OperationType;
import ipn.model.Picture;
import ipn.model.PictureEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureRepository extends CrudRepository<PictureEntity, String>,
    JpaSpecificationExecutor<PictureEntity> {

  List<PictureEntity> getPicturesByOperationType(OperationType operationType);
}

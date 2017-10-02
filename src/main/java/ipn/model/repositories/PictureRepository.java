package ipn.model.repositories;

import ipn.model.Picture;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Vadim Lygin on 10/2/2017.
 */

@Repository
public interface PictureRepository extends CrudRepository<Picture, String>,
    JpaSpecificationExecutor<Picture> {

}

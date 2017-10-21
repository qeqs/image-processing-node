package ipn.services;

import ipn.executors.Executor;
import ipn.model.transport.Picture;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Vadim Lygin on 9/26/2017.
 */
@Service
public class FeatureBuilder {

  private final List<Executor> executors;

  @Autowired
  public FeatureBuilder(List<Executor> executors) {
    this.executors = executors;
  }

  public Picture buildFeature(Picture picture) {

    executors.stream()
        .filter(executor -> executor.isApplicableFor(picture.getProcessInfo().getType()))
        .collect(Collectors.toList())
        .forEach(executor -> executor.process(picture));
    return picture;
  }

}

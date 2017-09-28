package ipn;


import ipn.operations.common.morph.OpeninigOperation;
import ipn.operations.common.statistic.OpeningDeviationOperation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    nu.pattern.OpenCV.loadShared();
    SpringApplication.run(Application.class, args);
  }

}

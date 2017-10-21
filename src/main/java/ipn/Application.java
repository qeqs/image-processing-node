package ipn;


import java.util.Collections;
import java.util.Date;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableAsync
public class Application {

  @Bean
  public Docket swaggerSettings() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("ipn.rest.controllers"))
        .build()
        .directModelSubstitute(Date.class, Long.class)
        .apiInfo(apiInfo());
  }

  private ApiInfo apiInfo() {
    return new ApiInfo(
        "Image processing REST API",
        "",
        "1.0",
        "",
        new Contact("Vadim Lygin", "", "vadimlygin@yandex.ru"),
        "",
        "",
        Collections.emptyList()
    );
  }

  public static void main(String[] args) {
    nu.pattern.OpenCV.loadShared();
    SpringApplication.run(Application.class, args);
  }

}

package tobyspring.helloboot;

import tobyspring.config.MySpringBootAnnotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;

@MySpringBootAnnotation
public class HelloBootApplication {

  private static final Logger logger = LoggerFactory.getLogger(HelloBootApplication.class);

  public static void main(String[] args) {
    logger.info("Hello Container Application");
    SpringApplication.run(HelloBootApplication.class, args);
  }

}

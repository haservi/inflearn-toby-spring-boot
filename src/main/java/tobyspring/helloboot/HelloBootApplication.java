package tobyspring.helloboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import tobyspring.config.MySpringBootAnnotation;

@MySpringBootAnnotation
public class HelloBootApplication {

  private static final Logger logger = LoggerFactory.getLogger(HelloBootApplication.class);

  @Bean
  ApplicationRunner applicationRunner(Environment environment) {
    return args -> {
      String name = environment.getProperty("my.name");
      logger.info("my.name: {}", name);
    };
  }

  public static void main(String[] args) {
    logger.info("Hello Container Application");
    SpringApplication.run(HelloBootApplication.class, args);
  }

}

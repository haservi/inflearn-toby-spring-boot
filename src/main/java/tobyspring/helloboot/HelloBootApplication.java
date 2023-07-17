package tobyspring.helloboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloBootApplication {

  private static final Logger logger = LoggerFactory.getLogger(HelloBootApplication.class);

  public static void main(String[] args) {
    logger.info("Hello Container Application");
    SpringApplication.run(HelloBootApplication.class, args);
  }
}

package tobyspring.helloboot;

import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final HelloService helloService;
  private final ApplicationContext applicationContext;

  public HelloController(HelloService helloService, ApplicationContext applicationContext) {
    this.helloService = helloService;
    this.applicationContext = applicationContext;

    logger.info("applicationContext = " + applicationContext);
  }

  @GetMapping("/hello")
  public String hello(String name) {
    return helloService.sayHello(Objects.requireNonNull(name));
  }
}

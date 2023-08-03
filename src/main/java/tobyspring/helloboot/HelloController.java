package tobyspring.helloboot;

import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  private final HelloService helloService;

  public HelloController(HelloService helloService) {
    this.helloService = helloService;
  }

  @GetMapping("/hello")
  public String hello(String name) {
    logger.info("hello service");
    if (name == null || name.trim().length() == 0) {
      throw new IllegalArgumentException();
    }

    return helloService.sayHello(Objects.requireNonNull(name));
  }

  @GetMapping("/count")
  public String count(String name) {
    return name + ": " + helloService.countOf(name);
  }

}

package tobyspring.helloboot;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary // 우선 순위 지정
public class HelloDecorator implements HelloService {

  private final HelloService helloService;

  public HelloDecorator(HelloService helloService) {
    this.helloService = helloService;
  }

  @Override
  public String sayHello(String name) {
    return "*" + helloService.sayHello(name) + "*";
  }

  @Override
  public int countOf(String name) {
    return helloService.countOf(name);
  }
}

package tobyspring.helloboot;

import java.util.stream.IntStream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@HelloBootTest
class HelloServiceCountTest {

  @Autowired
  private HelloService helloService;

  @Autowired
  private HelloRepository helloRepository;

  @Test
  void say_hello_increase_count() {
    IntStream.rangeClosed(1, 10).forEach(count -> {
      helloService.sayHello("Haseop");
      Assertions.assertThat(helloRepository.countOf("Haseop")).isEqualTo(count);
    });
  }

}

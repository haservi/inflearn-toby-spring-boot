package tobyspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class HelloControllerTest {

  @Test
  void hello_controller() {
    // Given
    HelloController helloController = new HelloController(name -> name);
    // When
    String response = helloController.hello("Test");
    // Then
    Assertions.assertThat(response).isEqualTo("Test");
  }

  @Test
  void hello_controller_is_null() {
    // Given
    HelloController helloController = new HelloController(name -> name);

    // Then
    Assertions.assertThatThrownBy(() -> {
      helloController.hello(null);
    }).isInstanceOf(IllegalArgumentException.class);

    Assertions.assertThatThrownBy(() -> {
      helloController.hello("");
    }).isInstanceOf(IllegalArgumentException.class);
  }

}

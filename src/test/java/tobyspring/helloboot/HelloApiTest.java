package tobyspring.helloboot;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
class HelloApiTest {

  @Test
  void hello_api() {
    // Given: http localhost:8080/hello?name=Spring
    TestRestTemplate restTemplate = new TestRestTemplate();
    ResponseEntity<String> response = restTemplate.getForEntity(
        "http://localhost:9090/app/hello?name={name}", String.class, "Spring");

    // Then: status code 200
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    // Then: header -> text/plain
    assertThat(response.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE)).startsWith(
        MediaType.TEXT_PLAIN_VALUE);
    // Then: body Hello Spring
    assertThat(response.getBody()).isEqualTo("*Hello Spring*");
  }

  @Test
  void fail_hello_api() {
    // Given: http localhost:8080/hello123?name=Spring
    TestRestTemplate restTemplate = new TestRestTemplate();
    ResponseEntity<String> response = restTemplate.getForEntity(
        "http://localhost:9090/hello123?name={name}", String.class, "Spring");

    // Then: status code 404
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
  }

}

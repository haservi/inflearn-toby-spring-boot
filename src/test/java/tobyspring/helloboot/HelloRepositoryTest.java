package tobyspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@HelloBootTest
public class HelloRepositoryTest {

  @Autowired
  private HelloRepository helloRepository;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @BeforeEach
  void init() {
    jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS hello(name varchar(50) PRIMARY KEY, count INT)");
  }

  @Test
  void find_hello_failed() {
    Assertions.assertThat(helloRepository.findHello("Tester")).isNull();
  }

  @Test
  void increase_count() {
    Assertions.assertThat(helloRepository.countOf("Haseop")).isZero();

    helloRepository.increaseCount("Haseop");
    Assertions.assertThat(helloRepository.countOf("Haseop")).isEqualTo(1);

    helloRepository.increaseCount("Haseop");
    Assertions.assertThat(helloRepository.countOf("Haseop")).isEqualTo(2);
  }

}

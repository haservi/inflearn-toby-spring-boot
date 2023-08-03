package tobyspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@HelloBootTest
class JdbcTemplateTest {

  @Autowired
  JdbcTemplate jdbcTemplate;

  @BeforeEach
  void init() {
    jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS hello(name varchar(50) PRIMARY KEY, count INT)");
  }

  @Test
  void insert_and_query() {
    jdbcTemplate.update("INSERT INTO hello VALUES(?, ?)", "Haseop", 3);
    jdbcTemplate.update("INSERT INTO hello VALUES(?, ?)", "Tester", 1);

    Long count = jdbcTemplate.queryForObject("SELECT count(*) FROM hello", Long.class);
    Assertions.assertThat(count).isEqualTo(2);
  }

  @Test
  void insert_and_query2() {
    jdbcTemplate.update("INSERT INTO hello VALUES(?, ?)", "Haseop", 3);
    jdbcTemplate.update("INSERT INTO hello VALUES(?, ?)", "Tester", 1);

    Long count = jdbcTemplate.queryForObject("SELECT count(*) FROM hello", Long.class);
    Assertions.assertThat(count).isEqualTo(2);
  }
}

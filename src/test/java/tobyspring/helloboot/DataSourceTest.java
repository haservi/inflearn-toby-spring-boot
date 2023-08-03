package tobyspring.helloboot;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = HelloBootApplication.class)
@TestPropertySource("classpath:/application.properties")
class DataSourceTest {

  @Autowired
  DataSource dataSource;

  @Test
  void connect() throws SQLException {
    Connection connection = dataSource.getConnection();
    connection.close();
  }

}
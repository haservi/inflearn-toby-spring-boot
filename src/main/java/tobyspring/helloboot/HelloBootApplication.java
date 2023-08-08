package tobyspring.helloboot;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionEvaluationReport;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class HelloBootApplication {

  private static final Logger logger = LoggerFactory.getLogger(HelloBootApplication.class);

  private final JdbcTemplate jdbcTemplate;

  public HelloBootApplication(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Bean
  ApplicationRunner run(ConditionEvaluationReport report) {
    return args -> {
      System.out.println(report.getConditionAndOutcomesBySource()
          .entrySet().stream()
          .filter(co -> co.getValue().isFullMatch())
//          .filter(co -> co.getKey().indexOf("Jmx") < 0)
          .map(co -> {
            co.getValue().forEach(c -> {
              System.out.println("\t" + c.getOutcome());
            });
            System.out.println();
            return co;
          }).count());
    };
  }

  @PostConstruct
  void init() {
    jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS hello(name varchar(50) PRIMARY KEY, count INT)");
  }

  @Bean
  ApplicationRunner applicationRunner(Environment environment) {
    return args -> {
      String name = environment.getProperty("my.name");
      logger.info("my.name: {}", name);
    };
  }

  public static void main(String[] args) {
    logger.info("Hello Container Application");
    SpringApplication.run(HelloBootApplication.class, args);
  }

}

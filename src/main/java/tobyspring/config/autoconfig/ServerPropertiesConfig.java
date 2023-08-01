package tobyspring.config.autoconfig;

import java.util.Objects;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import tobyspring.config.MyAutoConfiguration;

@MyAutoConfiguration
public class ServerPropertiesConfig {

  @Bean
  public ServerProperties serverProperties(Environment environment) {
    ServerProperties serverProperties = new ServerProperties();

    serverProperties.setContextPath(environment.getProperty("contextPath"));
    serverProperties.setPort(Integer.parseInt(
        Objects.requireNonNull(environment.getProperty("port"))));

    return serverProperties;
  }

}

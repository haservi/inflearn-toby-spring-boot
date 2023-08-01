package tobyspring.config.autoconfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import tobyspring.config.ConditionalMyOnClass;
import tobyspring.config.MyAutoConfiguration;

@MyAutoConfiguration
@ConditionalMyOnClass("org.apache.catalina.startup.Tomcat")
public class TomcatWebServerConfig {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Bean("tomcatWebServerFactory")
  @ConditionalOnMissingBean
  public ServletWebServerFactory servletWebServerFactory(ServerProperties serverProperties) {
    TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();

    logger.info("contextPath: {}", serverProperties.getContextPath());

    serverFactory.setContextPath(serverProperties.getContextPath());
    serverFactory.setPort(serverProperties.getPort());

    return serverFactory;
  }


}

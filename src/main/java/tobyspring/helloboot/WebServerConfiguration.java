package tobyspring.helloboot;

import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class WebServerConfiguration {

  @Bean
  ServletWebServerFactory servletWebServerFactory() {
    JettyServletWebServerFactory serverFactory = new JettyServletWebServerFactory();
    serverFactory.setPort(9090);
    return serverFactory;
  }

}

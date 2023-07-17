package tobyspring.helloboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
public class HelloBootApplication {

  @Bean
  public HelloController helloController(HelloService helloService) {
    return new HelloController(helloService);
  }

  @Bean
  public HelloService helloService() {
    return new SimpleHelloService();
  }

  private static final Logger logger = LoggerFactory.getLogger(HelloBootApplication.class);

  public static void main(String[] args) {
    logger.info("Hello Container Application");

    AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
      @Override
      protected void onRefresh() {
        super.onRefresh();
        ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();

        WebServer webServer = serverFactory.getWebServer(servletContext -> {
          servletContext.addServlet("dispatcherServlet",
              new DispatcherServlet(this)
          ).addMapping("/*");
        });
        webServer.start();
      }
    };
    applicationContext.register(HelloBootApplication.class);
    applicationContext.refresh();

  }

}

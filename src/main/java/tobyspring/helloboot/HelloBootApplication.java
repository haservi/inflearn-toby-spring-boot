package tobyspring.helloboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@ComponentScan
public class HelloBootApplication {

  @Bean
  public ServletWebServerFactory servletWebServerFactory() {
    return new TomcatServletWebServerFactory();
  }

  @Bean
  public DispatcherServlet dispatcherServlet() {
    return new DispatcherServlet();
  }

  private static final Logger logger = LoggerFactory.getLogger(HelloBootApplication.class);

  public static void main(String[] args) {
    logger.info("Hello Container Application");

    AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
      @Override
      protected void onRefresh() {
        super.onRefresh();

        ServletWebServerFactory serverFactory = this.getBean(ServletWebServerFactory.class);
        DispatcherServlet dispatcherServlet = this.getBean(DispatcherServlet.class);

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

package tobyspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;

public class HelloBootApplication {

    public static void main(String[] args) {
        System.out.println("Hello Container Application");
        // jetty 등도 쓸 수 있음(동작확인은 필요)
//        ServletWebServerFactory jettyServletWebServerFactory = new JettyServletWebServerFactory();
        ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        WebServer webServer = serverFactory.getWebServer();
        webServer.start();
    }

}

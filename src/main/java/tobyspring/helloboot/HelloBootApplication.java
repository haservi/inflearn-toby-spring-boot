package tobyspring.helloboot;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.http.HttpStatus;

import java.io.IOException;

public class HelloBootApplication {

    public static void main(String[] args) {
        System.out.println("Hello Container Application");
        // jetty 등도 쓸 수 있음(동작확인은 필요)
//        ServletWebServerFactory jettyServletWebServerFactory = new JettyServletWebServerFactory();
        ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        WebServer webServer = serverFactory.getWebServer(servletContext -> {
            servletContext.addServlet("hello", new HttpServlet() {
                @Override
                protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                    resp.setStatus(HttpStatus.OK.value());
                    resp.setHeader("Content-type", "text/plain");
                    resp.getWriter().println("Hello servlet");
                }
            }).addMapping("/hello");
        });
        webServer.start();
    }

}

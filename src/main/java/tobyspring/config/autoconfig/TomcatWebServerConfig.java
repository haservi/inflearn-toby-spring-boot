package tobyspring.config.autoconfig;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import tobyspring.config.ConditionalMyOnClass;
import tobyspring.config.MyAutoConfiguration;

@MyAutoConfiguration
@ConditionalMyOnClass("org.apache.catalina.startup.Tomcat")
public class TomcatWebServerConfig {

  @Bean("tomcatWebServerFactory")
  @ConditionalOnMissingBean
  public ServletWebServerFactory servletWebServerFactory() {
    return new TomcatServletWebServerFactory();
  }

//  static class TomcatCondition implements Condition {
//
//    @Override
//    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
//      return ClassUtils.isPresent("org.apache.catalina.startup.Tomcat",
//          context.getClassLoader());
//    }
//  }
}

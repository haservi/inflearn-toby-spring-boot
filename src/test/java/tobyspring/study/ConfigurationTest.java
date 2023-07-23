package tobyspring.study;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

class ConfigurationTest {

  @Test
  void config_test_1() {
    assertThat(new Common()).isNotSameAs(new Common());
  }

  @Test
  void config_test_2() {
    MyConfig myConfig = new MyConfig();
    Bean1 bean1 = myConfig.bean1();
    Bean2 bean2 = myConfig.bean2();

    assertThat(bean1.common).isNotSameAs(bean2.common);
  }

  @Test
  @DisplayName("프록시 빈 메서드 설정에 따른 테스트")
  void config_test_3() {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    context.register(MyConfig.class);
    context.refresh();

    Bean1 bean1 = context.getBean(Bean1.class);
    Bean2 bean2 = context.getBean(Bean2.class);

    assertThat(bean1.common).isSameAs(bean2.common);
  }

  @Test
  void proxy_common_method() {
    MyConfigProxy myConfigProxy = new MyConfigProxy();

    Bean1 bean1 = myConfigProxy.bean1();
    Bean2 bean2 = myConfigProxy.bean2();

    assertThat(bean1.common).isSameAs(bean2.common);
  }

  static class MyConfigProxy extends MyConfig {

    private Common common;

    @Override
    Common common() {
      if (this.common == null) {
        this.common = super.common();
      }
      return this.common;
    }
  }

  @Configuration// (proxyBeanMethods = false)
  static class MyConfig {

    @Bean
    Common common() {
      return new Common();
    }

    @Bean
    Bean1 bean1() {
      return new Bean1(common());
    }

    @Bean
    Bean2 bean2() {
      return new Bean2(common());
    }

  }

  static class Bean1 {

    private final Common common;

    Bean1(Common common) {
      this.common = common;
    }
  }

  static class Bean2 {

    private final Common common;

    Bean2(Common common) {
      this.common = common;
    }
  }

  static class Common {

  }
}

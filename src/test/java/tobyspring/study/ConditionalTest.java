package tobyspring.study;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.AnnotatedTypeMetadata;

class ConditionalTest {

  @Test
  void conditional() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
    ac.register(Config1.class);
    ac.refresh();

    MyBean bean = ac.getBean(MyBean.class);
  }

  @Test
  void false_condition() {
    AnnotationConfigApplicationContext ac2 = new AnnotationConfigApplicationContext();
    ac2.register(Config2.class);
    ac2.refresh();

    Assertions.assertThatThrownBy(() -> ac2.getBean(MyBean.class))
        .isInstanceOf(NoSuchBeanDefinitionException.class);
  }

  @DisplayName("컨텍스트러너를 이용한 성공 테스트")
  @Test
  void context_runner_true() {
    ApplicationContextRunner applicationContextRunner = new ApplicationContextRunner();
    applicationContextRunner.withUserConfiguration(Config1.class)
        .run(context -> {
          Assertions.assertThat(context).hasSingleBean(MyBean.class);
          Assertions.assertThat(context).hasSingleBean(Config1.class);
        });
  }

  @DisplayName("컨텍스트러너를 이용한 실패 테스트")
  @Test
  void context_runner_false() {
    new ApplicationContextRunner().withUserConfiguration(Config2.class)
        .run(context -> {
          Assertions.assertThat(context).doesNotHaveBean(MyBean.class);
          Assertions.assertThat(context).doesNotHaveBean(Config2.class);
        });
  }

  @Retention(RetentionPolicy.RUNTIME)
  @Target(ElementType.TYPE)
  @Conditional(BooleanCondition.class)
  @interface BooleanConditional {

    boolean value();
  }

  @Configuration
  @BooleanConditional(true)
  static class Config1 {

    @Bean
    MyBean myBean() {
      return new MyBean();
    }
  }


  @Configuration
  @BooleanConditional(false)
  static class Config2 {

    @Bean
    MyBean myBean() {
      return new MyBean();
    }
  }

  static class MyBean {

  }

  static class BooleanCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
      // metadata에 interface값이 들어있음
      Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(
          BooleanConditional.class.getName());
      return (Boolean) annotationAttributes.get("value");
    }
  }

}

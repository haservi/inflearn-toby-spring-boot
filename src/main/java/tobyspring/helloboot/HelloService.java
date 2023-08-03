package tobyspring.helloboot;

public interface HelloService {

  String sayHello(String name);

  // 구현을 안해도 되는 default
  default int countOf(String name) {
    return 0;
  }
}

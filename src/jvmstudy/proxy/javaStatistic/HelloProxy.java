package proxy.javaStatistic;

/**
 * Proxy
 */
public class HelloProxy implements HelloService {

    private Hello source = new Hello();

    @Override
    public void sayHello() {
        before();
        source.sayHello();
        after();
    }

    private void before(){
        System.out.println("Before hello, I need prepare something...");
    }

    private void after(){
        System.out.println("After hello, I need conclude something...");
    }
}

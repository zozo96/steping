package proxy.javaStatistic;

/**
 * Original
 */
public class Hello implements HelloService{

    @Override
    public void sayHello() {
        System.out.println("hi, this is Hello-Original.");
    }

    public static void main(String[] args) {
        HelloProxy helloProxy = new HelloProxy();
        helloProxy.sayHello();
    }
}

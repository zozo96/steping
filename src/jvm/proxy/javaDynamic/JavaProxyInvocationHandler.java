package proxy.javaDynamic;

import proxy.javaStatistic.Hello;
import proxy.javaStatistic.HelloService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JavaProxyInvocationHandler implements InvocationHandler {

    private Object obj;

    public JavaProxyInvocationHandler(Object obj){
        this.obj = obj;
    }

    public Object newProxyInstance(){
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("obj invoked before...");
        Object result = method.invoke(obj, args);
        System.out.println("obj invoked after...");
        return result;
    }

    public static void main(String[] args) {
        JavaProxyInvocationHandler proxyHandler = new JavaProxyInvocationHandler(new Hello());
        HelloService helloService = (HelloService) proxyHandler.newProxyInstance();
        helloService.sayHello();
    }
}

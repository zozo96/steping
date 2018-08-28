/**
 * 项目名：  steping
 * 文件名：  ProxyTest.java
 * 模块说明：
 * 修改历史：
 * 2018-07-19 - Songyanyan - 创建。
 */
package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Random;

/**
 * 代理测试类
 *
 * @author Songyanyan
 */
public class ProxyTest {
  public static void main(String[] args) {
    Object[] objects = new Object[500];
    Class[] interfaces = new Class[] { Comparable.class, Iterable.class };
    
    for (int i = 0; i < objects.length; i++) {
      Integer value = i + 1;
      ClassTraceHandler handler = new ClassTraceHandler(value);
      objects[i] = Proxy.newProxyInstance(null, interfaces, handler);
    }
    
    Integer key = new Random().nextInt(objects.length) + 1;
    int result = Arrays.binarySearch(objects, key);
    if (result >= 0)
      System.out.println(objects[result]);
    
  }
}

class ClassTraceHandler implements InvocationHandler {
  private Object target;
  
  public ClassTraceHandler(Object t) {
    target = t;
  }
  
  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    System.out.print(target);
    System.out.print("." + method.getName() + "(");
    if (args != null) {
      for (int i = 0; i < args.length; i++) {
        System.out.print(args[i]);
        if (i < args.length - 1)
          System.out.print(", ");
      }
    }
    System.out.println(")");
    
    return method.invoke(target, args);
  }
}

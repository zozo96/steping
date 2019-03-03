/**
 * 项目名：  steping
 * 文件名：  LookForClassLoader.java
 * 模块说明：
 * 修改历史：
 * 2019-02-11 - Songyanyan - 创建。
 */

import java.util.Vector;

/**
 * 1.查看系统的类加载器,Java默认提供三个ClassLoader 2.查看内存回收method.showGCInfo
 *
 * @author Songyanyan
 */
public class LookForClassLoader {
  private static int count = 0;
  public static void main(String[] args) {
    
    showGCInfo();
    // printXmxXms();
    // testGC();
    // testHeapDump();
    // try {
    // testStackDeep(0L, 0L, 0L);
    // } catch (Throwable e) {
    // System.out.println("deep of calling:" + count);
    // e.printStackTrace();
    // }
  }
  
  public void findClassLoader() {
    ClassLoader currentLoader = Thread.currentThread().getContextClassLoader(); // 获取当前线程上下文的类加载器
    System.out.println("current classLoader:" + currentLoader);
    System.out.println("parent classLoader:" + currentLoader.getParent());
    System.out.println("grandparent classLoader:" + currentLoader.getParent().getParent());
    
    // grandparent classLoader，即最顶层引导加载类BootStrap ClassLoader加载哪些类
    // 该加载器由C++编写，内嵌在JVM内核中，在内存（Java环境）中找不到其相应句柄，所以为null
    System.out.println(System.getProperty("sun.boot.class.path"));
  }
  
  public static void showGCInfo() {
    // -verbose:jni
    LookForClassLoader obj = new LookForClassLoader();
    System.gc();
  }
  
  public static void printXmxXms() {
    byte[] b = new byte[4 * 1024 * 1024];
    System.out.println("分配了4M空间给数组");
    System.gc();
    System.out.println("回收内存");
    System.out.print("Xmx=");
    System.out.println(Runtime.getRuntime().maxMemory() / 1024 / 1024 + "M");
    System.out.print("free mem=");
    System.out.println(Runtime.getRuntime().freeMemory() / 1024 / 1024 + "M");
    System.out.print("total mem=");
    System.out.println(Runtime.getRuntime().totalMemory() / 1024 / 1024 + "M");
  }
  
  public static void testGC() {
    // -Xmx20m -Xms20M -Xmn15m -XX:+PrintGCDetails
    byte[] b = null;
    for (int i = 0; i < 10; i++)
      b = new byte[1024 * 1024];
    
  }
  
  public static void testHeapDump() {
    Vector vector = new Vector();
    for (int i = 0; i < 25; i++) {
      vector.add(new byte[1024 * 1024]);
    }
  }
  
  // 去掉局部变量 调用层次可以更深
  public static void testStackDeep(long a, long b, long c) {
    long e = 1, f = 2, g = 3, h = 4;
    count++;
    testStackDeep(a, b, c);
  }
}

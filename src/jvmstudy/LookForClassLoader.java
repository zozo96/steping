/**
 * 项目名：  steping
 * 文件名：  LookForClassLoader.java
 * 模块说明：
 * 修改历史：
 * 2019-02-11 - Songyanyan - 创建。
 */

/**
 * 1.查看系统的类加载器,Java默认提供三个ClassLoader 2.查看内存回收method.showGCInfo
 *
 * @author Songyanyan
 */
public class LookForClassLoader {
  public static void main(String[] args) {
    showGCInfo();
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
    LookForClassLoader obj = new LookForClassLoader();
    System.gc();
  }
}

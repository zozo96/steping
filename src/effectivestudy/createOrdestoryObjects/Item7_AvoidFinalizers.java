package createOrdestoryObjects;
/**
 * 项目名：  steping
 * 文件名：  createOrdestoryObjects.Item7_AvoidFinalizers.java
 * 模块说明：
 * 修改历史：
 * 2018-02-26 - Songyanyan - 创建。
 */

/**
 * 尽量避免使用终结方法
 * <li>1.注重时间的任务不要使用</li>
 * <li>2.System.gc和System.runFinalization不能保证终止方法及时执行，实际是不能保证finalizer执行</li>
 * <li>3.执行终结方法的时间在不同JVM上相差很大</li>
 * <li>4.如果只是简单的调用终结方法而不加控制，在终结的过程中若有异常出现，未捕获的异常会使终结过程中断、对象处于破坏状态并且不能给出任何异常信息</li>
 * <li>5.还有个是性能问题，使用终结方法创建销毁对象的时间花费是不使用的400times</li>
 * <li>6.若需要使用可以结合try{}finally{//显式终结方法}</li>
 * <li>用处：1.安全网终结方法 2.本地对等体</li>
 * <li>安全网终结方法注意事项：需要记录终结方法非法用法（如果对象资源并没有被终结）</li>
 * <li>本地对等体时，需要手动调用显示终结方法</li>
 * 
 * 也可以考虑使用终结方法守卫者
 * 
 * @author Songyanyan
 */
public class Item7_AvoidFinalizers {
  
  // try-finally block gurantees execution of termination method
  public void guranteeTermination() {
    String obj = "hello";
    try {
      // Do ..
    } finally {
      // obj.terminate();
    }
  }
  
  // manual finalizer chain 手动构造终结器链
  @Override
  public void finalize() throws Throwable {
    try {
      // finalization 即时自己定义的终结方法出问题，也是可以实现最终的final
    } finally {
      super.finalize();
    }
  }
  
}

class Foo {
  // 自己额外写一个终结类：Star below
  private final Object finalizerGuardian = new Object() {
    @Override
    public void finalize() throws Throwable {
      // 终结外层实例
    }
  };
}
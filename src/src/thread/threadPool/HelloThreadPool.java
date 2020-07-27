/**
 * 项目名：  steping
 * 文件名：  HelloThreadPool.java
 * 模块说明：
 * 修改历史：
 * 2019-05-28 - Songyanyan - 创建。
 */
package thread.threadPool;

import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * 初次尝试线程池
 *
 * @author Songyanyan
 */
public class HelloThreadPool {
  public static void main(String[] args) {
    ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 2,
      100, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(2));

    IntStream.range(0, 5).mapToObj(PrintTask::new).forEach(printTask -> {
      executor.execute(printTask);
      System.out.println("线程池中所有线程数目：" + executor.getPoolSize() + "，队列中待执行的任务数目：" +
        executor.getQueue().size() + "，已执行完的任务数目：" + executor.getCompletedTaskCount());
    });

    executor.shutdown();
  }

  public static void newSingleThreadPool() {
    // 创建一个单线程化的线程池
    ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
    for (int i = 0; i < 10; i++) {
      final int index = i;
      singleThreadExecutor.execute(() -> {
        try {
          // 结果依次输出，相当于顺序执行各个任务
          System.out.println(Thread.currentThread().getName() + "正在被执行,打印的值是:" + index);
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      });
    }
  }

  public static void newScheduledThreadPool() {
    // 创建一个定长线程池，支持定时及周期性任务执行——延迟执行
    // 延迟1秒执行
    ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
    scheduledThreadPool.schedule(() -> System.out.println("延迟1秒执行"), 1, TimeUnit.SECONDS);
  }

  public static void newFixedThreadPool() {
    // 创建一个可重用固定个数的线程池
    ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
    for (int i = 0; i < 10; i++) {
      fixedThreadPool.execute(new Runnable() {
        public void run() {
          try {
            // 打印正在执行的缓存线程信息
            System.out.println(Thread.currentThread().getName() + "正在被执行");
            Thread.sleep(2000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      });
    }
  }

  public static void newCachedThreadPool() {
    ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    for (int i = 0; i < 10; i++) {
      try {
        // sleep可明显看到使用的是线程池里面以前的线程，没有创建新的线程
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      cachedThreadPool.execute(new Runnable() {
        public void run() {
          // 打印正在执行的缓存线程信息
          System.out.println(Thread.currentThread().getName() + "正在被执行");
        }
      });
    }
  }
}

class PrintTask implements Runnable {
  private int taskIndex;

  PrintTask(int index) {
    this.taskIndex = index;
  }

  @Override
  public void run() {
    System.out.println(taskIndex + " is running...");
    try {
      TimeUnit.SECONDS.sleep(2);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(taskIndex + " end");
  }
}

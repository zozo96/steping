package base;

import java.util.stream.IntStream;

import static java.lang.Thread.currentThread;

/**
 * 线程构造器详细使用
 *
 * @author Sophi
 * @since 2019/5/25
 **/
public class ThreadConstructorDetail {
    private static final String PREFIX = "prefix";

    public static void main(String[] args) {

        // 默认无参构造线程，线程名为 Thread-x ,线程组为父线程组，即main
        System.out.println("----无参构造线程，线程名值从0开始递增----");
        IntStream.range(1, 5).boxed().map(
                i -> new Thread(() -> System.out.println(currentThread().getName()))
        ).forEach(Thread::start);

        // 设定线程名字，
        Thread t1 = new Thread("NormalThread");
        System.out.println("----指定线程t1名字：" + t1.getName() + "----");
        t1.setName("modifiedName");
        System.out.println("----修改线程t1名字：" + t1.getName() + "----");
        IntStream.range(1, 5).mapToObj(ThreadConstructorDetail::createThread).forEach(Thread::start);

        // 获取未被start的线程的当前线程，即为父线程
        // 一个线程的创建肯定是由另一个线程完成
        Thread parent = currentThread();

        TicketWindowRunnable task = new TicketWindowRunnable();
        Thread t2_1 = new Thread(task);
        Thread t2_2 = new Thread(task,"CustomRunnableThread");

        // 线程组
        ThreadGroup group = new ThreadGroup("CustomThreadGroup");
        Thread t3_1 = new Thread(group, "CustomThreadGroup_t3_1");
        Thread t3_2 = new Thread(group, task);
        Thread t3_3 = new Thread(group, task, "CustomThreadGroup_t3_3");
        Thread t3_4 = new Thread(group, task, "CustomThreadGroup_t3_4", 102400);
        // 线程如果没有被显式地加入到线程组，就会被加入到父线程所在的线程组
        System.out.println("t2_1未被加入显式组的线程组：" + t2_1.getThreadGroup().getName());
        System.out.println("t3_1加入显式组的线程组：" + t3_1.getThreadGroup().getName());

    }

    public static Thread createThread(int no) {
        return new Thread(() -> System.out.println(currentThread().getName()), PREFIX + no);
    }
}

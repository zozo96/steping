package thread.api;

import java.util.concurrent.TimeUnit;

/**
 * 线程优先级
 *
 * 若CPU较忙，设置优先级可能会优先获取时间片
 * 该例子中t2优先级为10，出现频率高于t1，不同情况下结果不一样
 *
 * 子线程的优先级 是要<=所在线程组的最高优先级
 * @see Thread#setPriority
 * @see #testPriorityAction
 *
 * 优先级默认和父类保持一致，main线程优先级为5
 *
 * @author Sophi
 * @since 2019/5/25
 **/
public class ThreadPriority {
    public static void main (String[] args) throws InterruptedException{
        Thread t1 = new Thread(() -> {
            while (true){
                System.out.println(Thread.currentThread().getName());
            }
        }, "t1");
        t1.setPriority(3);
        t1.setDaemon(true);
        Thread t2 = new Thread(() -> {
            while (true){
                System.out.println(Thread.currentThread().getName());
            }
        }, "t2");
        t2.setPriority(10);
        t2.setDaemon(true);

        t1.start();
        t2.start();

        TimeUnit.SECONDS.sleep(1);

        testPriorityAction();
    }

    public static void testPriorityAction(){

        System.out.println(Thread.currentThread().getName() + " priority is " + Thread.currentThread().getPriority());

        ThreadGroup group = new ThreadGroup("customGroup");
        group.setMaxPriority(7);

        Runnable task = () -> System.out.println(Thread.currentThread().getName() + " belong group " + Thread.currentThread().getThreadGroup().getName() + ", priority is " + Thread.currentThread().getPriority());

        new Thread(task).start();

        Thread highPriorityThread = new Thread(group, task ,"priority10Thread");
        highPriorityThread.setPriority(10);
        highPriorityThread.start();
    }
}

package thread.security;

import java.util.concurrent.TimeUnit;

/**
 * 特殊Monitor之class对象Monitor
 * 即：对静态方法使用 Synchronized
 *
 * 实例方法使用 Synchronized 见：
 * @see ThisMonitor 实例对象监听
 * @see #m1 m1 m2 m3 m4会争夺同一 monitor
 * @author Sophi
 * @since 2019/6/8
 **/
public class ClassMonitor {
    public synchronized static void m1() {
        System.out.println("m1抢到啦");
        try {
            TimeUnit.MINUTES.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized static void m2() {
        System.out.println("m2抢到啦");
        try {
            TimeUnit.MINUTES.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void m3() {
        synchronized (ClassMonitor.class){
            System.out.println("m3抢到啦");
            try {
                TimeUnit.MINUTES.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void m4() {
        synchronized (ClassMonitor.class){
            System.out.println("m4抢到啦");
            try {
                TimeUnit.MINUTES.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

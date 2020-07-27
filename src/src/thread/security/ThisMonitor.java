package thread.security;

import java.util.concurrent.TimeUnit;

/**
 * 特殊 Monitor 之 实例对象Monitor
 * 对非静态方法使用 synchronized。
 *
 * @author Sophi
 * @see #m1 m1 m2 m3 m4会争夺同一 monitor
 * @see ClassMonitor class对象监听
 * @since 2019/6/8
 **/
public class ThisMonitor {
    public synchronized void m1() {
        System.out.println("m1抢到啦");
        try {
            TimeUnit.MINUTES.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void m2() {
        System.out.println("m2抢到啦");
        try {
            TimeUnit.MINUTES.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void m3() {
        synchronized (this){
            System.out.println("m3抢到啦");
            try {
                TimeUnit.MINUTES.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void m4() {
        synchronized (this){
            System.out.println("m4抢到啦");
            try {
                TimeUnit.MINUTES.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ThisMonitor monitor = new ThisMonitor();
        new Thread(monitor::m1).start();
        new Thread(monitor::m2).start();
        new Thread(monitor::m3).start();
        new Thread(monitor::m4).start();
    }
}

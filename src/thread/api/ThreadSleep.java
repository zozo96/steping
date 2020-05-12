package api;

import java.util.concurrent.TimeUnit;

/**
 * 线程休眠
 *
 * @author Sophi
 * @since 2019/5/25
 **/
public class ThreadSleep {
    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            long s_t = System.currentTimeMillis();
            try {
                TimeUnit.SECONDS.sleep(2);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            long e_t = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + " spends " + (e_t - s_t) + "mls");
        }, "custom").start();

        long s_t = System.currentTimeMillis();
        TimeUnit.SECONDS.sleep(2);
        long e_t = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName() + " spends " + (e_t - s_t) + "mls");
    }
}

package thread.api;

import java.util.concurrent.TimeUnit;

/**
 * 打断线程阻塞状态
 * 可以造成线程阻塞的方法：
 * @see Object#wait
 * @see Thread#sleep
 * @see Thread#join
 * ...
 *
 * @author Sophi
 * @since 2019/5/25
 **/
public class ThreadInterrupt {
    public static void main(String[] args) throws Exception {
        Thread thread = new Thread(() -> {
            try {
                TimeUnit.MINUTES.sleep(1);
            }catch (InterruptedException e){
                System.out.println("I'm interrupted");
            }
        });
        thread.start();
        TimeUnit.MILLISECONDS.sleep(2);
        thread.interrupt();
    }
}

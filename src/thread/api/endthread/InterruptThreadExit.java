package api.endthread;

import java.util.concurrent.TimeUnit;

/**
 * [正常退出]
 * 通过捕获中断信号关闭线程
 * 线程interrupt标识会被擦除的情况下不能使用
 *
 * @author Sophi
 * @since 2019/5/30
 **/
public class InterruptThreadExit {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(){
            @Override
            public void run() {
                System.out.println("I'm born, Happy~");
                while (!isInterrupted()){
                    // do ur work, by `isInterrupted() = true` exit thread's run
                }
                System.out.println("Uhmmm, OK, I'm shut down");
            }
        };
        thread.start();
        TimeUnit.SECONDS.sleep(10);
        System.out.println("main is done");
        thread.interrupt();
    }
}

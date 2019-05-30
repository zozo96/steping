package api;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * 线程是否被打断阻塞
 *
 * 可中断方法可以擦除线程 interrupted 状态
 * @see #testSleepResetInterrupted
 *
 * 线程执行可中断方法Sleep之前就被 interrupt
 * @see #testInterruptBeforeSleep
 *
 * @author Sophi
 * @since 2019/5/25
 **/
public class ThreadIsInterrupted {
    public static void main(String[] args) throws InterruptedException {
        Thread.interrupted();
        Thread thread = new Thread(() -> {
            while (true){

            }
        });
        thread.start();
        // 保证 thread 已运行
        TimeUnit.SECONDS.sleep(2);
        System.out.println("Step 1 : isInterrupted ? " + thread.isInterrupted());
        thread.interrupt();
        System.out.println("Step 2 : isInterrupted ? " + thread.isInterrupted());

        testSleepResetInterrupted();

        testInterruptBeforeSleep();
    }


    private static void testSleepResetInterrupted() throws InterruptedException{
        Thread thread = new Thread(() -> {
            while (true){
                try {
                    TimeUnit.MINUTES.sleep(1);
                }catch (InterruptedException e){
                    System.out.println("I'M INTERRUPTED,GO ON SLEEPING,SO Interrupted FLAG SWAPPED");
                }
            }
        });
        thread.start();
        TimeUnit.MILLISECONDS.sleep(2); // 保证 thread 已运行
        System.out.println("ResetStep 1 : isInterrupted ? " + thread.isInterrupted());
        thread.interrupt();
        System.out.println("ResetStep 2 : isInterrupted ? " + thread.isInterrupted());
    }

    private static void testInterruptBeforeSleep(){
        System.out.println("currentThread().isInterrupted(false) ：" + Thread.currentThread().isInterrupted());
        System.out.println("currentThread().isInterrupted(true) ：" + Thread.interrupted());

        Thread.currentThread().interrupt();
        System.out.println("Main thread is interrupted ? " + Thread.currentThread().isInterrupted());

        try {
            TimeUnit.MILLISECONDS.sleep(2);
            System.out.println("this cannot be logged");
        }catch (InterruptedException e){
            System.out.println("sleep-process Interrupted");
        }

    }
}

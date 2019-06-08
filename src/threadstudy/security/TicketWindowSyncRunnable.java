package security;

/**
 * 橱窗叫号程序 - 关键字同步
 * Synchronized 的互斥机制：同一时刻只有一个线程可以访问同步资源，即线程获取到与mutex关联的monitor锁
 *
 * 改进：使用的 Runnable 和 Synchronized互斥信号量 进行叫号
 *
 * @author Sophi
 * @since 2019/6/8
 **/
public class TicketWindowSyncRunnable implements Runnable {

    private final static int MAX = 50;
    private int index = 1;

    private static final Object MUTEX = new Object();

    @Override
    public void run() {
        // 使用互斥信号量对争抢的资源读写进行隔离
        synchronized (MUTEX){
            while (index <= MAX){
                System.out.println("index:"  + index++ + " is running at " + Thread.currentThread().getName());
            }
        }
    }

    public static void main(String[] args) {
        // 对同一个Runnable任务进行操作，任务中的资源是共享的，可以避免声明大量 static 资源
        TicketWindowSyncRunnable task = new TicketWindowSyncRunnable();
        Thread win_1 = new Thread(task, "窗口1");
        Thread win_2 = new Thread(task, "窗口2");
        Thread win_3 = new Thread(task, "窗口3");
        Thread win_4 = new Thread(task, "窗口4");

        win_1.start();
        win_2.start();
        win_3.start();
        win_4.start();
    }
}

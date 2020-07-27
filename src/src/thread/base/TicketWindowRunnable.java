package thread.base;

/**
 * 营业大厅叫号机用Runnable方式实现
 *
 * 改进：线程不安全
 * @see thread.security.TicketWindowSyncRunnable
 *
 * @author Sophi
 * @since 2019/5/25
 **/
public class TicketWindowRunnable implements Runnable{

    private final static int MAX = 50;
    // 依然存在线程安全问题，因为线程执行的顺序不可控
    private int index = 1;

    @Override
    public void run() {
        while (index <= MAX){
            System.out.println("index:"  + index++ + " is running at " + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        // 对同一个Runnable任务进行操作，任务中的资源是共享的，可以避免声明大量 static 资源
        TicketWindowRunnable task = new TicketWindowRunnable();
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

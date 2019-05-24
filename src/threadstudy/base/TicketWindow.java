package base;

import java.util.concurrent.TimeUnit;

/**
 * 模拟业务橱窗叫号程序 - 窗口
 *
 * 改进：共享资源仅仅依靠 static 修饰很薄弱，存在线程不安全且 static 字段生命周期过久
 * @see TicketWindowRunnable
 *
 * 改进：业务与逻辑耦合紧密。将线程控制和业务逻辑分开，即策略模式，见
 * @see RecordQuery
 * @see RowHandler
 *
 * @author Sophi
 * @since 2019/5/24
 **/
public class TicketWindow extends Thread{
    private static int MAX_DEAL = 50;
    private volatile static int index = 1;
    private String windowName;
    TicketWindow(int no){
        windowName = no + "号柜台";
    }

    @Override
    public void run() {
        while (index <= MAX_DEAL){
//            System.out.println("index:"  + index++ + " is running at " + windowName);
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("index:"  + index++ + " is running at " + windowName);
            } catch (InterruptedException e){
                System.out.println(windowName + " is interrupted");
            }
        }
    }

    public static void main(String[] args) {
        TicketWindow window_1 = new TicketWindow(1);
        TicketWindow window_2 = new TicketWindow(2);
        TicketWindow window_3 = new TicketWindow(3);
        TicketWindow window_4 = new TicketWindow(4);
        window_1.start();
        window_2.start();
        window_3.start();
        window_4.start();
    }
}
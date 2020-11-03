package thread.base;

/**
 * 守护线程
 * <p>
 * JVM exits when the only threads running are all daemon threads
 *
 * @author Sophi
 * @since 2019/5/25
 **/
public class DaemonThread {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "CustomThread");
        // 将thread设置为守护线程，main执行完，程序中的线程只剩下守护线程，JVM也将退出
        // 要在执行前设置，否则 IllegalThreadStateException
        thread.setDaemon(true);
        System.out.println("thread 为守护线程：" + thread.isDaemon());
        thread.start();
        Thread.sleep(2_000L);
        System.out.println("main thread Finished");
    }
}

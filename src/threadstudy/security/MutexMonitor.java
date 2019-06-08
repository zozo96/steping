package security;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * 互斥信号量的监听表现
 * <p>
 * - 使用 Jconsole 对堆栈进行分析：
 * Thread-0 获取到锁，状态为 TIMED_WAITING(sleeping)
 * Thread-1 被阻塞，状态为 BLOCKED， 当前锁的持有者为 Thread-0
 * Thread-2 被阻塞，状态为 BLOCKED， 当前锁的持有者为 Thread-0
 * <p>
 * - 使用 Jstack 打印进程的线程堆栈信息：
 * Thread-2 java.lang.Thread.State: BLOCKED (on object monitor)
 * - waiting to lock <0x00000000eb52bb30> (a java.lang.Object)
 * Thread-1 java.lang.Thread.State: BLOCKED (on object monitor)
 * - waiting to lock <0x00000000eb52bb30> (a java.lang.Object)
 * Thread-0 java.lang.Thread.State: TIMED_WAITING (sleeping)
 * at java.lang.Thread.sleep(Thread.java:340)
 * <p>
 *
 * 反编译该文件的 class，查看JVM指令与详解，见 MutexMonitorJavaP.md
 *
 * @author Sophi
 * @since 2019/6/8
 **/
public class MutexMonitor {
    private static final Object MUTEX = new Object();

    public void accessResource(int n) {
        synchronized (MUTEX) {
            try {
                System.out.println(n + " accessed the Monitor.");
                TimeUnit.MINUTES.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        final MutexMonitor monitor = new MutexMonitor();
        IntStream.range(0, 5).forEach(i -> new Thread(() -> monitor.accessResource(i)).start());
    }
}

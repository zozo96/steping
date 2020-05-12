package api;

import java.util.stream.IntStream;

/**
 * 提醒调度器愿意放弃当前的CPU资源，如果CPU资源不紧张，则会忽略这种提醒
 *
 * yield 对CPU调度器的一个提示，若调度器没有忽略该提示，会引起线程上下文的切换
 *
 * sleep 会导致当前线程暂停指定时间（短暂BLOCK），释放CPU资源
 *
 * @author Sophi
 * @since 2019/5/25
 **/
public class ThreadYield {
    public static void main(String[] args) {
        IntStream.range(0, 5).mapToObj(ThreadYield::createThread).forEach(Thread::start);
    }

    private static Thread createThread(int index) {
        return new Thread(()-> {
            if (index != 0)
                Thread.yield();
            System.out.println(index);
        });
    }
}

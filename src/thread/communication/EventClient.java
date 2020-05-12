package communication;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * 单线程生产者消费者事件发生模拟器
 *
 * @author Sophi
 * @since 2019/6/16
 **/
public class EventClient {
    public static void main(String[] args) {
//        singleThreadSimulation();
        mutiThreadSimulation();
    }

    public static void singleThreadSimulation(){
        final EventQueue queue = new EventQueue();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                queue.offer(new EventQueue.Event());
            }
        }, "Producer").start();

        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    queue.take();
                    TimeUnit.MILLISECONDS.sleep(10);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }, "Consumer").start();
    }

    public static void mutiThreadSimulation(){
        final EventQueueSupMulti queue = new EventQueueSupMulti();
        IntStream.range(0, 20).forEach(i-> new Thread(() -> queue.offer(new EventQueueSupMulti.Event()), "Producer" + i).start());
        IntStream.range(0, 20).forEach(i->{
            new Thread(() -> {
                queue.take();
                }, "Consumer" + i).start();
        });
    }
}

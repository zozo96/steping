package communication;

import java.util.LinkedList;

/**
 * 事件队列
 * 队列有三种状态：队列空，队列满，有Event但未达到队列上限
 * 队列满：生产者线程将会被阻塞
 * 队列空：消费者线程将会被阻塞
 * 通过notify唤醒曾被同一monitor执行wait阻塞的线程
 *
 * wait 和 notify 是 Object 对象的方法
 *
 * @author Sophi
 * @since 2019/6/16
 **/
public class EventQueue {
    private final int max;
    static class Event{

    }
    private final LinkedList<Event> eventQueue = new LinkedList<>();
    private static final int DEFAULT_MAX_EVENT = 10;

    public EventQueue(){
        this(DEFAULT_MAX_EVENT);
    }

    public EventQueue(int maxSize){
        this.max = maxSize;
    }

    public void offer(Event event){
        synchronized (eventQueue){
            if (eventQueue.size() >= max){
                try {
                    console("the queue is full, try later.");
                    eventQueue.wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

            console("a new event is submitted.");
            eventQueue.addLast(event);
            eventQueue.notify();
        }
    }

    public Event take(){
        synchronized (eventQueue){
            if (eventQueue.isEmpty()){
                try {
                    console("the queue is empty, try later.");
                    eventQueue.wait();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

            Event e = eventQueue.removeFirst();
            eventQueue.notify();
            console("the head event " + e + " of the queue is token.");

            return e;
        }
    }

    private void console(String msg){
        System.out.printf("%s: %s\n", Thread.currentThread().getName(), msg);
    }
}

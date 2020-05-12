package communication;

import java.util.LinkedList;

/**
 * 支持多线程访问的队列
 *
 * @author Sophi
 * @since 2019/6/16
 **/
public class EventQueueSupMulti {
    private final int max;
    static class Event{

    }
    private final LinkedList<EventQueueSupMulti.Event> eventQueue = new LinkedList<>();
    private static final int DEFAULT_MAX_EVENT = 10;

    public EventQueueSupMulti(){
        this(DEFAULT_MAX_EVENT);
    }

    public EventQueueSupMulti(int maxSize){
        this.max = maxSize;
    }

    public void offer(EventQueueSupMulti.Event event){
        synchronized (eventQueue){
            while (eventQueue.size() >= max){
                try {
                    console("the queue is full, try later.");
                    eventQueue.wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

            console("a new event is submitted.");
            eventQueue.addLast(event);
            eventQueue.notifyAll();
        }
    }

    public EventQueueSupMulti.Event take(){
        synchronized (eventQueue){
            while (eventQueue.isEmpty()){
                try {
                    console("the queue is empty, try later.");
                    eventQueue.wait();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

            EventQueueSupMulti.Event e = eventQueue.removeFirst();
            eventQueue.notifyAll();
            console("the head event " + e + " of the queue is token.");

            return e;
        }
    }

    private void console(String msg){
        System.out.printf("%s: %s\n", Thread.currentThread().getName(), msg);
    }
}

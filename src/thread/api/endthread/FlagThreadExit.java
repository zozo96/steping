package api.endthread;

import java.util.concurrent.TimeUnit;

/**
 * [正常退出]
 * 通过volatile类型的flag开关来关闭线程
 * 但是volatile虽然保证了可见性，却不具备原子性
 *
 * @author Sophi
 * @since 2019/5/30
 **/
public class FlagThreadExit {
    public static void main(String[] args) throws InterruptedException {
        Task task = new Task();
        task.start();
        TimeUnit.SECONDS.sleep(2);
        System.out.println("main has already sleep for 2s, sys will be shut down");
        task.close();
    }

    static class Task extends Thread{
        private volatile boolean open = true;

        @Override
        public void run() {
            System.out.println("I'm running");
            while (open && !isInterrupted()){
                // do homework or other ..
            }
            System.out.println("I'm off-line");
        }

        public void close(){
            this.open = false;
            this.interrupt();
        }
    }


}

package base;

import java.util.concurrent.TimeUnit;

public class Demo1_ListenAndBrowse {
    public static int i = 0;

    public static void main(String[] args) {

//        new Thread(){
//            @Override
//            public void run() {
//                Demo1_ListenAndBrowse.listenToMusic();
//            }
//        };

        new Thread(Demo1_ListenAndBrowse::listenToMusic).start();
        Demo1_ListenAndBrowse.browseNews();
    }

    public static void listenToMusic() {
        while (i < 10) {
            System.out.println(i + " listen to the music");
            try {
                TimeUnit.SECONDS.sleep(1);
                i++;
            }catch (InterruptedException e){
                System.out.println("listenToMusic is interrupted");
            }

        }
    }

    public static void browseNews() {
        while (i < 10) {
            System.out.println(i + " browse the News");
            try {
                TimeUnit.SECONDS.sleep(1);
                i++;
            }catch (InterruptedException e){
                System.out.println("browseNews is interrupted");
            }
        }
    }
}

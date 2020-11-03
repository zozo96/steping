package thread.api;

import jdk.nashorn.internal.ir.debug.JSONWriter;
import jdk.nashorn.internal.parser.JSONParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * join会使当线程等待下去，除非被外部打断
 * 实现异步查询南航、东航、春秋的航线时间 origin - destination
 * 最后再将所有结果一同返回
 *
 * @author Sophi
 * @since 2019/5/25
 **/
public class ThreadJoin {

    private static List<String> companies = Arrays.asList("CSA", "CEA", "SA");
    private static List<String> result = new ArrayList<>();

    public static void main(String[] args) {
        long beginTime = System.currentTimeMillis();

        System.out.println("---------- Begin ----------");
        String origin = "Korea";
        String destination = "China";
        List<QueryTask> tasks = companies.stream().map(company -> new QueryTask(company, origin, destination)).collect(Collectors.toList());
        tasks.forEach(Thread::start);
        tasks.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e){
                System.out.println(t.getName() + " is interrupted");
            }
        });

        tasks.stream().map(QueryTask::query).forEach(result::addAll);

        long endTime = System.currentTimeMillis();
        System.out.println("---------- End ----------");
        System.out.printf("---------- Total %ss ---------- \n",(endTime - beginTime) / 1000);
    }

    interface QueryList{
        List<String> query();
    }

    static class QueryTask extends Thread implements QueryList{

        private List<String> airLines = new ArrayList<>();
        private String origin;
        private String destination;

        QueryTask(String airCompany, String origin, String destination){
            super("[" + airCompany + "]");
            this.origin = origin;
            this.destination = destination;
        }


        @Override
        public void run() {
            System.out.printf("%s is querying from %s to %s \n", getName(), origin, destination);
            int randomVal = ThreadLocalRandom.current().nextInt(10);
            try {
                TimeUnit.SECONDS.sleep(randomVal);
                airLines.add(getName() + "-" + randomVal);
                System.out.printf("%s - %ss \n", getName(), randomVal);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        @Override
        public List<String> query() {
            return this.airLines;
        }

    }
}




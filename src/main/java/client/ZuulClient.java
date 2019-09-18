package client;

import java.util.concurrent.*;

public class ZuulClient {
    public static void main ( String[] args ) {

        String uri = "http://localhost:8082/books/checked-out";
        String uri1="http://localhost:8083/service2/checked-out";

        ExecutorService es = Executors.newFixedThreadPool(50);


        for (int threadCount=0;threadCount<100000;threadCount++) {
           // es.execute(new GetCallerThread( uri ));
           es.execute(new GetCallerThread( uri1 ));

        }

        //   es.execute(new GetCallerThread( uri1 ));



        es.shutdown();


    }



}

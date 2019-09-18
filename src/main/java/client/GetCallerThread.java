package client;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.atomic.AtomicInteger;

public class GetCallerThread implements Runnable {

    static AtomicInteger counter = new AtomicInteger( 0 );
    static AtomicInteger successCounter = new AtomicInteger(  0);
    static AtomicInteger failedCounter = new AtomicInteger(  0);
    final String uri ;

    RestTemplate restTemplate = new RestTemplate();

    public GetCallerThread ( String uri ) {
        this.uri = uri;
    }

    @Override
    public void run ( ) {


        String response = null;
        try {


            response = restTemplate.getForObject( uri, String.class );
            successCounter.incrementAndGet();

            System.out.println( counter.incrementAndGet() + " status = "+"200");
        } catch (RestClientException e) {
            if(e instanceof HttpStatusCodeException){
                HttpStatus statusCode = ((HttpStatusCodeException) e).getStatusCode( );
                System.out.println(counter.incrementAndGet() +  " status = "+statusCode);
                failedCounter.incrementAndGet();
            }else {
                System.out.println( counter.incrementAndGet() +  " status = "+e.getClass().getName());
                failedCounter.incrementAndGet();
            }
        }

        System.out.println("success = " + successCounter.get() );
        System.out.println("failures = " + failedCounter.get() );
    }
}

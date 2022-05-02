package bus.artemrogov.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Task01 {

    @Scheduled(fixedDelay = 2000)
    public void printDisplay(){
        System.out.println("TASK 01"+ getClass().getName());
    }
}

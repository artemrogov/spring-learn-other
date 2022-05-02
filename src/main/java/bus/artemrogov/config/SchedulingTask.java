package bus.artemrogov.config;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulingTask {

    @Scheduled(fixedRate = 2000)
    public void reportCurrentTime(){
        System.out.println("MESSAGE SCHEDULE");
    }
}

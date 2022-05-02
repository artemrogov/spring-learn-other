package bus.artemrogov.scheduler;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
@EnableAsync
public class TaskAsync {

    @Async
    @Scheduled(fixedRate = 1000)
    public void printMessage(){
        System.out.println("Async Message");
    }
}

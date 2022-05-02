package bus.artemrogov.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Task02{

    @Scheduled(fixedDelay = 3000)
    public void printDisplay() {
        System.out.println("Task 02" + getClass().getName());
    }
}

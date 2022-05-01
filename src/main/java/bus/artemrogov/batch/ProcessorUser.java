package bus.artemrogov.batch;

import bus.artemrogov.entity.User;
import org.springframework.batch.item.ItemProcessor;

public class ProcessorUser implements ItemProcessor<User,User> {

    @Override
    public User process(User user) throws Exception {
        String firstName = user.getFirstName().toUpperCase();
        String lastName  = user.getLastName().toUpperCase();
        User userMutation = new User();
        userMutation.setFirstName(firstName);
        userMutation.setLastName(lastName);
        return userMutation;
    }
}

package bus.artemrogov.batch.user;

import bus.artemrogov.domain.entity.User;
import bus.artemrogov.domain.repository.UserRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DBWriter implements ItemWriter<User> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void write(List<? extends User> users) throws Exception {
        this.userRepository.saveAll(users);
    }
}

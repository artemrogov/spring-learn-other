package bus.artemrogov.domain.service;

import bus.artemrogov.domain.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import bus.artemrogov.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetailsProfile loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username).orElseThrow();
        return UserDetailsProfile.build(user);
    }
}

package bus.artemrogov.service;

import bus.artemrogov.entity.User;
import bus.artemrogov.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;


import java.util.Set;

@Service
public class UserAccountService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Validator validator;

    public String addUserAccount(User account) {

        Set<ConstraintViolation<User>> violations = validator.validate(account);

        if(!violations.isEmpty()){

            StringBuilder sb = new StringBuilder();

            for (ConstraintViolation<User> constraintViolation : violations){
                sb.append(constraintViolation.getMessage());
            }

            throw new ConstraintViolationException(
                    String.format("Error occurred: %s", sb),
                    violations
            );

        }

        this.userRepository.save(account);
        return "Account for " + account.getFirstName() + "Added!";
    }
}

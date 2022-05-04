package bus.artemrogov.controller;

import bus.artemrogov.entity.User;
import bus.artemrogov.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserAccountService userAccountService;

    @PostMapping(value = "/addUser")
    public ResponseEntity<String> addUser(@RequestBody User user){
       try{
            return new ResponseEntity<>(this.userAccountService.addUserAccount(user), HttpStatus.CREATED);
        }catch (ConstraintViolationException validationErrorOperation){
            return new ResponseEntity<>(validationErrorOperation.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping(value = "/create-profile")
    public ResponseEntity<User> createAccount(@RequestBody User user){
        return new ResponseEntity<>(this.userAccountService.createAccount(user), HttpStatus.CREATED);
    }
}

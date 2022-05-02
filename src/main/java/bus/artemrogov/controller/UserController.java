package bus.artemrogov.controller;


import bus.artemrogov.entity.User;
import bus.artemrogov.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserAccountService userAccountService;

    @PostMapping(value = "/addUser")
    public Object addUser(@RequestBody User user){
        try {
            return ResponseEntity.ok(this.userAccountService.addUserAccount(user));
        }catch (ConstraintViolationException eOperation){
            return ResponseEntity.status(422);
        }
    }

}

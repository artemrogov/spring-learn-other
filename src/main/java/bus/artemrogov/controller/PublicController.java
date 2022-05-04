package bus.artemrogov.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicController {

    @GetMapping(value = "/public-route")
    public Object getResponseOkRoute(){
        return new ResponseEntity<>(
                ResponseEntity.ok("Test").getBody(), HttpStatus.OK);
    }


    @GetMapping(value = "/sec-route")
    public Object getSecRoute(){
        return new ResponseEntity<>(
                ResponseEntity.ok("Sec Route !").getBody(), HttpStatus.OK);
    }
}

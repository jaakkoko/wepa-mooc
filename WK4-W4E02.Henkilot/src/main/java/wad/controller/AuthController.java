package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import wad.domain.Person;
import wad.repository.PersonRepository;

@RestController
@RequestMapping("/authenticate")
public class AuthController{
    
    @Autowired
    PersonRepository personRepository;
    
    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<String> checkAuth(@RequestBody Person person){
        return new ResponseEntity<>("moro",HttpStatus.OK);
    }
    
}
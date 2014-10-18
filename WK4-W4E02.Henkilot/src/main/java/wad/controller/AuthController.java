package wad.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import wad.domain.Person;
import wad.service.PersonService;

@RestController
@RequestMapping("/authenticate")
public class AuthController{
    
    @Autowired
    PersonService personService;
    
    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<String> checkAuth(@RequestBody Person person){
        List<Person> users = personService.list();
        for(Person user:users){
            if (user.getUsername().equals(person.getUsername())){
                if(user.getPassword().equals(person.getPassword())){
                    return new ResponseEntity<>(user.getName(),HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        
        
    }
    
}
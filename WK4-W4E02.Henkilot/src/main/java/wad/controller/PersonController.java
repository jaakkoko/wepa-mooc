package wad.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import wad.domain.Person;
import wad.repository.PersonRepository;

@RestController
@RequestMapping("/persons")
public class PersonController{

    @Autowired
    PersonRepository personRepository;
    
    @RequestMapping(method=RequestMethod.POST)
    public void addPerson(@RequestBody Person person){
        personRepository.save(person);
    }
    
    @RequestMapping(method=RequestMethod.GET)
    public List<Person> getPersons(){
        return personRepository.findAll();
    }
    
    @RequestMapping(value="/{id}",method=RequestMethod.GET)
    public Person getPerson(@PathVariable long id){
        return personRepository.findOne(id);
    }
    
    
}
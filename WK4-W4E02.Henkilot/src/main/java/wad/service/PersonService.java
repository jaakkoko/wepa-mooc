package wad.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wad.domain.Person;
import wad.repository.PersonRepository;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;
    
    public List<Person> list() {

        return personRepository.findAll();
    }

    @Transactional
    public void addPerson(Person person) {
        personRepository.save(person);
    }
    
    public Person getPerson(long id){
        return personRepository.findOne(id);
    }
}

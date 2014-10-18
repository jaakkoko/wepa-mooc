package wad.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import wad.domain.Apartment;
import wad.repository.ApartmentRepository;

@RestController
@RequestMapping("/apartments")
public class ApartmentController{

    @Autowired
    ApartmentRepository apartmentRepository;
    
    @RequestMapping(method=RequestMethod.POST)
    public void addApartment(@RequestBody String name){
        Apartment apartment = new Apartment();
        apartment.setName(name);
        apartmentRepository.save(apartment);
    }
    
    @RequestMapping(method=RequestMethod.GET)
    public List<Apartment> getApartments(){
        return apartmentRepository.findAll();
    }
    
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public Apartment getApartment(@PathVariable long id){
        return apartmentRepository.findOne(id);
    }
    
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public void deleteApartment(@PathVariable long id){
        apartmentRepository.delete(id);
    }
    
}
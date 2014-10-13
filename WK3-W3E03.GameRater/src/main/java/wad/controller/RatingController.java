package wad.controller;

import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import wad.domain.Rating;
import wad.repository.RatingRepository;
import wad.service.GameRestClient;

@RestController
@RequestMapping("games")
public class RatingController{

    @Autowired
    RatingRepository ratingRepository;
        
    @RequestMapping(value="/{name}/ratings",method=RequestMethod.POST)
    public void addRating(@PathVariable String name,@RequestBody Rating rating){
        rating.setGameName(name);
        ratingRepository.save(rating);
    }
    
    @RequestMapping(value="/{name}/ratings",method=RequestMethod.GET)
    @ResponseBody
    public List<Rating> getRatings(@PathVariable String name){
        return ratingRepository.findByGameName(name);
    }
    
    
    

}
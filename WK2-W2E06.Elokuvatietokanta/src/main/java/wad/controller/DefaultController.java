package wad.controller;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import wad.domain.Actor;
import wad.domain.Movie;
import wad.repository.ActorRepository;
import wad.repository.MovieRepository;

@Controller
public class DefaultController {

    @Autowired
    ActorRepository actorRepository;
    @Autowired
    MovieRepository movieRepository;
    
    @RequestMapping("*")
    public String handleDefault() {
        return "/WEB-INF/views/menu.jsp";
    }
    
    @RequestMapping(value= "/actors",method=RequestMethod.GET)
    public String getActors(Model model){
        model.addAttribute("actors", actorRepository.findAll());
        return "/WEB-INF/views/actors.jsp";
    }
    
    @RequestMapping(value= "/movies",method=RequestMethod.GET)
    public String getMovies(Model model){
        model.addAttribute("movies", movieRepository.findAll());
        return "/WEB-INF/views/movies.jsp";
    }
    
    @RequestMapping(value= "/actors",method=RequestMethod.POST)
    public String addActors(@RequestParam String name){
        if(!name.isEmpty()){
            Actor actor = new Actor();
            actor.setName(name);
            actorRepository.save(actor);
        }
        return "redirect:/actors";
    }
    
    @RequestMapping(value= "/movies",method=RequestMethod.POST)
    public String addMovies(@RequestParam String name,@RequestParam int lengthInMinutes){
        if(!name.isEmpty()){
            Movie movie = new Movie();
            movie.setName(name);
            movie.setLengthInMinutes(lengthInMinutes);
            movieRepository.save(movie);
        }
        return "redirect:/movies";
    }
    
    @Transactional
    @RequestMapping(value= "/actors/{actorId}",method=RequestMethod.DELETE)
    public String deleteActor(@PathVariable long actorId){
        actorRepository.delete(actorId);
        return "redirect:/actors";
    }
    
    @Transactional
    @RequestMapping(value= "/movies/{movieId}",method=RequestMethod.DELETE)
    public String deleteMovie(@PathVariable long movieId){
        movieRepository.delete(movieId);
        return "redirect:/movies";
    }
    
    @RequestMapping(value="/actors/{actorId}",method=RequestMethod.GET)
    public String getActorsMovies(Model model,@PathVariable long actorId){
        
        Actor actor = actorRepository.getOne(actorId);
        model.addAttribute("actor",actor);
        model.addAttribute("movies",actor.getMovies());
            
        return "/WEB-INF/views/actor.jsp";
    }
     
    @Transactional
    @RequestMapping(value="/actors/{actorId}/movies",method=RequestMethod.POST)
    public String addMovieToActor(@PathVariable long actorId, @RequestParam long movieId){
        Actor actor = actorRepository.findOne(actorId);
        Movie movie = movieRepository.findOne(movieId);
        List<Movie> movies = actor.getMovies();
        List<Actor> actors = movie.getActors();
        movies.add(movie);
        actors.add(actor);
        movie.setActors(actors);
        actor.setMovies(movies);
        
        return "redirect:/actors";
    }
    
    
    
    
    
    
    
}

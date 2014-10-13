package wad.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import wad.domain.Game;
import wad.repository.GameRepository;

@RestController
@RequestMapping("games")
public class GameController{
    
    @Autowired
    GameRepository gameRepository;
    
    @RequestMapping(method=RequestMethod.POST)
    @ResponseBody
    public String create(@RequestBody Game game, RedirectAttributes redir){
        gameRepository.save(game);
        redir.addAttribute("name", game.getName());
        return "redirect:/{name}";
    }
    
    @RequestMapping(method=RequestMethod.GET)
    @ResponseBody
    public List<Game> getGames(){
        return gameRepository.findAll();
    }
    
    @RequestMapping(value="/{name}",method=RequestMethod.GET)
    @ResponseBody
    public Game getGame(@PathVariable String name){
        return gameRepository.findByName(name);
    }
    
    @RequestMapping(value="/{name}",method=RequestMethod.DELETE)
    @ResponseBody
    public Game delete(@PathVariable String name){
        Game game = gameRepository.findByName(name);
        gameRepository.delete(game);
        return game;
    } 
}


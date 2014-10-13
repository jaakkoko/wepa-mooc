package wad.controller;

import java.util.Collection;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import wad.domain.Game;
import wad.service.GameRestClient;



@RestController
@RequestMapping("games")
public class GameController{
    
    @Autowired
    GameRestClient client;
    private String uri = "http://wepa-scoreservice-heroku.herokuapp.com/games";
    
    @PostConstruct
    private void setUri(){
        client.setUri(uri);
    }
    
    @RequestMapping(method=RequestMethod.POST)
    @ResponseBody
    public Game create(@RequestBody Game game, RedirectAttributes redir){
        return client.create(game);
    }
    
    @RequestMapping(method=RequestMethod.GET)
    @ResponseBody
    public Collection<Game> getGames(){
        return client.findAll();
    }
    
    @RequestMapping(value="/{name}",method=RequestMethod.GET)
    @ResponseBody
    public Game getGame(@PathVariable String name){
    
        return client.findByName(name);
    }
    
    @RequestMapping(value="/{name}",method=RequestMethod.DELETE)
    @ResponseBody
    public Game delete(@PathVariable String name){
        Game game = client.findByName(name);
        client.deleteByName(name);
        return game;
    } 
}

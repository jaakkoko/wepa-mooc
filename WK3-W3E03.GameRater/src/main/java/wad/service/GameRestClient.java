package wad.service;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import wad.domain.Game;

@Service
public class GameRestClient implements GameService{

    
    RestTemplate restTemplate;
    private String uri;
    
    public GameRestClient(){
        this.restTemplate = new RestTemplate();
    }
    
    @Override
    public void setUri(String uri){
        this.uri = uri;
    }

    @Override
    public Game create(Game game){
        return restTemplate.postForObject(uri, game, Game.class);
    }

    @Override
    public Game findByName(String name){
        return restTemplate.getForObject(uri + "/{name}", Game.class, name);
    }

    @Override
    public void deleteByName(String name){
        restTemplate.delete(uri + "/{name}", name);
    }

    @Override
    public Collection<Game> findAll(){
        return restTemplate.getForObject(uri, Collection.class);
    }
    
}
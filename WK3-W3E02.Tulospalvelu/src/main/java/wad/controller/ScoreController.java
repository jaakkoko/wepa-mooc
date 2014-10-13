package wad.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import wad.domain.Game;
import wad.domain.Score;
import wad.repository.GameRepository;
import wad.repository.ScoreRepository;

@RestController
@RequestMapping("games")
public class ScoreController{

    @Autowired
    ScoreRepository scoreRepository;
    @Autowired
    GameRepository gameRepository;
    
    @RequestMapping(value="/{name}/scores",method=RequestMethod.POST)
    @ResponseBody
    public Score create(@PathVariable String name, @RequestBody Score score){
        Game game = gameRepository.findByName(name);
        score.setGame(game);
        scoreRepository.save(score);
        return score;
    }
    
    @RequestMapping(value="/{name}/scores",method=RequestMethod.GET)
    @ResponseBody
    public List<Score> showScores(@PathVariable String name){
        Game game = gameRepository.findByName(name);
        return scoreRepository.findByGame(game);
    }
    
    @RequestMapping(value="/{name}/scores/{id}",method=RequestMethod.GET)
    @ResponseBody
    public Score showScore(@PathVariable String name,@PathVariable long id){
        Game game = gameRepository.findByName(name);
        return scoreRepository.findByGameAndId(game, id);
    }
    
    @RequestMapping(value="/{name}/scores/{id}",method=RequestMethod.DELETE)
    
    public Score deleteScore(@PathVariable String name,@PathVariable long id){
        Game game = gameRepository.findByName(name);
        Score score = scoreRepository.findByGameAndId(game, id);
        scoreRepository.delete(score);
        return score;
    }
    
    
    
}
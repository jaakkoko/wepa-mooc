package wad.controller;

import wad.domain.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import wad.service.LoveService;


@Controller
@RequestMapping("/loves")
public class LoveController {

    @Autowired
    private LoveService loveService;

    @RequestMapping(method=RequestMethod.POST)
    public String createPair(String nameOne, String nameTwo, RedirectAttributes red){
    
        Pair pair = this.getPair();
        pair.setNameOne(nameOne);
        pair.setNameTwo(nameTwo);
        int match = loveService.countMatch(pair);
        red.addFlashAttribute("match",match);
        red.addFlashAttribute("pair", pair);
        
        return "redirect:/loves";
        
    }
    
    @ModelAttribute("pair")
    private Pair getPair() {
        return new Pair();
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String get(){
        return "/WEB-INF/views/loves.jsp";
    } 
    
  
}

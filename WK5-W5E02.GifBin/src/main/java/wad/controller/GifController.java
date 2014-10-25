package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/gifs")
public class GifController {

    @Autowired
    //GifRepository gifRepository;
    
    @RequestMapping(method = RequestMethod.GET)
    public String redirect() {
        return "redirect:/gifs/1";
    }
    
    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    public String getGifById(Model model,@PathVariable("id") long id) {
        //model.addAttribute("count")
        return "redirect:/gifs/1";
    }
}

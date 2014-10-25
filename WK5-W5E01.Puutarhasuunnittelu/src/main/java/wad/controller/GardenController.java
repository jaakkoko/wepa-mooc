package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wad.repository.GardenRepository;

@Controller
@RequestMapping("/gardens")
public class GardenController {

    @Autowired
    GardenRepository gaRepo;
    
    @RequestMapping(method = RequestMethod.GET)
    public String getGardens(Model model) {
        model.addAttribute("gardens", gaRepo.findAll());        
        return "gardens";
    }
    
    
}

package wad.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wad.domain.Vegetable;
import wad.repository.VegetableRepository;

@Controller
@RequestMapping("/vegetables")
public class VegetableController {

    @Autowired
    private VegetableRepository vgRepo;
    
    
    @ModelAttribute("vegetable")
    public Vegetable vegetable(){
        return new Vegetable();
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String getVegetables(Model model) {
        model.addAttribute("message", "Vegetables!");
        model.addAttribute("vegetables",vgRepo.findAll());
        model.addAttribute("vegetable");
        return "vegetables";
    }
        
    @RequestMapping(method = RequestMethod.POST)
    public String addVegetables(@Valid @ModelAttribute Vegetable vegetable,
            BindingResult res){
        if(res.hasErrors()) return "vegetables";
        else{            
            vgRepo.save(vegetable);
            return "redirect:/vegetables";
        }
    }
}

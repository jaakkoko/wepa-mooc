package wad.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wad.domain.Garden;
import wad.repository.GardenRepository;
import wad.repository.VegetableRepository;

@Controller
@RequestMapping("/gardens")
public class GardenController {

    @Autowired
    GardenRepository gaRepo;
    
    @Autowired
    VegetableRepository vgRepo;
    
    @ModelAttribute("garden")
    public Garden vegetable(){
        return new Garden();
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String getGardens(Model model) {
        model.addAttribute("gardens", gaRepo.findAll());
        model.addAttribute("vegetables",vgRepo.findAll());
        return "gardens";
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String addGarden(@Valid @ModelAttribute Garden garden,
            BindingResult res){
        if(res.hasErrors())return "gardens";
        else{
            gaRepo.save(garden);
            return "redirect:/gardens";
        }
    }
    
    @Transactional
    @RequestMapping(value="/{id}/vegetables",method=RequestMethod.POST)
    public String addVegetable(@ModelAttribute("vegetableId") long vegetableId,
            @PathVariable("id") long id){  
        
        gaRepo.findOne(id).getVegetables().add(vgRepo.findOne(vegetableId));
        return "redirect:/gardens";
        
    }
    
}

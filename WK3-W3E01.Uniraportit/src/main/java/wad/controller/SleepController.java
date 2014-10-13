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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import wad.domain.Sleep;
import wad.repository.SleepRepository;

@Controller
@RequestMapping("/sleeps")
public class SleepController{

    @Autowired
    SleepRepository sleepRepository;
    
    @ModelAttribute("sleep")
    private Sleep getSleep(){
        return new Sleep();
    }
    
    @RequestMapping(method=RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("sleeps",sleepRepository.findAll());
        return "/WEB-INF/views/sleeps.jsp";
    }
    
    @Transactional
    @RequestMapping(method=RequestMethod.POST)
    public String create(RedirectAttributes redirectAttrs, @Valid @ModelAttribute Sleep sleep, BindingResult result){
        if (result.hasErrors()){
            return "/WEB-INF/views/sleeps.jsp";
        }else{
            sleepRepository.save(sleep);
            redirectAttrs.addAttribute("id",sleep.getId());
            return "redirect:/{id}";
        }
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public String read(Model model, @PathVariable Long id){
        model.addAttribute("sleep",sleepRepository.findOne(id));
        return "/WEB-INF/views/sleep.jsp";
    }
    
    @Transactional
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public String delete(@PathVariable long id){
        sleepRepository.delete(id);
        return "redirect:/sleeps";
    }
}
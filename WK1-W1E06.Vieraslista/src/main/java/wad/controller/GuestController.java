package wad.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wad.domain.Guest;

@Controller
@RequestMapping("/guests")
public class GuestController {

    private final List<Guest> guests = new ArrayList<>();

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("guests", guests);
        return "/WEB-INF/views/page.jsp";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addGuest(@ModelAttribute Guest guest){
        if (guest.getName() != null){
            if (!guest.getName().trim().isEmpty()) guests.add(guest);
        }
        
        
        return "redirect:/guests";
    }
    
    @RequestMapping(value = "/{id}/delete",  method = RequestMethod.POST)
    public String deleteGuest(@PathVariable String id){
       Guest guest = getGuestById(id);
       if (guest != null) guests.remove(guest);
       return "redirect:/guests";
    }
    
    public Guest getGuestById(String id){
        for(Guest guest : guests){
            if (guest.getId().equals(id)) return guest;
        }
        return null;
    }
}

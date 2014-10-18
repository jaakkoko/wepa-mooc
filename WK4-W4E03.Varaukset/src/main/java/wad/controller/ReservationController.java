package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import wad.ext.apartments.ApartmentService;
import wad.repository.ReservationRepository;

@RestController
@RequestMapping("/reservations")
public class ReservationController{
    
    @Autowired
    ApartmentService apartmentService;
    
    @Autowired
    ReservationRepository reservationRepository;
    
    @RequestMapping(method=RequestMethod.GET)
    public String getReservations(Model model){
        model.addAttribute("apartments", apartmentService.list());
        model.addAttribute("reservations", reservationRepository.findAll());
        return "/WEB-INF/views/reservations.jsp";
    }
    
}

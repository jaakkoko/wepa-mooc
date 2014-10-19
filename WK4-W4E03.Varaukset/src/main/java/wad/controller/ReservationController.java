package wad.controller;

import java.util.Date;
import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wad.domain.Reservation;
import wad.ext.apartments.ApartmentService;
import wad.repository.ReservationRepository;

@Controller
@RequestMapping("/reservations")
public class ReservationController{
    
    @Autowired
    ApartmentService apartmentService;
    
    @Autowired
    ReservationRepository reservationRepository;
    
    @PostConstruct
    public void setUri(){
        apartmentService.setUri("http://localhost:8069/api");
    }
    
    @RequestMapping(method=RequestMethod.GET)
    public String getReservations(Model model){
        System.out.println("getReservations");
        model.addAttribute("apartments", apartmentService.list());        
        model.addAttribute("reservations", reservationRepository.findAll());
        return "/WEB-INF/views/reservations.jsp";
    }
    
  
    @Transactional
    @RequestMapping(method=RequestMethod.POST)    
    public String addReservation(@ModelAttribute Reservation reservation){
        boolean reserved = false;
        for(Reservation res : reservationRepository.findAll()){
            if (res.getApartmentId().equals(reservation.getApartmentId())){
                Date start = res.getReservationStart();
                Date end = res.getReservationEnd();
                if (reservation.getReservationStart().after(start) && reservation.getReservationStart().before(end)) reserved = true;
                if (reservation.getReservationEnd().after(start) && reservation.getReservationEnd().before(end)) reserved = true;
                if (reservation.getReservationStart().before(start) && reservation.getReservationEnd().before(end)) reserved = true;
                if (reservation.getReservationStart().before(start) && reservation.getReservationEnd().after(end)) reserved = true;
                if (reservation.getReservationStart().after(start) && reservation.getReservationEnd().before(end)) reserved = true;
                if (reservation.getReservationStart().equals(start)) reserved = true;
            }
        }
        if(!reserved){
            reservation.setApartment(apartmentService.get(reservation.getApartmentId()).getName());
            reservationRepository.save(reservation);
        }
        return "redirect:/reservation";
    }
    
    @Transactional
    @RequestMapping(value="/{id}/payment",method=RequestMethod.POST)
    public String pay(@PathVariable long id){
        reservationRepository.findOne(id).setPaymentStatus("PAID");
        return "redirect:/reservation";
    }
   
}

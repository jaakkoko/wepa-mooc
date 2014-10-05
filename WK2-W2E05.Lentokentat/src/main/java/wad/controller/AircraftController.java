package wad.controller;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import wad.domain.Aircraft;
import wad.domain.Airport;
import wad.repository.AircraftRepository;
import wad.repository.AirportRepository;

@Controller
@RequestMapping("aircrafts")
public class AircraftController {

    @Autowired
    private AircraftRepository aircraftRepository;
    @Autowired
    private AirportRepository airportRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("aircrafts", aircraftRepository.findAll());
        model.addAttribute("airports", airportRepository.findAll());

        return "/WEB-INF/views/aircrafts.jsp";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create(@ModelAttribute Aircraft aircraft) {
        aircraftRepository.save(aircraft);
        return "redirect:/aircrafts";
    }
    
    @Transactional
    @RequestMapping(value="/{aircraftId}/airports",method = RequestMethod.POST)
    public String assignAirport(@PathVariable long aircraftId,@RequestParam long airportId){
            Aircraft aircraft = aircraftRepository.findOne(aircraftId);
            Airport airport = airportRepository.findOne(airportId);
            aircraft.setAirport(airport);
            airport.getAircrafts().add(aircraft);
            
            
            return "redirect:/aircrafts";
    }

}

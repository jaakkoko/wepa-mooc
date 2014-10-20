package wad.service;


import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import wad.Application;
import wad.domain.Aircraft;
import wad.domain.Airport;
import wad.repository.AircraftRepository;
import wad.repository.AirportRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class AirportServiceTest{

    @Autowired
    private AirportService apService;
    
    @Autowired
    private AircraftRepository acRepository;
    
    @Autowired
    private AirportRepository apRepository;
    
    private void createAircraft(String name){
        Aircraft ac = new Aircraft();
        ac.setName(name);
        acRepository.save(ac);
    }
    
    private void createAirport(String name){
        Airport ap = new Airport();
        ap.setName(name);
        apRepository.save(ap);        
    }
   
    @Test
    public void addAircraftToAirport() throws Exception{
        createAircraft("Testcraft");
        createAirport("Testport");
        
        apService.assignAirport(1L,1L);
                
        Airport res = acRepository.findOne(1L).getAirport();
        assertNotNull(res);
        assertEquals("Testport",res.getName());
        
    }
    
    @Test
    public void airportHasAircraft() throws Exception{
        createAircraft("Testcraft");
        createAirport("Testport");
        
        apService.assignAirport(1L, 1L);
        
        Aircraft res = apRepository.findOne(1L).getAircrafts().get(0);
        assertNotNull(res);
        assertEquals("Testcraft",res.getName());
    }
    
    @Test
    public void aircraftHasOnlyOneAirport() throws Exception{
        createAircraft("Testcraft");
        createAirport("Testport");
        createAirport("Testport");
        
        apService.assignAirport(1L, 1L);
        apService.assignAirport(1L, 2L);
        
        List<Aircraft>aircraftsFound = new ArrayList<>();
        for (Airport ap : apRepository.findAll()){
            for(Aircraft ac : ap.getAircrafts()){
               aircraftsFound.add(ac);
            }
        }
        
        assertNotNull(aircraftsFound);
        assertEquals(1,aircraftsFound.size());
    }
    
    @Test
    public void cantAddSameAircraft() throws Exception{
        createAircraft("Testcraft");
        createAirport("Testfield");
        
        apService.assignAirport(1L, 1L);
        apService.assignAirport(1L, 1L);
        apService.assignAirport(1L, 1L);
        
        Airport airport = acRepository.findOne(1L).getAirport();
        int res = airport.getAircrafts().size();
        
        assertEquals(1,res);
    }
    
     
}
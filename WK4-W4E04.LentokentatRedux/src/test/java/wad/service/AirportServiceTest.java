package wad.service;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import wad.Application;
import wad.domain.Aircraft;
import wad.domain.Airport;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class AirportServiceTest{

    private final String AIRPORT_URI = "/airports";
    private final String AIRCRAFT_URI = "/aircrafts";

    @Autowired
    private WebApplicationContext webAppContext;

    private MockMvc mockMvc;
    
    @Before
    public void setUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
    }
    
    @Test
    public void statusOK() throws Exception{
        mockMvc.perform(get(AIRPORT_URI)).andExpect(status().isOk());
    }
    
    @Test
    public void addAirport(){
        Airport ap = new Airport();
        
                
    }
    
    @Test
    public void addAircraft(){
        Aircraft ac = new Aircraft();
        
        
        
    }
    
}
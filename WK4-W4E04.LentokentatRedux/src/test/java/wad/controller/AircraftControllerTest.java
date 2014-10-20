package wad.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import wad.Application;
import wad.domain.Aircraft;
import wad.repository.AircraftRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class AircraftControllerTest{

    private final String API_URI = "/aircrafts";

    @Autowired
    private WebApplicationContext webAppContext;
    
    @Autowired
    private AircraftRepository aircraftRepository;

    private MockMvc mockMvc;
    
    
    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
    }

    @Test
    public void getAircrafts() throws Exception {
        mockMvc.perform(get(API_URI))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("aircrafts"))
                .andExpect(model().attributeExists("airports"));
    }
   
    @Test
    public void postAircraft() throws Exception {
        mockMvc.perform(post(API_URI)
                .param("name","HA-LOL"))
                .andExpect(status().is3xxRedirection());
        
        Aircraft ac = aircraftRepository.findOne(1L);
        assertNotNull(ac);
        assertEquals("HA-LOL",ac.getName());
        
    }
 
}
package wad.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import wad.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import wad.service.MessageService;



@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @ModelAttribute("message")
    private Message getMessage() {
        return new Message();
    }
    

    @RequestMapping(method = RequestMethod.GET,produces = "application/hal+json;")
    public List<Message> list() {
        //model.addAttribute("messages", messageService.list());
        
        return messageService.list();
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public void create(@RequestBody String message) {
        ObjectMapper mapper = new ObjectMapper();
        try{
            Message msg = mapper.readValue(message, Message.class);
            messageService.addMessage(msg);
        }catch (IOException e){
            System.out.println(e);
        }
       
        
    }
}

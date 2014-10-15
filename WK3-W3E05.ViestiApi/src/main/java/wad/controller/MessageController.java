package wad.controller;

import java.util.List;
import javax.validation.Valid;
import wad.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import wad.service.MessageService;

@Controller
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @ModelAttribute("message")
    private Message getMessage() {
        return new Message();
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Message> list() {
        //model.addAttribute("messages", messageService.list());
        
        return messageService.list();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void create(@Valid Message message,
            BindingResult result) {
        if (!result.hasErrors()) {
            messageService.addMessage(message);
        }
        
    }
}

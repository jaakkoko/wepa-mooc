package wad.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FormController {

    private String inMemory = "nothing..";

    @RequestMapping("*")
    // BEGIN STUB
    public String view(Model model, @RequestParam(required=false, defaultValue="") String data) {
        
        model.addAttribute("data", inMemory);
        if (!data.isEmpty()) inMemory = data;
        return "/WEB-INF/views/page.jsp";
    }
    
    // END STUB
}

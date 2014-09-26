package wad.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class VisitCountController {
    @RequestMapping("/tervehdi")
    public String hello(@RequestParam String nimi) {
        return "Hei " + nimi;
    }
}

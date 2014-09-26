package wad.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class VisitCountController {
    int visitCount = 0;
    @RequestMapping("/visits")
    public int Visits(){
        this.visitCount++;
        return this.visitCount;
    }
}

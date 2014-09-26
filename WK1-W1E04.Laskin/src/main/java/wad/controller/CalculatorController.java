package wad.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class CalculatorController {

    @RequestMapping("/calculate")
    public int Calculate(@RequestParam(defaultValue="plus") String op,
            @RequestParam(defaultValue="0") int first,
            @RequestParam(defaultValue="0") int second){
        
        if(op.contentEquals("minus"))return first-second;
        if(op.contentEquals("multiply")) return first*second;
        return first+second;
    }

}

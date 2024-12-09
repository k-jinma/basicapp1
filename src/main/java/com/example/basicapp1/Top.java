package com.example.basicapp1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class Top {

    @GetMapping("top")
    public String showTop(){
        return "top";
    }

    @GetMapping("calc")
    public String calculation(@RequestParam("num1") int num1, @RequestParam("num2") int num2, Model model){
        System.out.println( num1 + num2 );

        model.addAttribute("result", num1 + num2);
        
        return "result";
    }
    
}

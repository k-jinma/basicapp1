package com.example.basicapp1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/")
public class Top {

    @Autowired
    CustomerMapper customerMapper;

    @GetMapping("top")
    public String showTop() {

        CustomerEntity customer = customerMapper.getCustomerById(1);
        System.out.println(customer.getId());
        System.out.println(customer.getName());
        System.out.println(customer.getAddress());

        // ビューでの表示実装は省略
        return "top";
    }
    

}

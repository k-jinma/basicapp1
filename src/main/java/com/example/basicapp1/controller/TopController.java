package com.example.basicapp1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.basicapp1.entity.CustomerEntity;
import com.example.basicapp1.entity.HistoryEntity;
import com.example.basicapp1.entity.ItemEntity;
import com.example.basicapp1.mapper.CustomerMapper;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/")
public class TopController {

    @Autowired
    CustomerMapper customerMapper;

    @GetMapping("top")
    public String showTop(Model model) {

        CustomerEntity customer = customerMapper.getCustomerById(1);
        System.out.println(customer.getId());
        System.out.println(customer.getName());
        System.out.println(customer.getAddress());

        List<ItemEntity> items = customerMapper.getAllItems();
        for (ItemEntity item : items) {
            System.out.println(item.getId());
            System.out.println(item.getName());
            System.out.println(item.getPrice());
        }
        model.addAttribute("itemList", items);

        HistoryEntity history = customerMapper.getHistoryById(4);
        if( history == null ){
            model.addAttribute("message", "購入履歴がありません");
            return "top";
        }
        model.addAttribute("history", history);

        // ビューでの表示実装は省略
        return "top";
    }
}

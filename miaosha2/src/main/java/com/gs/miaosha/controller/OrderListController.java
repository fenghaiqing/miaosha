package com.gs.miaosha.controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import com.gs.miaosha.entity.User;


@Controller
@RequestMapping("/order")
public class OrderListController {

	
	@RequestMapping("/list")
	public String toOrderList(Model model,User user) {
		
		model.addAttribute("user", user);
		return "order_list";
	}
}

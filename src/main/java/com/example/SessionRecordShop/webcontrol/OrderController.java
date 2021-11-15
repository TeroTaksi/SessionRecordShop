package com.example.SessionRecordShop.webcontrol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.SessionRecordShop.domain.OrderRepository;

public class OrderController {
	
	@Autowired
	private OrderRepository orderRepository;
	
	// LIST shopping cart
	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public String listOrder(Model model) {
		model.addAttribute("orders", orderRepository.findAll());
		return "orders"; //.html
		
	}
}

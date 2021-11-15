package com.example.SessionRecordShop.webcontrol;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.SessionRecordShop.domain.MyOrder;
import com.example.SessionRecordShop.domain.OrderRepository;
import com.example.SessionRecordShop.domain.ShopCartItem;


@Controller
public class OrderController {
	
	@Autowired
	private OrderRepository orderRepository;
	
	// LIST shopping cart
	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public String listOrder(Model model) {
		model.addAttribute("orders", orderRepository.findAll());
		return "orders"; //.html	
	}
	
	// LIST Order details --> ordered records
	@RequestMapping(value = "/order/{id}", method = RequestMethod.GET)
	public String orderDetails(@PathVariable("id") Long orderId, Optional<MyOrder> myOrder, Model model) {	
		myOrder = orderRepository.findById(orderId);
		MyOrder order = myOrder.get();
		model.addAttribute("orderDetails", order.getShopCartItemList());
		
		double sum = 0;
		for(ShopCartItem item : order.getShopCartItemList()) {
			sum += item.getTotalCost();
		}
		model.addAttribute("sum", sum);

		return "orderdetails"; //.html	
	}
	
	// Repository Proggiksesta
//	@RequestMapping(value = "/plusone/{id}", method = RequestMethod.GET)
//	public String addRecordQuantity(@PathVariable("id") Long itemId, Optional<MyOrder> myOrder) {		
//		item = shopCartItemRepository.findById(itemId);
//		ShopCartItem shopCartItem = item.get();
//		
//		int quantity = shopCartItem.getQuantity();
//		shopCartItem.setQuantity(quantity = quantity + 1);
//		shopCartItem.setTotalCost(shopCartItem.getQuantity() * shopCartItem.getRecord().getPrice());
//		shopCartItemRepository.save(shopCartItem);
//
//		return "redirect:/shoppingcart";
//	}
	
}

package com.example.SessionRecordShop.webcontrol;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.SessionRecordShop.domain.MyOrder;
import com.example.SessionRecordShop.domain.OrderRepository;
import com.example.SessionRecordShop.domain.ShopCartItem;


@Controller
public class OrderController {
	
	@Autowired
	private OrderRepository orderRepository;
	
	// LIST MyOrders
	@RequestMapping(value = "/orderlist", method = RequestMethod.GET)
	public String listOrder(Model model) {
		model.addAttribute("orders", orderRepository.findAll());
		return "orders"; //.html	
	}
	
	// LIST MyOrder details --> ordered records & sum of order
	@RequestMapping(value = "/order/{id}", method = RequestMethod.GET)
	public String orderDetails(@PathVariable("id") Long orderId, Optional<MyOrder> myOrder, Model model) {
		// Optional<MyOrder> myOrder
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
	
	// DELETE MyOrder by id
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/deleteorder/{id}", method = RequestMethod.GET)
	public String deleteRecord(@PathVariable("id") Long orderId, Model model) {
		orderRepository.deleteById(orderId);
		return "redirect:../orderlist";
	}
	
	//------------- R E S T -----------------------------------------------------
	
	// REST - LIST all MyOrder (localhost:8080/orders) - @ResponseBody: List<MyOrder> --> JSON
	@RequestMapping(value="/orders", method = RequestMethod.GET)
	public @ResponseBody List<MyOrder> yyOrderListRest() {
		// return: Iterable<T> to List<ShopCartItem>
		return (List<MyOrder>) orderRepository.findAll(); 
	}
	
	// REST - get MyOrder by id
	@RequestMapping(value="/orders/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<MyOrder> findMyOrderByIdRest(@PathVariable("id") Long myOrderId) {
		return orderRepository.findById(myOrderId);
	}
	
	// REST - SAVE MyOrder
	@RequestMapping(value="/orders", method = RequestMethod.POST)
	public @ResponseBody MyOrder saveMyOrderRest(@RequestBody MyOrder myOrder) {
		return orderRepository.save(myOrder);
	}
}

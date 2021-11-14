// http://localhost:8080/recordlist
package com.example.SessionRecordShop.webcontrol;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.SessionRecordShop.domain.Record;
import com.example.SessionRecordShop.domain.RecordRepository;
import com.example.SessionRecordShop.domain.ShopCartItem;
//import com.example.SessionRecordShop.domain.ShopCartItemRepository;



@Controller
public class ShopCartItemController { //----------- S E S S I O N -------------------------------------
	
//	@Autowired
//	private ShopCartItemRepository shopCartItemRepository;
	
	@Autowired
	private RecordRepository recordRepository;
	
	@Autowired
	private HttpSession session;
	
	
	
	// LIST shopping cart
	@RequestMapping(value = "/shoppingcart", method = RequestMethod.GET)
	public String addCart(Model model) {
		@SuppressWarnings("unchecked")
		List<ShopCartItem> cartItems = (List<ShopCartItem>) session.getAttribute("cartItems");
		
		if(cartItems != null) {
			double sum = 0;
			for(ShopCartItem item :cartItems) {
				sum += item.getTotalCost();
			}
			model.addAttribute("sum", sum);
			model.addAttribute("cartItems", cartItems);
			return "shoppingcart"; // .html
		}

		model.addAttribute("sum", 0.00);
		return "shoppingcart"; // .html		
	}
	
	// ADD record to shopping cart by id -> shopCartItemRepository
	@RequestMapping(value = "/addcart/{id}", method = RequestMethod.GET)
	public String addCart(@PathVariable("id") Long recordId, Model model) {	
		
		@SuppressWarnings("unchecked")	
		List<ShopCartItem> cartItems = (List<ShopCartItem>) session.getAttribute("cartItems");
		ArrayList<Record> records = (ArrayList<Record>) recordRepository.findAll();
		
		if(cartItems == null) {
			cartItems = new ArrayList<ShopCartItem>();
			session.setAttribute("cartItems", cartItems);
			for(Record record : records) {
				if (record.getRecordId() == recordId) {
					cartItems.add(new ShopCartItem(1, record.getPrice(), record));
					return "redirect:/recordlist";
				}
			}
		}
		
		boolean idFounded = false;
		
		for(ShopCartItem item : cartItems) {
			if (item.getRecord().getRecordId() == recordId) {
				int quantity = item.getQuantity();
				item.setQuantity(quantity = quantity + 1);
				item.setTotalCost(quantity*item.getRecord().getPrice());
				idFounded = true;
				return "redirect:/recordlist";
			}
		}

		if(idFounded == false) {
			for(Record record : records) {
				if (record.getRecordId() == recordId) {
					cartItems.add(new ShopCartItem(1, record.getPrice(), record));
					break;
				}
			}
		}		
		return "redirect:/recordlist";
	}

	// PLUS ONE: Edit item quantity by increments in shopping cart (quantity +1) --> "/plusone/{recordId}", koska shopCartteja ei ole tallennettu repoon, joten ei ole Id:tä
	@RequestMapping(value = "/plusone/{id}", method = RequestMethod.GET)
	public String addRecordQuantity(@PathVariable("id") Long idFromRecord) {		
		@SuppressWarnings("unchecked")	
		List<ShopCartItem> cartItems = (List<ShopCartItem>) session.getAttribute("cartItems");

		for(ShopCartItem item : cartItems) {
			if(item.getRecord().getRecordId() == idFromRecord) {
				int quantity = item.getQuantity();
				item.setQuantity(quantity = quantity + 1);
				item.setTotalCost(item.getQuantity() * item.getRecord().getPrice());
				break;
			}
		}
		return "redirect:/shoppingcart";
	}
	
	// SUBTRACT ONE: Edit item quantity by increments in shopping cart (quantity -1) --> "/minusone/{recordId}", koska shopCartteja ei ole tallennettu repoon, joten ei ole Id:tä
	@RequestMapping(value = "/minusone/{id}", method = RequestMethod.GET)
	public String subtractRecordQuantity(@PathVariable("id") Long idFromRecord) {	
		@SuppressWarnings("unchecked")	
		List<ShopCartItem> cartItems = (List<ShopCartItem>) session.getAttribute("cartItems");
		
		for(ShopCartItem item : cartItems) {
			if(item.getRecord().getRecordId() == idFromRecord) {
				if(item.getQuantity() > 0) {
					int quantity = item.getQuantity();
					item.setQuantity(quantity = quantity - 1);
					item.setTotalCost(item.getQuantity() * item.getRecord().getPrice());
					break;
				}
			}
		}
		return "redirect:/shoppingcart";
	}
	
	// DELETE item from shopping cart --> "/deleteitem/{recordId}"
	@RequestMapping(value = "/deleteitem/{id}", method = RequestMethod.GET)
	public String deleteItem(@PathVariable("id") Long idFromRecord, Model model) {	
		@SuppressWarnings("unchecked")	
		List<ShopCartItem> cartItems = (List<ShopCartItem>) session.getAttribute("cartItems");
		
		int i = 0;
		for(ShopCartItem item : cartItems) {
			if(item.getRecord().getRecordId() == idFromRecord) {
				cartItems.remove(i);
				break;
			}
			i++;
		}
		return "redirect:../shoppingcart";
	}
	
}

/*
 
 
 
*/

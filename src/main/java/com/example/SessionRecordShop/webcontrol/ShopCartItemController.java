// http://localhost:8080/recordlist
package com.example.SessionRecordShop.webcontrol;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
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
import com.example.SessionRecordShop.domain.ShopCartItemRepository;



@Controller
public class ShopCartItemController {
	
	@Autowired
	private ShopCartItemRepository shopCartItemRepository;
	
	@Autowired
	private RecordRepository recordRepository;
	
	@Autowired
	private HttpSession session;
	
//	@Autowired
//	private HttpServletRequest request;
	
	// LIST shopping cart
	@RequestMapping(value = "/shoppingcart", method = RequestMethod.GET)
	public String addCart(Model model) {
		//@SuppressWarnings("unchecked")
		//List<ShopCartItem> cartItems = (List<ShopCartItem>) session.getAttribute("cartItems");
		model.addAttribute("cartItems", session.getAttribute("cartItems"));
		//model.addAttribute("shopCartItems", shopCartItemRepository.findAll());
		return "shoppingcart"; // .html
	}
	
	// ADD record to shopping cart by id -> shopCartItemRepository
	@RequestMapping(value = "/addcart/{id}", method = RequestMethod.GET)
	public String addCart(@PathVariable("id") Long recordId, Model model) {	
		
		@SuppressWarnings("unchecked")	
		List<ShopCartItem> cartItems = (List<ShopCartItem>) session.getAttribute("cartItems");
		ArrayList<Record> records = (ArrayList<Record>) recordRepository.findAll();
		
		if (cartItems == null) {
			cartItems = new ArrayList<ShopCartItem>();
			session.setAttribute("cartItems", cartItems); //request.getSession().setAttribute("sessionItems", cartItems);
			for(Record record : records) {
				if (record.getRecordId() == recordId) {
					cartItems.add(new ShopCartItem(1, record.getPrice(), record));
					break;				
				}
			}
		}

		return "redirect:/recordlist"; // .html
		
//		boolean idFounded = false;
//		
//		for(ShopCartItem item : cartItems) {
//			if (item.getRecord().getRecordId() == recordId) {
//				int quantity = item.getQuantity();
//				item.setQuantity(quantity = quantity + 1);
//				item.setTotalCost(quantity*item.getRecord().getPrice());
//				cartItems.add(item);
//				idFounded = true;
//				return "redirect:/recordlist";		
//			} 
//		}
//		
//		if (cartItems == null || !idFounded) {
//			cartItems = new ArrayList<ShopCartItem>();
//			setAttribute("cartItems", cartItems); //request.getSession().setAttribute("sessionItems", cartItems);
//			for(Record record : records) {
//				if (record.getRecordId() == recordId) {
//					cartItems.add(new ShopCartItem(1, record.getPrice(), record));
//					break;				
//				}
//			}
//		}
	}

	// PLUS ONE: Edit item quantity by increments in shopping cart (quantity +1)
	@RequestMapping(value = "/plusone/{id}", method = RequestMethod.GET)
	public String addRecordQuantity(@PathVariable("id") Long itemId, Optional<ShopCartItem> item) {		
		item = shopCartItemRepository.findById(itemId);
		ShopCartItem shopCartItem = item.get();
		
		int quantity = shopCartItem.getQuantity();
		shopCartItem.setQuantity(quantity = quantity + 1);
		shopCartItem.setTotalCost(shopCartItem.getQuantity() * shopCartItem.getRecord().getPrice());
		shopCartItemRepository.save(shopCartItem);

		return "redirect:/shoppingcart";
	}
	
	// SUBTRACT ONE: Edit item quantity by increments in shopping cart (quantity -1)
	@RequestMapping(value = "/minusone/{id}", method = RequestMethod.GET)
	public String subtractRecordQuantity(@PathVariable("id") Long itemId, Optional<ShopCartItem> item) {
		item = shopCartItemRepository.findById(itemId);
		ShopCartItem shopCartItem = item.get();
		
		int quantity = shopCartItem.getQuantity();
		
		if (quantity > 0) {
			shopCartItem.setQuantity(quantity = quantity - 1);
			shopCartItem.setTotalCost(shopCartItem.getQuantity() * shopCartItem.getRecord().getPrice());
			shopCartItemRepository.save(shopCartItem);
		}		
		return "redirect:/shoppingcart";
	}
	
	// SAVE ShopCartItem --> Jos form/quantity kombon saa toimimaan
	@RequestMapping(value = "/saveitem", method = RequestMethod.POST)
	public String saveShopCartItem(ShopCartItem shopCartItem) {
		shopCartItemRepository.save(shopCartItem);
		return "redirect:/shoppingcart";
	}
	
	// DELETE item from shopping cart
	@RequestMapping(value = "/deleteitem/{id}", method = RequestMethod.GET)
	public String deleteItem(@PathVariable("id") Long itemId, Model model) {
		shopCartItemRepository.deleteById(itemId);
		return "redirect:../shoppingcart";
	}
	
}

/*
 
<div th:each="client : ${clients}">

            <div th:each="purchase : ${client.purchases}">
                <span id="2"
                    th:text="${#aggregates.sum(purchase.products.![price])}"> </span>
                <div th:each="product : ${purchase.products}">
                    <span th:text="${product.price}"></span>

                </div>
            </div>
        </div> 
 
 


<!--  Miten #aggregates.sum() toimii? -->
<!-- <p th:text="${#aggregateUtils.sum(shopCartItems)}"></p>  -->
<!-- <p th:text="${#aggregates.sum(shopCartItems)}"></p>  -->

<div th:each="item : ${shopCartItems}">
        <span th:text="${#aggregates.sum(item.totalCost)}"> </span>
 </div>

<p th:text="${#aggregates.sum(shopCartItems)}"></p>

 */

// jos shopCartItems on tyhjä, luodaan uusi ShopCartItem ja tallennetaan se repoon. Record record -parametri etsitään id:n avulla - ShopCartItem (int quantity, double totalCost, Record record)
//if(shopCartItems.isEmpty()) {
//	for(Record record : records) {
//		if (record.getRecordId() == recordId) {
//			shopCartItemRepository.save(new ShopCartItem(1, record.getPrice(), record));
//			return "redirect:/recordlist";		
//		}
//	}
//}


//for(ShopCartItem item : shopCartItems) {
//if (item.getRecord().getRecordId() == recordId) {
//int quantity = item.getQuantity();
//item.setQuantity(quantity = quantity + 1);
//item.setTotalCost(quantity*item.getRecord().getPrice());
//shopCartItemRepository.save(item);
//break;
//} else {
//// ShopCartItem(int quantity, double totalCost, Record record);
//shopCartItemRepository.save(new ShopCartItem(1, item.getRecord().getPrice(),item.getRecord())); // Mitä item.gerRecord palauttaa? miten getRecordById??
//}

/*



*/

//int quantity = record.getShopCartItem().getQuantity();
//record.getShopCartItem().setQuantity(quantity = quantity + 1);
//record.getShopCartItem().setTotalCost(quantity*record.getPrice());
//recordRepository.save(record); // tämä pitää seivaa shopCarttiin??

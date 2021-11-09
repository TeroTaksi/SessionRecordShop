// http://localhost:8080/recordlist
package com.example.SessionRecordShop.webcontrol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.SessionRecordShop.domain.Record;
import com.example.SessionRecordShop.domain.RecordRepository;


@Controller
public class RecordController {
	
	@Autowired
	private RecordRepository recordRepository;
	
//	@Autowired
//	private ShopCartItemRepository shopCartItemRepository;
	
	// LIST all records - localhost:8080/recordlist
	@RequestMapping(value = {"/", "/recordlist"}, method = RequestMethod.GET) // both end points
	public String allrecords(Model model) {
		model.addAttribute("records", recordRepository.findAll());
		return "recordlist"; // .html
	}
	
	// SEND EMPTY Record OBJECT to new Record form
	@RequestMapping(value = "/addrecord", method = RequestMethod.GET)
	public String addRecord(Model model) {
		model.addAttribute("newRecord", new Record());
		return "addrecord"; // .html
	}
	
	// SAVE Record from save record / edit record 
	@RequestMapping(value = "/saverecord", method = RequestMethod.POST)
	public String saveRecord(Record record) {
		recordRepository.save(record);
		return "redirect:/recordlist";
	}
	
	// EDIT Record by id
	@RequestMapping(value = "/editrecord/{id}", method = RequestMethod.GET)
	public String editRecord(@PathVariable("id") Long recordId, Model model) {
		model.addAttribute("editRecord", recordRepository.findById(recordId));
		return "editrecord"; // .html
	}
	
	// DELETE Record by id
	@RequestMapping(value = "/deleterecord/{id}", method = RequestMethod.GET)
	public String deleteRecord(@PathVariable("id") Long recordId, Model model) {
		recordRepository.deleteById(recordId);
		return "redirect:../recordlist";
	}

}

	
/*

//model.addAttribute("record", recordRepository.findById(recordId));
//ArrayList<Record> recordsById = (ArrayList<Record>) recordRepository.findById(recordId);

	
//	// List shopping cart (Records where quantity > 0)
//	@RequestMapping(value = "/shoppingcart", method = RequestMethod.GET) // Tämä metodi on listalle
//	public String addCart(Model model) {
//		model.addAttribute("records", recordRepository.findAll());
//		return "shoppingcart"; // .html
//	}

*/

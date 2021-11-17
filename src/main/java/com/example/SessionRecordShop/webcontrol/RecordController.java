// http://localhost:8080/recordlist
package com.example.SessionRecordShop.webcontrol;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.SessionRecordShop.domain.Record;
import com.example.SessionRecordShop.domain.RecordRepository;
import com.example.SessionRecordShop.domain.FormatRepository;


@Controller
public class RecordController { //----------- S E S S I O N -------------------------------------
	
	@Autowired
	private RecordRepository recordRepository;
	
	@Autowired
	private FormatRepository formatRepository;
	
	// LOGIN form
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login"; // .html
	}
	
	// LIST all records - localhost:8080/recordlist
	@RequestMapping(value = {"/", "/recordlist"}, method = RequestMethod.GET) // both end points
	public String allrecords(Model model) {
		model.addAttribute("records", recordRepository.findAll());
		return "recordlist"; // .html
	}
	
	// --------validointi hässäkkä---------------
	// SEND EMPTY Record OBJECT to new Record form
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/addrecord", method = RequestMethod.GET)
	public String addRecord(Model model) {
		model.addAttribute("record", new Record());
		model.addAttribute("formats", formatRepository.findAll());
		return "addrecord"; // .html
	}
	// --------validointi hässäkkä---------------
	// SAVE Record from /saverecord
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/saverecord", method = RequestMethod.POST)
	public String saveRecord(@Valid Record record, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) { // validation errors
			model.addAttribute("record", record);
			model.addAttribute("formats", formatRepository.findAll());
			return "addrecord";  // .html
		} else { // no validation errors
			recordRepository.save(record);
			return "redirect:/recordlist"; // .html
		}
	}
	
	// EDIT Record by id
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/editrecord/{id}", method = RequestMethod.GET)
	public String editRecord(@PathVariable("id") Long recordId, Model model) {
		model.addAttribute("editRecord", recordRepository.findById(recordId));
		model.addAttribute("formats", formatRepository.findAll());
		return "editrecord"; // .html
	}
	
	// SAVE EDITED Record from /editrecord
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/editrecord/{id}", method = RequestMethod.POST)
	public String saveEditedRecord(@Valid Record editRecord, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) { // validation errors
			model.addAttribute("editRecord", editRecord);
			model.addAttribute("formats", formatRepository.findAll());
			return "editrecord";  // .html
		} else { // no validation errors
			recordRepository.save(editRecord);
			return "redirect:/recordlist"; // .html
		}
	}
	
	// DELETE Record by id
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/deleterecord/{id}", method = RequestMethod.GET)
	public String deleteRecord(@PathVariable("id") Long recordId, Model model) {
		recordRepository.deleteById(recordId);
		return "redirect:../recordlist";
	}
	
	//------------- R E S T -----------------------------------------------------
	
	// REST - LIST all Records (localhost:8080/records) - @ResponseBody: List<Book> --> JSON
	@RequestMapping(value="/records", method = RequestMethod.GET)
	public @ResponseBody List<Record> recordListRest() {
		// return: Iterable<T> to List<Record>
		return (List<Record>) recordRepository.findAll(); 
	}
	
	// REST - get Format by id
	@RequestMapping(value="/records/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Record> findRecordByIdRest(@PathVariable("id") Long recordId) {
		return recordRepository.findById(recordId);
	}
	
	// REST - SAVE Record
	@RequestMapping(value="/records", method = RequestMethod.POST)
	public @ResponseBody Record saveRecordRest(@RequestBody Record record) {
		return recordRepository.save(record);
	}

}

	
/*

 ------- CRUD FUNCTIONS ------------------------------------------------------------------------------------------------
 Methods inherited from interface org.springframework.data.repository.CrudRepository
 
 count();
 delete(T entity);
 deleteAll()
 deleteAll(Iterable<? extends ID> ids);
 deleteAll(Iterable<? extends T> entities);
 deleteAllById(id);
 deleteById(id);
 existsById(id);
 findById(id);
 save(x);
 
 https://docs.spring.io/spring-data/jpa/docs/current/api/org/springframework/data/jpa/repository/JpaRepository.html
------------------------------------------------------------------------------------------------------------------------

*/

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

import com.example.SessionRecordShop.domain.Format;
import com.example.SessionRecordShop.domain.FormatRepository;


@Controller
public class FormatController { //----------- S E S S I O N -------------------------------------
	
	@Autowired
	private FormatRepository formatRepository;
	
	// LIST all Formats --> localhost:8080/formatslist
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/formatslist", method = RequestMethod.GET)
	public String listAllFormats(Model model) {
		model.addAttribute("formats", formatRepository.findAll());
		return "formatslist"; // .html
	}
	
	// SEND EMPTY Format object to ADD format form --> /addformat
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/addformat", method = RequestMethod.GET)
	public String addFormat(Model model) {
		model.addAttribute("format", new Format());
		return "addformat"; // .html
	}
	
	// SAVE Format from form --> /addformat
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/saveformat", method = RequestMethod.POST)
	public String saveFormat(@Valid Format format, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) { // validation errors
			model.addAttribute("format", format);
			return "addformat";  // .html
		} else { // no validation errors
			formatRepository.save(format);
			return "redirect:/formatslist"; // .html
		}

	}
	
	// EDIT Format by id
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/editformat/{id}", method = RequestMethod.GET)
	public String editFormat(@PathVariable("id") Long formatId, Model model) {
		model.addAttribute("editFormat", formatRepository.findById(formatId));
		return "editformat";
	}
	
	// SAVE EDITED Format from form --> /editformat/{id}
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/editformat/{id}", method = RequestMethod.POST)
	public String saveEditedFormat(@Valid Format editFormat, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) { // validation errors
			model.addAttribute("editFormat", editFormat);
			return "editformat";  // .html
		} else { // no validation errors
			formatRepository.save(editFormat);
			return "redirect:/formatslist"; // .html
		}

	}
	
	// DELETE Format by id --> Risky Business -> Deletoimalla LP:n, deletoin koko prkleen Record tietokannan, koska silloin poistan Recordista parametrin
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/deleteformat/{id}", method = RequestMethod.GET)
	public String deleteFormat(@PathVariable("id") Long formatId, Model model) {
		formatRepository.deleteById(formatId);
		return "redirect:/formatslist";
	}
	
	//------------- R E S T -----------------------------------------------------
	
	// REST - LIST all Formats (localhost:8080/formats) - @ResponseBody: List<Format> --> JSON
	@RequestMapping(value="/formats", method = RequestMethod.GET)
	public @ResponseBody List<Format> formatListRest() {
		// return: Iterable<T> to List<Record>
		return (List<Format>) formatRepository.findAll(); 
	}
	
	// REST - get Format by id
	@RequestMapping(value="/formats/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Format> findFormatByIdRest(@PathVariable("id") Long formatId) {
		return formatRepository.findById(formatId);
	}
	
	// REST - SAVE Format
	@RequestMapping(value="/formats", method = RequestMethod.POST)
	public @ResponseBody Format saveFormatRest(@RequestBody Format format) {
		return formatRepository.save(format);
	}
	
	// REST - EDIT Format by id
//	@RequestMapping(value="/formats/edit/{id}", method = RequestMethod.POST)
//	public @ResponseBody Optional<Format> editFormatByIdRest(@PathVariable("id") Long formatId) {
//		Format format = (Optional<Format>) formatRepository.findById(formatId);
//		return Optional.empty();
//	}
	
	//  (Optional<Format>)
	// Format format = 
	
	// REST - DELETE Format
//	@RequestMapping(value="/formats/delete/{id}", method = RequestMethod.DELETE)
//	public @ResponseBody Optional<Format> deleteFormatRest(@PathVariable("id") Long formatId) {
//		formatRepository.deleteById(formatId);
//		return "";
//	}
	
	// https://spring.io/guides/tutorials/rest/
//	@RequestMapping(value="/formats/{id}", method = RequestMethod.DELETE)
//	void deleteFormatRest(@PathVariable("id") Long formatId) {
//		formatRepository.deleteById(formatId);
//	}
	
	
	


}

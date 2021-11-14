package com.example.SessionRecordShop.webcontrol;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.SessionRecordShop.domain.Format;
import com.example.SessionRecordShop.domain.FormatRepository;




@Controller
public class FormatController { //----------- S E S S I O N -------------------------------------
	
	@Autowired
	private FormatRepository formatRepository;
	
	// LIST all Formats --> localhost:8080/formatslist
	@RequestMapping(value = "/formatslist", method = RequestMethod.GET)
	public String listAllFormats(Model model) {
		model.addAttribute("formats", formatRepository.findAll());
		return "formatslist"; // .html
	}
	
	// SEND EMPTY Format object to ADD format form --> /addformat
	@RequestMapping(value = "/addformat", method = RequestMethod.GET)
	public String addFormat(Model model) {
		model.addAttribute("format", new Format());
		return "addformat"; // .html
	}
	
	// SAVE Format from form --> /addformat
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
	@RequestMapping(value = "/editformat/{id}", method = RequestMethod.GET)
	public String editFormat(@PathVariable("id") Long formatId, Model model) {
		model.addAttribute("editFormat", formatRepository.findById(formatId));
		return "editformat";
	}
	
	// SAVE EDITED Format from form --> /editformat/{id}
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
	@RequestMapping(value = "/deleteformat/{id}", method = RequestMethod.GET)
	public String deleteFormat(@PathVariable("id") Long formatId, Model model) {
		formatRepository.deleteById(formatId);
		return "redirect:/formatslist";
	}

}

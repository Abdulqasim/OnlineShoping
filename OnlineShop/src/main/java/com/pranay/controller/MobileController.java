package com.pranay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pranay.model.Mobile;
import com.pranay.service.MobileService;

@Controller
@RequestMapping(path = "/mobiles")
public class MobileController {
	@Autowired
	private MobileService ms;

	// ADD
	@GetMapping("/add")
	public String addForm(Model model) {
		model.addAttribute("mobile", new Mobile());
		return "addform";
	}

	@PostMapping("/add")
	public String addMobileSubmit(@ModelAttribute Mobile mobile) {
		this.ms.add(mobile);
		return "redirect:/mobile";
	}

	// GET ALL
	@GetMapping("")
	public String getAll(Model model) {
		model.addAttribute("mobiles", this.ms.getAll());
		return "mobilelist";
	}

	// FIND BY NAME
	@GetMapping("/findbyname")
	public String findByName(@RequestBody String name, Model model) {
		model.addAttribute("mobile", this.ms.findByName(name));
		return "foundpage";
	}

	// GET BY ID
	@GetMapping("/view")
	public String viewById(@RequestParam("id") Integer id, Model model) {
		model.addAttribute("mobile", this.ms.getById(id));
		return "view";
	}

	// UPDATE BY ID
	@PutMapping("/update")
	public String updateById(@RequestParam("id") Integer id, @ModelAttribute Mobile mobile) {
		this.ms.updateById(id, mobile);
		return "redirect:/products";
	}

	// DELTE BY ID
	@DeleteMapping("/delete")
	public String deleteById(@RequestParam("id") Integer id) {
		this.ms.deleteById(id);
		return "redirect:";
	}

}

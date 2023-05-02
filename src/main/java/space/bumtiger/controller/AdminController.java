package space.bumtiger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import space.bumtiger.service.CorderAdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private CorderAdminService service;

	@GetMapping
	public String adminPage(Model model) {
		model.addAttribute("orderCount", service.getOrderCount());
		return "admin";
	}

	@PostMapping("/deleteOrders")
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteAllOrders() {
		service.deleteAllOrders();
		return "redirect:/admin";
	}
}

package space.bumtiger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import space.bumtiger.service.CorderAdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	private CorderAdminService service;

	@PostMapping("/deleteOrders")
	public String deleteAllOrders() {
		service.deleteAllOrders();
		return "redirect:/admin";
	}
}

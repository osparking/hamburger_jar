package space.bumtiger.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import jakarta.validation.Valid;
import space.bumtiger.domain.Corder;
import space.bumtiger.repository.CorderRepository;

@Controller
@RequestMapping("/orders")
@SessionAttributes({ "corder" })
public class OrderController {
	private CorderRepository repository;

	public OrderController(CorderRepository repository) {
		super();
		this.repository = repository;
	}

	@GetMapping("current")
	public String orderForm(Model model) {
		return "orderForm";
	}

	@PostMapping
	public String processOrder(@Valid Corder corder, Errors errors,
			SessionStatus sessionStatus) {
		if (errors.hasErrors()) {
			return "orderForm";
		}
		repository.save(corder);
		sessionStatus.setComplete();

		return "redirect:/";
	}
}

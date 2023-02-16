package space.bumtiger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import space.bumtiger.domain.Corder;
import space.bumtiger.repository.CorderRepository;

@Controller
@RequestMapping("/orders")
@SessionAttributes("burgerOrder")
public class OrderController {
	private CorderRepository repository;

	public OrderController(CorderRepository repository) {
		super();
		this.repository = repository;
	}

	@GetMapping("current")
	public String orderForm() {
		return "orderForm";
	}

	@PostMapping
	public String processOrder(@Valid Corder order, Errors errors,
			SessionStatus sessionStatus) {
		if (errors.hasErrors()) {
			return "orderForm";
		}
		repository.save(order);
		sessionStatus.setComplete();
		
		return "redirect:/";
	}
}

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

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("burgerOrder")
public class OrderController {
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
		log.info("주문 내용: {}", order);
		sessionStatus.setComplete();
		return "redirect:/";
	}
}

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
import space.bumtiger.repository.CorderBurgerRepository;
import space.bumtiger.repository.CorderRepository;

@Controller
@RequestMapping("/orders")
@SessionAttributes({ "corder" })
public class CorderController {
	private CorderRepository repository;
	private CorderBurgerRepository cbRepository;

	public CorderController(CorderRepository corderRepository,
			CorderBurgerRepository corderBurgerRepository) {
		super();
		this.repository = corderRepository;
		this.cbRepository = corderBurgerRepository;
	}

	@GetMapping("current")
	public String orderForm(Model model) {
		model.addAttribute("ccNumber", ccNumber);
		model.addAttribute("ccExpiration", ccExpiration);
		return "orderForm";
	}

	@Value("${credential.ccNumber}")
	public String ccNumber;

	@Value("${credential.ccExpiration}")
	public String ccExpiration;

	@PostMapping
	public String processOrder(@Valid Corder corder, Errors errors,
			SessionStatus sessionStatus) {
		if (errors.hasErrors()) {
			return "orderForm";
		}
		/** 
		 * 주문 자체 저장 
		 */
		Corder corderSaved = repository.save(corder);
		
		/** 
		 * 주문되는 버거정보 CorderBurger 테이블에 저장
		 */
		short key = 1;
		for (var corderBurger : corder.getBurgers()) {
			corderBurger.setCorder(corderSaved.getId());
			corderBurger.setCorderKey(key++);
			cbRepository.save(corderBurger);
		}
		sessionStatus.setComplete();

		return "redirect:/";
	}
}

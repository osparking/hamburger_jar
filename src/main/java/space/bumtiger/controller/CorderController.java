package space.bumtiger.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import space.bumtiger.domain.Corder;
import space.bumtiger.repository.CorderBurgerRepository;
import space.bumtiger.repository.CorderRepository;
import space.bumtiger.security.LoginUser;
import space.bumtiger.service.CorderService;

@Controller
@RequestMapping("/orders")
@SessionAttributes({ "corder", "burgerNames" })
public class CorderController {
	@Autowired
	private CorderRepository repository;
	@Autowired
	private CorderBurgerRepository cbRepository;
	@Autowired
	private CorderService service;

	@GetMapping("/read")
	public String showOrderDetail(Model model, 
			HttpServletRequest request,
			@AuthenticationPrincipal Object user) throws Exception {
		String idStr = request.getParameter("id");
		var corder = new Corder();

		corder.setId(45);
		if (idStr != null) {
			Integer id = Integer.parseInt(idStr);
			corder = service.getOrder(id, user);
		}
		model.addAttribute("corder", corder);
		return "orderDetails";
	}
	
	@GetMapping("current")
	public String orderForm(Model model, HttpServletRequest request) {
		model.addAttribute("ccNumber", ccNumber);
		model.addAttribute("ccExpiration", ccExpiration);
		
		var buffer = new StringBuffer("홍");
		var dtFmt = DateTimeFormatter.ofPattern("yy_MM_dd");
		var nowDT = LocalDateTime.now();
		buffer.append(nowDT.format(dtFmt));
		buffer.append("일");
		
		request.setAttribute("custName", buffer.toString());
		
		buffer.delete(0, buffer.length());
		dtFmt = DateTimeFormatter.ofPattern("HH:mm:ss");
		buffer.append(nowDT.format(dtFmt));
		buffer.append("초");
		
		model.addAttribute("addrDetail", buffer.toString());
		
		return "orderForm";
	}

	@Value("${credential.ccNumber}")
	public String ccNumber;

	@Value("${credential.ccExpiration}")
	public String ccExpiration;

	@PostMapping
	public String processOrder(@Valid Corder corder, Errors errors,
			SessionStatus sessionStatus, 
			@AuthenticationPrincipal Principal principal) {
		if (errors.hasErrors()) {
			return "orderForm";
		}
		/** 
		 * 주문 자체 저장 
		 */
		corder.setUserId(LoginUser.getUserId(principal));
		corder.setPlacedAt(LocalDateTime.now());
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

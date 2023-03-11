package space.bumtiger.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import space.bumtiger.repository.UserRepository;

@Controller
@RequestMapping("/register")
public class RegistrationController {
	private UserRepository repository;
	private PasswordEncoder pwEncoder;
	public RegistrationController(UserRepository repository,
			PasswordEncoder pwEncoder) {
		super();
		this.repository = repository;
		this.pwEncoder = pwEncoder;
	}
	
	@GetMapping
	public String registerView() {
		return "registration";
	}

	@PostMapping
	public String processRegistration(RegistrationForm form) {
		repository.save(form.toUser(pwEncoder));
		return "redirect:/login";
	}
}

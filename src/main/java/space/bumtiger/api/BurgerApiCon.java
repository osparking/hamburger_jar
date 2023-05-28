package space.bumtiger.api;


import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import space.bumtiger.domain.Burger;
import space.bumtiger.domain.User;
import space.bumtiger.repository.BurgerRepository;
import space.bumtiger.repository.UserRepository;
import space.bumtiger.web.BurgerProps;

@RestController
@RequestMapping(path="/api/burgers",
								produces="application/json")
@CrossOrigin(origins = "http://hamburger:8080")
@AllArgsConstructor
public class BurgerApiCon {
	private BurgerRepository burgerRepo;
	private BurgerProps burgerProps;
	private UserRepository userRepository;
	
	@GetMapping(params="recent")
	public List<Burger> recentBurgers(@RequestParam String username) {
		User user = userRepository.findByUsername(username);
		PageRequest page = PageRequest.of(0, 
				burgerProps.getPageSize());
		var result = burgerRepo.findByUserIdOrderByCreatedAtDesc(
				user.getId(), page); 
		return result;
	}

}
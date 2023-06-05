package space.bumtiger.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import space.bumtiger.domain.Corder;
import space.bumtiger.repository.CorderRepository;

@RestController
@RequestMapping(path="/api/orders",
								produces="application/json")
@CrossOrigin(origins = "http://hamburger:8080")
@AllArgsConstructor
public class CorderApiCon {
	private CorderRepository orderRepo;
	
	@PutMapping(path="/{orderId}", consumes="application/json") 
	public Corder putOrder(
			@PathVariable("orderId") Integer orderId,
			@RequestBody Corder corder) {
		corder.setId(orderId);
		return orderRepo.save(corder);
	}
		
}

package space.bumtiger.service;

import java.security.Principal;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import space.bumtiger.domain.Corder;
import space.bumtiger.domain.User;
import space.bumtiger.repository.CorderRepository;
import space.bumtiger.repository.UserRepository;

@Service
@AllArgsConstructor
public class CorderService {

	private CorderRepository repository;
	private UserRepository userRepository;
	
	@PostAuthorize("hasRole('ADMIN') || " +
	    "authentication.name == returnObject.username")
	public Corder getOrder(Integer id, Principal principal) {
		Corder corder = repository.findById(id).orElseThrow();
		User user = userRepository.findById(corder.getUserId()).get();

		corder.setUsername(user.getUsername());
		return corder;
	}
}

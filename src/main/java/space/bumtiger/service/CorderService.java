package space.bumtiger.service;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import space.bumtiger.domain.Corder;
import space.bumtiger.repository.CorderRepository;
import space.bumtiger.security.LoginUser;

@Service
@AllArgsConstructor
public class CorderService {

	private CorderRepository repository;
	
	@PostAuthorize("hasRole('ADMIN') || returnObject.readable")
	public Corder getOrder(Integer id, Object user) throws Exception {
		Corder corder = repository.findById(id).orElseThrow();
		corder.setReadable(false);
		if (LoginUser.getUserId(user) == corder.getUserId()) {
			corder.setReadable(true);
		}
		return corder;
	}
	
}

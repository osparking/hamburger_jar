package space.bumtiger.service;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import space.bumtiger.domain.Corder;
import space.bumtiger.domain.User;
import space.bumtiger.repository.CorderRepository;
import space.bumtiger.security.CustomOAuth2User;

@Service
@AllArgsConstructor
public class CorderService {

	private CorderRepository repository;

	@PostAuthorize("hasRole('ADMIN') || returnObject.readable")
	public Corder getOrder(Integer id, Authentication auth) {
		Corder corder = repository.findById(id).orElseThrow();
		corder.setReadable(false);
		var prin = auth.getPrincipal();
		if (prin instanceof CustomOAuth2User) {
			var idLocal = ((CustomOAuth2User) prin).getIdLocal();
			if (corder.getUserId() == idLocal)
				corder.setReadable(true);
		} else {
			if (corder.getUserId() == ((User)prin).getId())
				corder.setReadable(true);
		}
		return corder;
	}
}

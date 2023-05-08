package space.bumtiger.service;

import java.util.NoSuchElementException;

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
		Corder corder = repository.findById(id).orElseThrow(() -> {
			var msgBuf = new StringBuffer("ID가 '");

			msgBuf.append(id);
			msgBuf.append("'인 주문 없습니다.");

			return new NoSuchElementException(msgBuf.toString());
		});
		corder.setReadable(false);
		if (LoginUser.getUserId(user) == corder.getUserId()) {
			corder.setReadable(true);
		}
		return corder;
	}
	
}

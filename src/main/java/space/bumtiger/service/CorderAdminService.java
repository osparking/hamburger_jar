package space.bumtiger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import space.bumtiger.repository.CorderRepository;

@Service
public class CorderAdminService {
	@Autowired
	private CorderRepository repository;
	
	public void deleteAllOrders() {
		repository.deleteAll();
	}
}

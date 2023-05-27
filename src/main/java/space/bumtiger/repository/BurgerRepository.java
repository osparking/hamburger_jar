package space.bumtiger.repository;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;

import space.bumtiger.domain.Burger;

public interface BurgerRepository 
				extends CrudRepository<Burger, Integer> {

	List<Burger> findByUserIdOrderByCreatedAtDesc(Integer user,
			PageRequest page);
}

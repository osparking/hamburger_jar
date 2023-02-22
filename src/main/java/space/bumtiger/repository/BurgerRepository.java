package space.bumtiger.repository;

import org.springframework.data.repository.CrudRepository;

import space.bumtiger.domain.Burger;

public interface BurgerRepository 
				extends CrudRepository<Burger, Integer> {
}

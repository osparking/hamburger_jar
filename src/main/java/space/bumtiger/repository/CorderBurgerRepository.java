package space.bumtiger.repository;

import org.springframework.data.repository.CrudRepository;

import space.bumtiger.domain.CorderBurger;

public interface CorderBurgerRepository 
				extends CrudRepository<CorderBurger, Integer> {

}

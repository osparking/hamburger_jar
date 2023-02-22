package space.bumtiger.repository;

import org.springframework.data.repository.CrudRepository;

import space.bumtiger.domain.BurgerIngredient;

public interface BurgerIngreRepository 
				extends CrudRepository<BurgerIngredient, Integer> {

}

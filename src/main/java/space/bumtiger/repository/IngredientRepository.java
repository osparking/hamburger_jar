package space.bumtiger.repository;

import org.springframework.data.repository.CrudRepository;

import space.bumtiger.domain.Ingredient;

public interface IngredientRepository 
				extends CrudRepository<Ingredient, String>{

}
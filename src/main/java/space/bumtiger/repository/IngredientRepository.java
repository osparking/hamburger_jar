package space.bumtiger.repository;

import java.util.Optional;
import space.bumtiger.domain.Ingredient;

import org.springframework.data.repository.Repository;

public interface IngredientRepository 
				extends Repository<Ingredient, String>{

	Iterable<Ingredient> findAll();

	Optional<Ingredient> findById(String id);

	Ingredient save(Ingredient ingredient);

}
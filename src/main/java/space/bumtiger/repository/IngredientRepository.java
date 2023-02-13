package space.bumtiger.repository;

import java.util.Optional;

import space.bumtiger.domain.Ingredient;

public interface IngredientRepository {

	Iterable<Ingredient> findAll();

	Optional<Ingredient> findById(String id);

	Ingredient save(Ingredient ingredient);

}
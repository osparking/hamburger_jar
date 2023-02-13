package space.bumtiger.repository.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import space.bumtiger.domain.Ingredient;
import space.bumtiger.repository.IngredientRepository;

@Repository
public class IngredientRepositoryJdbcImpl 
																implements IngredientRepository {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public IngredientRepositoryJdbcImpl(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Iterable<Ingredient> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Ingredient> findById(String id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Ingredient save(Ingredient ingredient) {
		// TODO Auto-generated method stub
		return null;
	}

}

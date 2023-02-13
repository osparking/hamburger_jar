package space.bumtiger.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import space.bumtiger.domain.Ingredient;
import space.bumtiger.repository.IngredientRepository;

@Repository
public class IngredientRepositoryJdbcImpl implements IngredientRepository {

	private JdbcTemplate jdbcTemplate;

	public IngredientRepositoryJdbcImpl(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	// @formatter:off
	private Ingredient mapRowToIngredient(ResultSet row, int rowNum)
			throws SQLException {
		return new Ingredient(
				row.getString("id"), 
				row.getString("name"),
				Ingredient.Type.valueOf(row.getString("type")));
	}

	@Override
	public Iterable<Ingredient> findAll() {
		return jdbcTemplate.query("select id, name, type from Ingredient",
				this::mapRowToIngredient);
	}

	@Override
	public Optional<Ingredient> findById(String id) {
		List<Ingredient> results = jdbcTemplate.query(
				"select id, name, type from Ingredient where id=?",
				this::mapRowToIngredient, id);
		return results.size() == 0 ? Optional.empty() : 
																 Optional.of(results.get(0));
	}
	// @formatter:on

	@Override
	public Ingredient save(Ingredient ingredient) {
		// TODO Auto-generated method stub
		return null;
	}

}

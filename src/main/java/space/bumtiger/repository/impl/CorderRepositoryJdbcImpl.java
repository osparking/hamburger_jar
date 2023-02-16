package space.bumtiger.repository.impl;

import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import space.bumtiger.domain.Burger;
import space.bumtiger.domain.Corder;
import space.bumtiger.domain.Ingredient;
import space.bumtiger.repository.CorderRepository;

@Repository
public class CorderRepositoryJdbcImpl implements CorderRepository {
	private JdbcOperations jdbcOperations;

	public CorderRepositoryJdbcImpl(JdbcOperations jdbcOperations) {
		super();
		this.jdbcOperations = jdbcOperations;
	}

	// @formatter:off
	@Override
	@Transactional
	public Corder save(Corder order) {
		var pscf = new PreparedStatementCreatorFactory(
				"insert into corder (cust_name, addr_road, addr_detail, "
						+ "addr_zip, cc_number, cc_expiration, "
						+ "cc_cvv, placed_at) values (?,?,?,?,?,?,?,?)",
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, 
				Types.CHAR, Types.VARCHAR, Types.CHAR, 
				Types.CHAR, Types.TIMESTAMP);
		pscf.setReturnGeneratedKeys(true);
		
		order.setPlacedAt(LocalDateTime.now());
		PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
				Arrays.asList(order.getCustName(), 
						order.getAddrRoad(),
						order.getAddrDetail(), 
						order.getAddrZip(),
						order.getCcNumber(), 
						order.getCcExpiration(), 
						order.getCcCVV(),
						Timestamp.valueOf(order.getPlacedAt())));
		
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcOperations.update(psc, keyHolder);
		long orderId = keyHolder.getKey().longValue();
		order.setId(orderId);

		List<Burger> burgers = order.getBurgers();
		int i = 0;
		for (Burger burger : burgers) {
			saveBurger(orderId, i++, burger);
		}

		return order;
	}

	private long saveBurger(long orderId, int burgerKey, Burger burger) {
		burger.setCreatedAt(LocalDateTime.now());

		var pscf = new PreparedStatementCreatorFactory(
				"insert into burger (name, created_at) values (?,?)", 
				Types.VARCHAR, Types.TIMESTAMP);
		pscf.setReturnGeneratedKeys(true);
		
		PreparedStatementCreator psc = 
				pscf.newPreparedStatementCreator(
						Arrays.asList(
								burger.getName(), 
								Timestamp.valueOf(burger.getCreatedAt())));
		
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcOperations.update(psc, keyHolder);
		long burgerId = keyHolder.getKey().longValue();
		burger.setId(burgerId);
		
		saveBurgerIngres(burgerId, burger.getIngredients());
		saveCorderBurger(orderId, burgerId, burgerKey);
		
		return burgerId;
	}

	private void saveBurgerIngres
			(long burgerId, List<Ingredient> ingredients) {
		int key = 0;
		for (Ingredient ingredient : ingredients) {
			jdbcOperations.update(
					"insert into burger_ingre"
							+ "(burger_id, ingre_id, ingre_order) values (?,?,?)",
					burgerId, ingredient.getId(), key++);
		}
	}

	private void saveCorderBurger
				(long orderId, long burgerId, int burgerKey) {
		jdbcOperations.update(
				"insert into corder_burger"
						+ "(corder_id, burger_id, burger_key) values (?,?,?)",
				orderId, burgerId, burgerKey);
	}
	// @formatter:on
}

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
						+ "cc_cvv, placed_at) values (?,?,?,?,?,?,?)",
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
	// @formatter:on

	private void saveBurger(long orderId, int burgerKey, Burger burger) {
		// TODO Auto-generated method stub
	}
}

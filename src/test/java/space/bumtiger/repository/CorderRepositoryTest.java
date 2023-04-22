package space.bumtiger.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Collection;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;

import space.bumtiger.domain.Corder;

@DataJdbcTest
class CorderRepositoryTest {

	@Autowired
	private CorderRepository repository;

	private Corder bumOrder;
	private Corder yourOrder;

	@AfterEach
	void tearDown() throws Exception {
		repository.deleteAll();
	}

	@BeforeEach
	void setUp() throws Exception {
		bumOrder = new Corder();
		bumOrder.setAddrDetail("101호");
		bumOrder.setAddrRoad(null);
		bumOrder.setAddrZip(null);
		bumOrder.setCcCVV(null);
		bumOrder.setCcExpiration(null);
		bumOrder.setCcNumber(null);
		bumOrder.setCustName(null);
		bumOrder.setPlacedAt(null);

		yourOrder = new Corder();
		yourOrder.setAddrDetail("연못있는 집");
		yourOrder.setAddrRoad(null);
		yourOrder.setAddrZip(null);
		yourOrder.setCcCVV(null);
		yourOrder.setCcExpiration(null);
		yourOrder.setCcNumber(null);
		yourOrder.setCustName(null);
		yourOrder.setPlacedAt(null);
	}

	@Test
	void testSave() {
		Corder bumOrCorder = repository.save(bumOrder);
		assertNotNull(bumOrCorder);
		assertNotNull(bumOrCorder.getId());
	}

	@Test
	@DisplayName("시험 대상 - ID로 주문 검색 기능")
	void testFindById() {
		// Arrange
		bumOrder = repository.save(bumOrder);

		// Act
		var foundOrder = repository.findById(bumOrder.getId());

		// Assert
		assertNotNull(foundOrder);
		assertThat(foundOrder.get().getId()).isEqualTo(bumOrder.getId());
	}

	@Test
	@DisplayName("시험 대상 - 모든 주문 검색 완료")
	void testFindAll() {
		// arrange
		// - beforeEach 메소드에 의해서 일부 수행
		repository.save(bumOrder);
		repository.save(yourOrder);

		// act
		var orders = repository.findAll();

		// assert
		assertNotNull(orders);
		int size = 0;
		if (orders instanceof Collection) {
			size = ((Collection<?>) orders).size();
		}
		assertThat(size).isEqualTo(2);
	}

	@Test
	void testDeleteById() {
		fail("Not yet implemented");
	}

}

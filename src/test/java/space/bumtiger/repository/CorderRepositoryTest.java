package space.bumtiger.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;

import space.bumtiger.domain.Corder;

@DataJdbcTest
class CorderRepositoryTest {

	@Autowired
	private CorderRepository repository;
	
	private Corder bumOrder;
	
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
	}

	@Test
	void testSave() {
		fail("Not yet implemented");
	}

	@Test
	void testFindById() {
		fail("Not yet implemented");
	}

	@Test
	void testFindAll() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteById() {
		fail("Not yet implemented");
	}

}

package space.bumtiger.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;

import space.bumtiger.domain.Burger;
import space.bumtiger.domain.Corder;
import space.bumtiger.domain.CorderBurger;

@DataJdbcTest
@DisplayName("주문 및 소속 햄버거 DB CRUD 시험")
class CorderBurgerRepositoryTest {

	@Autowired
	private CorderBurgerRepository repository;
	@Autowired
	private CorderRepository corderRepo;
	@Autowired
	private BurgerRepository burgerRepo;

	private CorderBurger orderBurger1 = new CorderBurger();
	private CorderBurger orderBurger2 = new CorderBurger();
	private CorderBurger orderBurger3 = new CorderBurger();

	@BeforeEach
	void setUp() throws Exception {
		// 우리는 버거가 세개 있고, ID는 저장 후 정해진다.
		// {{ 버거 세건
		// 제 1 버거
		Burger burger1 = new Burger();
		burger1.setName("앵거스버거");
		burger1.setCreatedAt(LocalDateTime.now());
		burger1 = burgerRepo.save(burger1);
		int burger1_Id = burger1.getId();

		// 제 2 버거
		Burger burger2 = new Burger();
		burger2.setName("치즈버거");
		burger2.setCreatedAt(LocalDateTime.now());
		burger2 = burgerRepo.save(burger1);
		int burger2_Id = burger2.getId();

		// 제 3 버거
		Burger burger3 = new Burger();
		burger3.setName("생선버거");
		burger3.setCreatedAt(LocalDateTime.now());
		burger3 = burgerRepo.save(burger3);
		int burger3_Id = burger3.getId();
		// }} 

		// {{ 주문 두건
		Corder order1 = new Corder();
		Corder order2 = new Corder();
		int order1Id = corderRepo.save(order1).getId();
		int order2Id = corderRepo.save(order2).getId();
		// }}

		// 주문-버거 개체는 세개 있는데, ID는 저장할 때 부여된다.
		// {{ 주문-버거 세건
		// corderKey 는 주문에서 버거가 추가된 순서이다.
		orderBurger1.setCorder(order1Id);
		orderBurger1.setCorderKey((short) 1);
		orderBurger1.setBurger(burger1_Id);

		orderBurger2.setCorder(order2Id);
		orderBurger2.setCorderKey((short) 1);
		orderBurger2.setBurger(burger2_Id);

		orderBurger3.setCorder(order2Id);
		orderBurger3.setCorderKey((short) 2);
		orderBurger3.setBurger(burger3_Id);
		// }}
	}

	@AfterEach
	void tearDown() throws Exception {
		repository.deleteAll();
	}

	@Test
	@DisplayName("주문-버거 연결 정보 정상 저장")
	void testSave() {
		// arrange
		// act
		var savedCorBur = repository.save(orderBurger3);
		
		// assert
		assertNotNull(savedCorBur);
		assertThat(savedCorBur.getId()).isNotNull();
	}

	@Test
	void testSaveAll() {
		fail("Not yet implemented");
	}

	@Test
	void testFindAllById() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteAllById() {
		fail("Not yet implemented");
	}

}

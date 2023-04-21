package space.bumtiger.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;

import space.bumtiger.domain.Burger;

@DataJdbcTest
class BurgerRepositoryTest {

	@Autowired
	private BurgerRepository repository;
	private Burger cheeseBurger;
	private Burger bulgogiBurger;

	@Test
	@DisplayName("햄버거 - 저장소 - 저장 성공!")
	void testSave() {
		// Arrange
		// Act
		var savedBurger = repository.save(cheeseBurger);

		// Assert
		assertNotNull(savedBurger);
		assertThat(savedBurger.getId()).isNotEqualTo(null);
	}

	@Test
	@DisplayName("햄버거 - 저장소 - ID 찾기 성공!")
	void testFindById() {
		// Arrange
		var burger = repository.save(cheeseBurger);

		// Act
		var foundBurger = repository.findById(burger.getId());

		// Assert
		assertNotNull(foundBurger);
		assertThat(foundBurger.get().getId()).isEqualTo(burger.getId());
	}

	@Test
	@DisplayName("햄버거 - 저장소 - 모두 찾기 성공!")
	void testFindAll() {
		// arrange
		// - beforeEach 메소드에 의해서 일부 수행
		repository.save(cheeseBurger);
		repository.save(bulgogiBurger);

		// act
		var burgers = repository.findAll();

		// assert
		assertNotNull(burgers);
		int size = 0;
		if (burgers instanceof Collection) {
			size = ((Collection<?>) burgers).size();
		}
		assertThat(size).isEqualTo(2);
	}

	@Test
	@DisplayName("햄버거 - 저장소 - ID 삭제 성공!")
	void testDeleteById() {
		// arrange
		Burger burger2del = repository.save(cheeseBurger);
		repository.save(bulgogiBurger);
		
		// act
		repository.delete(burger2del);
		
		// assert
		var optionalBurger = repository.findById(burger2del.getId());
		assertThat(optionalBurger.isEmpty());
		
		var burgers = repository.findAll();
		int size = 0;
		if (burgers instanceof Collection<Burger>) {
			size = ((Collection<?>) burgers).size();
			assertThat(size).isEqualTo(1);
		}
	}

	@BeforeEach
	void cookBurgers() {
		cheeseBurger = new Burger();
		cheeseBurger.setCreatedAt(LocalDateTime.now());
		cheeseBurger.setName("범스치킨버거");

		bulgogiBurger = new Burger();
		bulgogiBurger.setCreatedAt(LocalDateTime.now());
		bulgogiBurger.setName("한국식_불고기버거");
	}
}

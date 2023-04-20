package space.bumtiger.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

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

	@BeforeEach
	void cookBurgers() {
		cheeseBurger = new Burger();
		cheeseBurger.setCreatedAt(LocalDateTime.now());
		cheeseBurger.setName("범스치킨버거");
	}
}

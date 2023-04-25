package space.bumtiger.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import space.bumtiger.domain.Burger;
import space.bumtiger.domain.BurgerIngredient;
import space.bumtiger.repository.IngredientRepository;

@SpringBootTest
@AutoConfigureMockMvc
class BurgerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private IngredientRepository ingredientRepository;

	private Burger cheeseBurger;
	
	@BeforeEach
	void setUp() throws Exception {
		Integer burgerId = 1;

		BurgerIngredient bing1 = new BurgerIngredient();
		bing1.setId(1);
		bing1.setBurger(burgerId);
		bing1.setBurgerKey((short) 1);
		bing1.setIngredient("BNBD");

		BurgerIngredient bing2 = new BurgerIngredient();
		bing2.setId(2);
		bing2.setBurger(burgerId);
		bing2.setBurgerKey((short) 2);
		bing2.setIngredient("BNBD");

		BurgerIngredient bing3 = new BurgerIngredient();
		bing3.setId(3);
		bing3.setBurger(burgerId);
		bing3.setBurgerKey((short) 3);
		bing3.setIngredient("BNBD");

		List<BurgerIngredient> ingList = new ArrayList<>();
		ingList.addAll(List.of(bing1, bing2, bing3));

		cheeseBurger = new Burger();
		cheeseBurger.setId(burgerId);
		cheeseBurger.setName("범스치킨버거");
		cheeseBurger.setIngredients(ingList);		
	}

	@AfterEach
	void tearDown() throws Exception {
		ingredientRepository.deleteAll();
	}

	@Test
	void testProcessBurger() {
		fail("Not yet implemented");
	}

	@Test
	@WithMockUser(username = "", password = "", roles = "USER")
	@DisplayName("햄버거 설계 폼 읽혀짐")
	void testShowDesignForm() throws Exception {
		mockMvc.perform(get("/design"))
				.andExpect(status().isOk()).andExpect(view().name("design"))
				.andExpect(content().string(containsString("햄버거를 창조하세요")));
	}

}

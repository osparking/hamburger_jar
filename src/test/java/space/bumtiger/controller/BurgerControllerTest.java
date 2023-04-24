package space.bumtiger.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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

import space.bumtiger.repository.IngredientRepository;

@SpringBootTest
@AutoConfigureMockMvc
class BurgerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private IngredientRepository ingredientRepository;

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
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

package space.bumtiger.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import space.bumtiger.domain.BurgerIngredient;
import space.bumtiger.domain.Ingredient;
import space.bumtiger.domain.Ingredient.Type;
import space.bumtiger.repository.BurgerRepository;
import space.bumtiger.repository.IngredientRepository;
import space.bumtiger.repository.UserRepository;

@Configuration
@Profile("test")
public class TestConfig {

	private BurgerRepository burgerRepository;
	private UserRepository userRepository;
	
	private List<BurgerIngredient> convert2BurIng(
			Ingredient ... ingredients) {
		var result = new ArrayList<BurgerIngredient>();
		
		for (Ingredient ing : ingredients) {
			result.add(new BurgerIngredient(ing.getId()));
		}
		return result;
	}
	
	@Bean
	CommandLineRunner loadIngredient(IngredientRepository repository) {
		return args -> {
			repository.save(new Ingredient("BNBD", "번빵", Type.BREAD));
			repository.save(new Ingredient("RCBD", "쌀빵", Type.BREAD));
			repository.save(new Ingredient("BLGG", "불고기", Type.PROTEIN));
			repository.save(new Ingredient("CHBM", "닭가슴살", Type.PROTEIN));
			repository.save(new Ingredient("SHMP", "새우", Type.PROTEIN));
			repository.save(new Ingredient("TMTO", "토마토", Type.VEGGIES));
			repository.save(new Ingredient("LETC", "상추", Type.VEGGIES));
			repository.save(new Ingredient("CDCS", "체다치즈", Type.CHEESE));
			repository.save(new Ingredient("MLCS", "모짜렐라치즈", Type.CHEESE));
			repository.save(new Ingredient("BGSC", "버거소스", Type.SAUCE));
			repository.save(new Ingredient("BBSC", "바베큐소스", Type.SAUCE));
		};
	}
}

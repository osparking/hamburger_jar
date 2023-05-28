package space.bumtiger.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import space.bumtiger.domain.Burger;
import space.bumtiger.domain.BurgerIngredient;
import space.bumtiger.domain.Ingredient;
import space.bumtiger.domain.Ingredient.Type;
import space.bumtiger.domain.User;
import space.bumtiger.repository.BurgerRepository;
import space.bumtiger.repository.IngredientRepository;
import space.bumtiger.repository.UserRepository;

@Configuration
@AllArgsConstructor
public class TestConfig {

	private BurgerRepository burgerRepository;
	private UserRepository userRepository;

	private List<BurgerIngredient> convert2BurIng(Ingredient ... ingredients) {
		return Arrays.stream(ingredients)
				.map(ing -> new BurgerIngredient(ing.getId()))
				.collect(Collectors.toCollection(ArrayList::new));
	}
	
	@Bean
	CommandLineRunner loadIngredient(IngredientRepository ingRepo) {
		return args -> {
			Ingredient 번빵 = new Ingredient("BNBD", "번빵", Type.BREAD);
			Ingredient 쌀빵 = new Ingredient("RCBD", "쌀빵", Type.BREAD);
			Ingredient 불고기 = new Ingredient("BLGG", "불고기", Type.PROTEIN);
			Ingredient 닭가슴살 = new Ingredient("CHBM", "닭가슴살", Type.PROTEIN);
			Ingredient 새우 = new Ingredient("SHMP", "새우", Type.PROTEIN);
			Ingredient 토마토 = new Ingredient("TMTO", "토마토", Type.VEGGIES);
			Ingredient 상추 = new Ingredient("LETC", "상추", Type.VEGGIES);
			Ingredient 체다치즈 = new Ingredient("CDCS", "체다치즈", Type.CHEESE);
			Ingredient 모짜렐라치즈 = new Ingredient("MLCS", "모짜렐라치즈", Type.CHEESE);
			Ingredient 버거소스 = new Ingredient("BGSC", "버거소스", Type.SAUCE);
			Ingredient 바베큐소스 = new Ingredient("BBSC", "바베큐소스", Type.SAUCE);
			
			ingRepo.save(번빵);
			ingRepo.save(쌀빵);   
			ingRepo.save(불고기);  
			ingRepo.save(닭가슴살); 
			ingRepo.save(새우);   
			ingRepo.save(토마토);  
			ingRepo.save(상추);   
			ingRepo.save(체다치즈); 
			ingRepo.save(모짜렐라치즈);
			ingRepo.save(버거소스); 
			ingRepo.save(바베큐소스);

			var hong = new User();
			hong.setUsername("Kang");
			hong.setEnabled(true);
			hong.setRole("ROLE_USER");
			hong = userRepository.save(hong);
			Integer hongId = hong.getId();
	    Burger burger1 = new Burger();
	    burger1.setName("육식가");
	    burger1.setUserId(hongId);
	    burger1.setIngredients(convert2BurIng(
	            번빵, 불고기, 닭가슴살, 버거소스, 바베큐소스, 체다치즈));
	    burgerRepository.save(burger1);
	 
	    Burger burger2 = new Burger();
	    burger2.setName("잡식가용");
	    burger2.setUserId(hongId);
	    burger2.setIngredients(convert2BurIng(
	            쌀빵, 불고기, 체다치즈, 
	            모짜렐라치즈, 버거소스));
	    burgerRepository.save(burger2);
	 
	    Burger burger3 = new Burger();
	    burger3.setName("채식주의자");
	    burger3.setUserId(hongId);
	    burger3.setIngredients(convert2BurIng(
	            번빵, 쌀빵, 토마토, 
	            상추, 바베큐소스));
	    burgerRepository.save(burger3);
		};
	}
}

package space.bumtiger.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;
import space.bumtiger.domain.Burger;
import space.bumtiger.domain.BurgerOrder;
import space.bumtiger.domain.Ingredient;
import space.bumtiger.domain.Ingredient.Type;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("burgerOrder")
public class DesignBurgerController {

	@PostMapping
	public String processBurger(Burger burger,
			@ModelAttribute BurgerOrder burgerOrder) {
		burgerOrder.addBurger(burger);
		log.info("만들어진 버거 처리: {}", burger);

		return "redirect:/orders/current";
	}

	@ModelAttribute
	public void addIngredientsToModel(Model model) {
		List<Ingredient> ingredients = Arrays.asList(
				new Ingredient("BNBD", "번빵", Type.BREAD),
				new Ingredient("RCBD", "쌀빵", Type.BREAD),
				new Ingredient("BLGG", "불고기", Type.PROTEIN),
				new Ingredient("CHBM", "닭가슴살", Type.PROTEIN),
				new Ingredient("SHMP", "새우", Type.PROTEIN),
				new Ingredient("TMTO", "토마토", Type.VEGGIES),
				new Ingredient("LETC", "상추", Type.VEGGIES),
				new Ingredient("CDCS", "체다치즈", Type.CHEESE),
				new Ingredient("MLCS", "모짜렐라치즈", Type.CHEESE),
				new Ingredient("BGSC", "버거소스", Type.SAUCE),
				new Ingredient("BBSC", "바베큐소스", Type.SAUCE));

		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(),
					filterByType(ingredients, type));
		}
	}

	@ModelAttribute(name = "burgerOrder")
	public BurgerOrder order() {
		return new BurgerOrder();
	}

	@ModelAttribute(name = "burger")
	public Burger burger() {
		return new Burger();
	}

	@GetMapping
	public String showDesignForm() {
		return "design";
	}

	private Iterable<Ingredient> filterByType(List<Ingredient> ingredients,
			Type type) {
		return ingredients.stream().filter(x -> x.getType().equals(type))
				.collect(Collectors.toList());
	}

}
package space.bumtiger.controller;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import space.bumtiger.domain.Burger;
import space.bumtiger.domain.BurgerIngredient;
import space.bumtiger.domain.Corder;
import space.bumtiger.domain.CorderBurger;
import space.bumtiger.domain.Ingredient;
import space.bumtiger.domain.Ingredient.Type;
import space.bumtiger.repository.BurgerIngreRepository;
import space.bumtiger.repository.BurgerRepository;
import space.bumtiger.repository.IngredientRepository;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes({ "corder" })
public class BurgerController {

	private IngredientRepository repository;
	private BurgerRepository burgerRepository;
	private BurgerIngreRepository burgerIngreRepository;

	public BurgerController(IngredientRepository repository) {
		super();
		this.repository = repository;
	}

	@PostMapping
	public String processBurger(@Valid Burger burger, Errors errors,
			@ModelAttribute Corder corder) {
		if (errors.hasErrors())
			return "design";
		else {
			short key = 1;
			var corderBurger = new CorderBurger();

			/**
			 * 이 버거 자체 저장
			 */
			Burger savedBurger = burgerRepository.save(burger);

			/**
			 * 이 버거에 들어가는 재료(들) 저장
			 */
			for (BurgerIngredient burgerIngredient : burger.getIngredients()) {
			        burgerIngredient.setBurger(savedBurger.getId());
			        burgerIngredient.setBurgerKey(key++);
			        burgerIngreRepository.save(burgerIngredient);
			}
			
			/**
			 * 고객 주문(Corder: Customer Order)에 이 버거 추가
			 */
			corderBurger.setCorder(corder.getId());
			key = (short) (corder.getBurgers().size() + 1);
			corderBurger.setCorderKey(key);
			corder.addBurger(corderBurger);
			log.info("만들어진 버거 처리: {}", burger);

			return "redirect:/orders/current";
		}
	}

	@ModelAttribute
	public void addIngredientsToModel(Model model) {
		Iterable<Ingredient> ingredients = repository.findAll();

		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(),
					filterByType(ingredients, type));
		}
	}

	@ModelAttribute(name = "corder")
	public Corder corder() {
		return new Corder();
	}

	@ModelAttribute(name = "burger")
	public Burger burger() {
		return new Burger();
	}

	@GetMapping
	public String showDesignForm() {
		return "design";
	}

	private Iterable<Ingredient> filterByType(Iterable<Ingredient> ingredients,
			Type type) {
		return StreamSupport.stream(ingredients.spliterator(), false)
				.filter(x -> (x.getType().equals(type))).collect(Collectors.toList());
	}

}
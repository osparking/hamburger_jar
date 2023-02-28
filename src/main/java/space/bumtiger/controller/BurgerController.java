package space.bumtiger.controller;

import java.util.ArrayList;
import java.util.List;
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

import jakarta.servlet.http.HttpSession;
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
@SessionAttributes({ "corder", "burgerNames" })
public class BurgerController {

	private IngredientRepository ingredientRepository;
	private BurgerRepository burgerRepository;
	private BurgerIngreRepository burgerIngreRepository;

	public BurgerController(IngredientRepository ingredientRepository,
			BurgerRepository burgerRepository,
			BurgerIngreRepository burgerIngreRepository) {
		super();
		this.ingredientRepository = ingredientRepository;
		this.burgerRepository = burgerRepository;
		this.burgerIngreRepository = burgerIngreRepository;
	}

	@PostMapping
	public String processBurger(@Valid Burger burger, Errors errors,
			@ModelAttribute Corder corder,
			HttpSession session) {
		if (errors.hasErrors())
			return "design";
		else {
			@SuppressWarnings("unchecked")
			List<String> burgerNames = (List<String>) session
					.getAttribute("burgerNames");			
			burgerNames.add(burger.getName());
			/**
			 * 이 버거 자체 저장
			 */
			Burger savedBurger = burgerRepository.save(burger);

			/**
			 * 이 버거에 들어가는 재료(들) 저장
			 */
			short key = 1;
			for (BurgerIngredient burgerIngredient : burger.getIngredients()) {
			        burgerIngredient.setBurger(savedBurger.getId());
			        burgerIngredient.setBurgerKey(key++);
			        burgerIngreRepository.save(burgerIngredient);
			}
			
			/**
			 * 고객 주문(Corder: Customer Order)에 이 버거 추가
			 */
			var corderBurger = new CorderBurger();
			corderBurger.setCorder(corder.getId());
			corderBurger.setBurger(savedBurger.getId());
			corder.addBurger(corderBurger);
			log.info("저장된 버거 정보: {}", savedBurger);

			return "redirect:/orders/current";
		}
	}
	
	@ModelAttribute(name = "burgerNames")
	public List<String> burgerNames() {
	        return new ArrayList<String>();
	}
	
	@ModelAttribute
	public void addIngredientsToModel(Model model) {
		Iterable<Ingredient> ingredients = ingredientRepository.findAll();

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
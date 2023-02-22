package space.bumtiger.domain;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class BurgerIngredient {
	@Id
	private Integer id;
	private Integer burger;
	private Short burgerKey;
	private String ingredient;
}

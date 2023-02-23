package space.bumtiger.web;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import space.bumtiger.domain.BurgerIngredient;

@Component
public class IdToBurgerIngredientConverter 
							implements Converter<String, BurgerIngredient> {

	@Override
	public BurgerIngredient convert(String id) {
		var bi = new BurgerIngredient();
		bi.setIngredient(id);
		return bi;
	}

}

package space.bumtiger.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import space.bumtiger.domain.Ingredient;
import space.bumtiger.domain.Ingredient.Type;
import space.bumtiger.repository.IngredientRepository;

@Component
public class IdToIngConverter implements Converter<String, Ingredient> {

	private Map<String, Ingredient> ingMap = new HashMap<>();

	public IdToIngConverter(IngredientRepository repository) {
		super();
		var iter = repository.findAll();
		iter.forEach(ing -> ingMap.put(ing.getId(), ing));
	}

	@Override
	public Ingredient convert(String id) {
		return ingMap.get(id);
	}

}

/*
 * ingMap.put("BNBD", new Ingredient("BNBD", "번빵", Type.BREAD));
 * ingMap.put("RCBD", new Ingredient("RCBD", "쌀빵", Type.BREAD));
 * ingMap.put("BLGG", new Ingredient("BLGG", "불고기", Type.PROTEIN));
 * ingMap.put("CHBM", new Ingredient("CHBM", "닭가슴살", Type.PROTEIN));
 * ingMap.put("SHMP", new Ingredient("SHMP", "새우", Type.PROTEIN));
 * ingMap.put("TMTO", new Ingredient("TMTO", "토마토", Type.VEGGIES));
 * ingMap.put("LETC", new Ingredient("LETC", "상추", Type.VEGGIES));
 * ingMap.put("CDCS", new Ingredient("CDCS", "체다치즈", Type.CHEESE));
 * ingMap.put("MLCS", new Ingredient("MLCS", "모짜렐라치즈", Type.CHEESE));
 * ingMap.put("BGSC", new Ingredient("BGSC", "버거소스", Type.SAUCE));
 * ingMap.put("BBSC", new Ingredient("BBSC", "바베큐소스", Type.SAUCE));
 */
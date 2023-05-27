package space.bumtiger.domain;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Table
public class Burger {

	@Id
	private Integer id;

	@NotNull
	@Size(min = 2, message = "이름은 최소 2 문자만 가능합니다.")
	private String name;
	
	private Integer userId;
	
	private LocalDateTime createdAt = LocalDateTime.now();

	@NotNull
	@Size(min = 1, message = "고객님, 최소 1개의 재료를 선택하세요.")
	private List<BurgerIngredient> ingredients;

}

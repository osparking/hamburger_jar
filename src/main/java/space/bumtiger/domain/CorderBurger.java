package space.bumtiger.domain;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class CorderBurger {
	@Id
	private Integer id;
	private Integer corder;
	private Short corderKey;
	private Integer burger;
}

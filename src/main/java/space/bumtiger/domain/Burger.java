package space.bumtiger.domain;

import java.util.List;

import lombok.Data;

@Data
public class Burger {
	private String name;
	private List<String> ingredients;
	
}

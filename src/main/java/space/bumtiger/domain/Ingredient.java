package space.bumtiger.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Table
@AllArgsConstructor
public class Ingredient implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	@Id
	private Integer sn;
	private String name;
	private Type type;
	
	public Ingredient() {
		super();
		this.id = null;
		this.name = null;
		this.type = null;
	}

	public Ingredient(String id, String name, Type type) {
		this.id = id;
		this.name = name;
		this.type = type;
	}

	public enum Type {
		BREAD, PROTEIN, VEGGIES, CHEESE, SAUCE
	}

}

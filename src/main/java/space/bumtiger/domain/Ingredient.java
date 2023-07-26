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
	private String name;
	private Type type;
	
	public Ingredient() {
		super();
		this.id = null;
		this.name = null;
		this.type = null;
	}

	public enum Type {
		BREAD, PROTEIN, VEGGIES, CHEESE, SAUCE
	}

}

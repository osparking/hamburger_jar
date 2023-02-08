package space.bumtiger.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class BurgerOrder {
	private String custName;
	private String addrRoad;
	private String addrDetail;
	private String addrZip;
	private String ccNumber;
	private String ccExpiration;
	private String ccCVV;

	private final List<Burger> burgers = new ArrayList<>();

	public void addBurger(Burger burger) {
		burgers.add(burger);
	}
}

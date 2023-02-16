package space.bumtiger.repository;

import space.bumtiger.domain.CorderBurger;

public interface CorderRepository {
	CorderBurger save(CorderBurger order);
}

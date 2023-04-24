package space.bumtiger.repository;

import org.springframework.data.repository.CrudRepository;

import space.bumtiger.domain.CorderBurger;

public interface CorderBurgerRepository
		extends CrudRepository<CorderBurger, Integer> {
	Iterable<CorderBurger> findAllByCorder(int order);

	default void deleteByCorder(int corder) {
		var items = this.findAllByCorder(corder);
		items.iterator().forEachRemaining(row -> this.delete(row));
	}
}

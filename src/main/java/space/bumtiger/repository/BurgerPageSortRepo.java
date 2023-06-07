package space.bumtiger.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import space.bumtiger.domain.Burger;

public interface BurgerPageSortRepo 
				extends PagingAndSortingRepository<Burger, Integer> {
}

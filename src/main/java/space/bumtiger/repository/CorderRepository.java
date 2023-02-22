package space.bumtiger.repository;

import org.springframework.data.repository.CrudRepository;

import space.bumtiger.domain.Corder;

public interface CorderRepository 
				extends CrudRepository<Corder, Integer> {
}

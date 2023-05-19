package space.bumtiger.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import space.bumtiger.domain.Corder;

public interface CorderRepository 
				extends CrudRepository<Corder, Integer> {

	List<Corder> findByUserIdOrderByPlacedAtDesc(Integer id);
}

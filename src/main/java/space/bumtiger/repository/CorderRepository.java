package space.bumtiger.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import space.bumtiger.domain.Corder;

// @formatter:off
public interface CorderRepository 
		extends CrudRepository<Corder, Integer> {

	List<Corder> findByUserIdOrderByPlacedAtDesc(
			Integer id, Pageable pageable);
}
// @formatter:on

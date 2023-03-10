package space.bumtiger.repository;

import org.springframework.data.repository.CrudRepository;

import space.bumtiger.domain.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	User findByUsername(String username);
}

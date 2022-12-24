package com.pranay.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import com.pranay.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

	@RestResource
	Optional<User> findByEmail(String email);
	
	
}

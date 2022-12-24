package com.pranay.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pranay.model.Mobile;

@Repository
public interface MobileRepository extends CrudRepository<Mobile, Integer> {

	Optional<Mobile> findByName(String name);

}

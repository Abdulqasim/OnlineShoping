package com.pranay.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.pranay.model.Mobile;
import com.pranay.repository.MobileRepository;

@Service
public class MobileService {

	@Autowired
	private MobileRepository mr;

	// ADD
	public Mobile add(Mobile mobile) {
		return this.mr.save(mobile);
	}

	// GET ALL
	public Iterable<Mobile> getAll() {
		return this.mr.findAll();
	}

	// GET BY ID
	public Mobile getById(Integer id) {
		return this.mr.findById(id).orElseThrow(() -> {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Mobile with id " + id + " does not exists");
		});
	}

	// UPDATE BY ID
	public Mobile updateById(Integer id, Mobile mobile) {
		this.getById(id);
		mobile.setId(id);
		return this.mr.save(mobile);
	}

	// FIND BY NAME
	public Optional<Mobile> findByName(String name) {
		return this.mr.findByName(name);
	}

	// DELTE BY ID
	public void deleteById(Integer id) {
		this.mr.deleteById(id);
	}
}

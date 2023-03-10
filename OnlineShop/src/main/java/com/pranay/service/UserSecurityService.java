package com.pranay.service;

import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pranay.model.User;
import com.pranay.repository.UserRepository;

@Service
public class UserSecurityService implements UserDetailsService {

	@Autowired
	private UserRepository ur;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return this.ur.findByEmail(username).map(user -> {
			return new User(user.getEmail(), user.getPassword(), user.getRoles().stream()
					.map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList()));
		}).orElseThrow(() -> {
			throw new UsernameNotFoundException("User with email does not exists");
		});
	}
}

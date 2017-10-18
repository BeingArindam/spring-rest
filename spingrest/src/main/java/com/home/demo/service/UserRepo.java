package com.home.demo.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.home.demo.entity.User;

public interface UserRepo extends JpaRepository<User, Long> {
	public User findByUsername(String username);
}

package com.home.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.home.demo.entity.Account;
import com.home.demo.service.AccountRepository;

@RestController
@RequestMapping(name = "/accounts")
public class AccountController {
	@Autowired
	AccountRepository accountRepository;

	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@GetMapping
	public List<Account> getAll() {
		return this.accountRepository.findAll();
	}

	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@PostMapping
	public Account saveAccount(Account account) {
		return this.accountRepository.save(account);
	}

}

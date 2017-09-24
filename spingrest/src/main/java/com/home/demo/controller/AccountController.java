package com.home.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.home.demo.entity.Account;
import com.home.demo.service.AccountRepository;

@RestController
public class AccountController {
	@Autowired
	AccountRepository accountRepository;

	@RequestMapping(name = "/accounts")
	public List<Account> getAll() {
		return this.accountRepository.findAllAccount();
	}
}

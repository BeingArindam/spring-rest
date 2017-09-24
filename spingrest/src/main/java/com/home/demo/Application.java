package com.home.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.home.demo.entity.Account;
import com.home.demo.service.AccountRepository;

@Configuration
@ComponentScan(basePackages = { "com.home.demo" })
@EnableAutoConfiguration
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}

@Component
class AccountCommandLineRunner implements CommandLineRunner {
	public void run(String... arg0) throws Exception {
		for (final Account account : this.accountRepository.findAllAccount()) {
			System.out.println(account);
		}
	}

	@Autowired
	AccountRepository accountRepository;

}

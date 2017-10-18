package com.home.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.stereotype.Component;

import com.home.demo.entity.Account;
import com.home.demo.entity.Role;
import com.home.demo.entity.User;
import com.home.demo.service.AccountRepository;
import com.home.demo.service.UserRepo;

@Configuration
@ComponentScan(basePackages = { "com.home.demo" })
@EnableAutoConfiguration
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	public void authenticationManager(AuthenticationManagerBuilder builder, UserRepo repo) throws Exception {
		if (repo.count() == 0) {
			final List<Role> roles = new ArrayList<>();
			roles.add(new Role("USER"));
			roles.add(new Role("ADMIN"));
			repo.save(new User("user", "password", roles));
		}
		builder.userDetailsService(s -> new CustomUserDetails(repo.findByUsername(s)));
	}
}

@Component
class AccountCommandLineRunner implements CommandLineRunner {
	@Override
	public void run(String... arg0) throws Exception {
		for (final Account account : this.accountRepository.findAll()) {
			System.out.println(account);
		}
	}

	@Autowired
	AccountRepository accountRepository;

}

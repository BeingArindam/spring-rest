package com.home.demo.service;

import java.util.Collection;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.home.demo.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	@Transactional(value = TxType.REQUIRED)
	Collection<Account> findByName(String name);

	// @Query("select distinct a from Account a left join fetch a.channels c
	// where a.id = c.account order by a.id")
	// List<Account> findAllAccount();
}

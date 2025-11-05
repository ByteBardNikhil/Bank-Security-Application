package com.security.bank.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.security.bank.entity.Account;
import com.security.bank.entity.AccountType;
import com.security.bank.entity.BranchType;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	
	Optional<Account> findByAccountNumber(Long accountNumber);
	
	
	@Query("Select a from Account a Where a.status='ACTIVE'")
	List<Account> findAllActiveAccounts();
	@Query("Select a from Account a Where a.status='INACTIVE'")
	List<Account> findAllInActiveAccounts();
	
	@Query("Select a FROM Account a Where a.accountType=:accountType")
	List<Account> findAllByAccountType(@Param("accountType")AccountType accountType);
	
	@Query("Select a FROM Account a Where a.branch=:branchType")
	List<Account> findAllByBranch(@Param("branchType")BranchType  branchType);
	
	

}

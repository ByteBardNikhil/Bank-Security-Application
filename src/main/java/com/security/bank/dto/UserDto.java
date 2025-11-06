package com.security.bank.dto;

import java.util.ArrayList;
import java.util.List;

import com.security.bank.entity.Account;
import com.security.bank.entity.Investment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	
    private String name;
    private String username;
    private String password;
    private String address;
    private Long number;
    private String identityProof;
    private List<Account> accountList = new ArrayList<>();
    private List<Investment> investmentList = new ArrayList<>();
    
    
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getNumber() {
		return number;
	}
	public void setNumber(Long number) {
		this.number = number;
	}
	public String getIdentityProof() {
		return identityProof;
	}
	public void setIdentityProof(String identityProof) {
		this.identityProof = identityProof;
	}
	public List<Account> getAccountList() {
		return accountList;
	}
	public void setAccountList(List<Account> accountList) {
		this.accountList = accountList;
	}
	public List<Investment> getInvestmentList() {
		return investmentList;
	}
	public void setInvestmentList(List<Investment> investmentList) {
		this.investmentList = investmentList;
	}
}

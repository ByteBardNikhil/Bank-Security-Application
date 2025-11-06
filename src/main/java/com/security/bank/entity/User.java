package com.security.bank.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.security.bank.dto.UserDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;





//this class must implements UserDetails Why? -->Spring Security
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String username;
    private String password;
    private String address;
    private Long number;
    private String identityProof;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Account> accountList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Investment> investmentList = new ArrayList<>();
    
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name="user_role",joinColumns = @JoinColumn(name="user",referencedColumnName = "id"),inverseJoinColumns = @JoinColumn(name="role",referencedColumnName = "id"))
    private Set<Role> roles=new HashSet<>();

    public static User userDtoToUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setAddress(userDto.getAddress());
        user.setNumber(userDto.getNumber());
        user.setIdentityProof(userDto.getIdentityProof());
        return user;
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	


    
   
    

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      return this.roles.stream().map(role->new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
    }

   

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
	
}

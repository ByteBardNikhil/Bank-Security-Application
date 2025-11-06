package com.security.bank.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.security.bank.dto.UserDto;
import com.security.bank.entity.User;
import com.security.bank.repository.UserRepository;

@Service
public class UserService {
	private final UserRepository userRepository;
	public UserService(UserRepository userRepository)
	{
		this.userRepository=userRepository;
	}

	public User createUser(UserDto userDto) {
		
		BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
		String encodedPassword=bCryptPasswordEncoder.encode(userDto.getPassword());
		userDto.setPassword(encodedPassword);
		User user=User.userDtoToUser(userDto);
		
	
	  userRepository.save(user);
	  return user;
		
		
	}

}

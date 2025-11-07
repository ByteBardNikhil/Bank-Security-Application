package com.security.bank.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.security.bank.dto.JwtResponse;
import com.security.bank.dto.UserDto;
import com.security.bank.entity.User;
import com.security.bank.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController{
	
//	private final AuthService authService;
	
	private final UserService userService;
	
 
    /*
     * User 1 :
     *   "username": "riya_s",
  "password": "abc@987",
     * */
	
	@GetMapping("/test")
	
	public String testConnection()
	{
		return "Success";
	}
	
	@PostMapping("/login")
	public ResponseEntity<JwtResponse> loginUser(@RequestBody JwtResponse jwtRequest)
	{
		
//		JwtResponse jwtResponse=authService.login(jwtRequest);
		
		return ResponseEntity.ok(null);
	}
	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public User registerUser(@RequestBody UserDto userDto)
	{
//		UserDto savedUser=userService.registerUser(userDto);
//		return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
		User user=userService.createUser(userDto);
		return user;
		
	}
	
	

}

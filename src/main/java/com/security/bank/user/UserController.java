package com.security.bank.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.bank.dto.JwtResponse;
import com.security.bank.dto.UserDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController{
	
//	private final AuthService authService;
//	private final UserService userService;
	
	@GetMapping("/test")
	@PreAuthorize("hasRole('ADMIN')")
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
	public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto)
	{
//		UserDto savedUser=userService.registerUser(userDto);
//		return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
		return null;
	}
	
	

}

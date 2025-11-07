package com.security.bank.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtAuthenticationHelper jwtHelper;
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String requestHeader=request.getHeader("Authorization");
		
		//Bearer yeyavasv
	
		
		String username=null;
		String []tokenArray=null;
		String token=null;
		
		if(requestHeader!=null && requestHeader.startsWith("Bearer"))
		{
			token=requestHeader.substring(7);
//			tokenArray=requestHeader.split(" ");
//			token=tokenArray[1];
			
			username=jwtHelper.getUsernameFromToken(token);
			if(username!=null )
			{
				UserDetails userDetails=userDetailsService.loadUserByUsername(username);
				if(!jwtHelper.isTokenExpired(token) && SecurityContextHolder.getContext().getAuthentication()==null)
				{
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
					usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetails(request));
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
					
					
				}
			}
			
			
			
		}
		filterChain.doFilter(request, response);
		
	}

}

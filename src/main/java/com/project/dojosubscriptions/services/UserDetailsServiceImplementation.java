package com.project.dojosubscriptions.services;

import java.util.ArrayList;
import java.util.List;
import com.project.dojosubscriptions.models.User;
import com.project.dojosubscriptions.models.Role;
import com.project.dojosubscriptions.repositories.UserRepository;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService{
	// Member variables / service initializations go here
	private UserRepository userRepository;
		
	public UserDetailsServiceImplementation(UserRepository userRepository){
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		System.out.println(user.getPassword());
		if (user == null){
			throw new UsernameNotFoundException("User not found");
		}
		return new org.springframework.security.core.userdetails.User(user.getusername(), user.getPassword(), getAuthorities(user));
	}

	private List<GrantedAuthority> getAuthorities(User user){
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (Role role: user.getRoles()){
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getName());
			authorities.add(grantedAuthority);
		}
		return authorities;
	}
	
	// Crud methods to act on services go here.
}

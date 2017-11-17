package com.project.dojosubscriptions.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

// import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.dojosubscriptions.repositories.RoleRepository;
import com.project.dojosubscriptions.repositories.UserRepository;
import com.project.dojosubscriptions.models.Role;
import com.project.dojosubscriptions.models.User;
@Transactional
@Service
public class UserService {
	// Member variables / service initializations go here
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

		
	public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	public void saveWithUserRole(User user){
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRoleList(roleRepository.findByName("ROLE_USER"));
		userRepository.save(user);
	}

	public void saveUserWithAdminRole(User user){
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		List<Role> roles = roleRepository.findByName("ROLE_ADMIN");
		roles.add(roleRepository.findByName("ROLE_USER").get(0));
		user.setRoleList(roles);
		userRepository.save(user);
	}

	public User findByUsername(String username){
		return userRepository.findByUsername(username);
	}

	public void updateUser(User user){
		userRepository.save(user);
	}
	public List<User> getAllUsers(){
		return (List<User>) userRepository.findAll();
	}
	public User findById(long id){
		return userRepository.findOne(id);
	}
	public void destroyUser(long id){
		userRepository.delete(id);
	}
	public List<Role> getRole(String role){
		return roleRepository.findByName(role);
	}
	
	// Crud methods to act on services go here.
}

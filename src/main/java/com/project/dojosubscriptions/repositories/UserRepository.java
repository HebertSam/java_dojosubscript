package com.project.dojosubscriptions.repositories;

import com.project.dojosubscriptions.models.User;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository 												
public interface UserRepository extends CrudRepository<User,Long>{
	public User findByUsername(String username);
	
	// Example:
	// public YourModelHere findByName(String name);
}

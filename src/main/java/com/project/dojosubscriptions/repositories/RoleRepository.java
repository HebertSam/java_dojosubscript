package com.project.dojosubscriptions.repositories;

import com.project.dojosubscriptions.models.Role;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository 												
public interface RoleRepository extends CrudRepository<Role,Long>{
	// Query methods go here.
	public List<Role> findByName(String role);
	
	// Example:
	// public YourModelHere findByName(String name);
}

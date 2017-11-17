package com.project.dojosubscriptions.repositories;
import com.project.dojosubscriptions.models.Package;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository 												
public interface PackageRepository extends CrudRepository<Package,Long>{
	// Query methods go here.
	
	// Example:
	// public YourModelHere findByName(String name);
}

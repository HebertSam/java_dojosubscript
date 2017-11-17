package com.project.dojosubscriptions.services;
import com.project.dojosubscriptions.models.Package;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.dojosubscriptions.repositories.PackageRepository;

@Service
public class PackageService {
	// Member variables / service initializations go here
	private PackageRepository packageRepository;
		
	public PackageService(PackageRepository packageRepository){
		this.packageRepository = packageRepository;
	}

	public List<Package> findAllPack(){
		return (List<Package>) packageRepository.findAll();
	}
	public void createPackage(Package pack){
		packageRepository.save(pack);
	}
	public Package findOne(long id){
		return packageRepository.findOne(id);
	}
	public void updatePack(Package pack){
		packageRepository.save(pack);
	}
	public void delete(long id){
		packageRepository.delete(id);
	}
	// Crud methods to act on services go here.
}

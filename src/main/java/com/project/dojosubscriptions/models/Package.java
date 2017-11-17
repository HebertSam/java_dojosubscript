package com.project.dojosubscriptions.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Package{
	@Id
	@GeneratedValue
	private long id;
	@Size(min=2, max=255)
	private String name;
	private double cost;
	private boolean active = true;

	@OneToMany(mappedBy="pack", fetch=FetchType.LAZY)
	private List<User> users;

	// Member variables and annotations go here.
	
	@DateTimeFormat(pattern="MM:dd:yyyy HH:mm:ss")
	private Date createdAt;
	
	@DateTimeFormat(pattern="MM:dd:yyyy HH:mm:ss")
	private Date updatedAt;

	@PrePersist
	public void onCreate(){this.createdAt = new Date();}
	@PreUpdate
	public void onUpdate(){this.updatedAt = new Date();}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public void setCost(double cost){
		this.cost = cost;
	}
	public double getCost(){
		return cost;
	}
	public void setUsers(List<User> users){
		this.users = users;
	}
	public List<User> getUsers(){
		if(this.users == null){
			this.users = new ArrayList<User>();
		}
		return users;
	}
	public void setActive(boolean active){
		this.active = active;
	}
	public boolean getActive(){
		return active;
	}
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	
	// Setters and Getters go here

	public Package(){}
	
	public Package(String name, double cost){
		this.name = name;
		this.cost = cost;
		this.active = true;
		this.createdAt = new Date();
		this.updatedAt = new Date();
	}
}

package com.project.dojosubscriptions.models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;


import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class User{
	@Id
	@GeneratedValue
	private long id;
	@Column(unique=true)
	@Size(min=2, max=255)
	private String username;
	@Size(min=2, max=255)
	private String password;
	@Transient
	private String passwordConfirm;
	@Size(min=2, max=255)
	private String firstName;
	@Size(min=2, max=255)
	private String lastName;
	@Max(31)
	private int dueDay;

	@ManyToMany(fetch= FetchType.EAGER)
	@JoinTable(
		name="users_roles",
		joinColumns = @JoinColumn(name = "user_id"),
		inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	private List<Role> roles;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="package_id")
	private Package pack;
	
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
	public String getCreatedAt() {
		SimpleDateFormat dateFM = new SimpleDateFormat("EEE, d MMM yyyy");
		return dateFM.format(this.createdAt);
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
	public void setUsername(String username){
		this.username = username;
	}
	public String getusername(){
		return username;
	}
	public void setPassword(String password){
		this.password = password;
	}
	public String getPassword(){
		return password;
	}
	public void setPasswordConfirm(String passwordConfirm){
		this.passwordConfirm = passwordConfirm;
	}
	public String getPasswordConfirm(){
		return passwordConfirm;
	}
	public void setRole(Role roles){
		this.roles.add(roles);
	}
	public void setRoleList(List<Role> roles){
		this.roles = roles;
	}
	public List<Role> getRoles(){
		return roles;
	}
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	public String getFirstName(){
		return firstName;
	}
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	public String getLastName(){
		return lastName;
	}
	public void setDueDate(int dueDay){
		this.dueDay = dueDay;
	}
	public String getDueDate(){
		Date dueDate;
		SimpleDateFormat dateFM = new SimpleDateFormat("EEE, d MMM yyyy");
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		if (this.dueDay > cal.getActualMaximum(Calendar.DAY_OF_MONTH)){
			cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
			dueDate = cal.getTime();
		} else if (day < this.dueDay){
			cal.set(Calendar.DATE, this.dueDay);
			dueDate = cal.getTime();
		} else {
			cal.add(Calendar.MONTH, 1);
			cal.set(Calendar.DAY_OF_MONTH, this.dueDay);
			dueDate = cal.getTime(); 
		}
		return dateFM.format(dueDate);
	}
	public void setPack(Package pack){
		this.pack = pack;
	}
	public Package getPack(){
		return pack;
	}

	
	// Setters and Getters go here

	public User(){}
	
	public User(String username, String password, String passwordConfirm){
		this.username = username;
		this.password = password;
		this.passwordConfirm = passwordConfirm;
		this.createdAt = new Date();
		this.updatedAt = new Date();
	}
}

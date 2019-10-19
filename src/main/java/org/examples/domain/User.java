package org.examples.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.WhereJoinTable;

//@Entity
@Table(name="user")
public class User {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@ManyToMany
	@JoinTable(name="user_group_role",joinColumns=@JoinColumn(name="user_id"),inverseJoinColumns=@JoinColumn(name="group_id"))
	private List<Group> groups = new ArrayList<Group>();
	
	@ManyToMany
	@JoinTable(name="user_group_role",joinColumns=@JoinColumn(name="user_id"),inverseJoinColumns=@JoinColumn(name="group_id"))
	@WhereJoinTable(clause="user_group_role='MODERATOR'")
	private List<Group> moderateGroups = new ArrayList<Group>(); 
	
	public User(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public List<Group> getModerateGroups() {
		return moderateGroups;
	}

	public void setModerateGroups(List<Group> moderateGroups) {
		this.moderateGroups = moderateGroups;
	}
	
}

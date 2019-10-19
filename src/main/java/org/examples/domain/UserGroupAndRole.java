package org.examples.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity
@Table(name="user_group_role")
public class UserGroupAndRole implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id	
	@Column(insertable=false,updatable=false,name="user_id")
	private Long userId;
	
	@Id
	@Column(insertable=false,updatable=false,name="group_id")
	private Long groupId;
	
	@Column(name="user_group_role")
	@Enumerated(EnumType.STRING)
	private UserGroupRole userGroupRole;

	public UserGroupAndRole(Long userId, Long groupId, UserGroupRole userGroupRole) {
		super();
		this.userId = userId;
		this.groupId = groupId;
		this.userGroupRole = userGroupRole;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public UserGroupRole getUserGroupRole() {
		return userGroupRole;
	}

	public void setUserGroupRole(UserGroupRole userGroupRole) {
		this.userGroupRole = userGroupRole;
	}
	
	
}

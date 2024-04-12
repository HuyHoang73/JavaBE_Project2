package com.javaweb.repository.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "fullname")
	private String fullname;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "status")
	private String status;
	
//	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//	private List<UserRoleEntity> userRoleEntities = new ArrayList<UserRoleEntity>();
	
	@ManyToMany
	@JoinTable(
		name = "user_role",
		joinColumns = @JoinColumn(name = "userid"),
		inverseJoinColumns = @JoinColumn(name = "roleid"))
	List<RoleEntity> roles = new ArrayList<RoleEntity>();
}

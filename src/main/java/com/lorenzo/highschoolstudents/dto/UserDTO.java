package com.lorenzo.highschoolstudents.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.lorenzo.highschoolstudents.entities.Role;
import com.lorenzo.highschoolstudents.entities.User;

public class UserDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotBlank(message = "Campo obrigatório")
	private String name;
	
	@Email(message = "Favor entrar com um email válido")
	private String email;

	
	private List<RoleDTO> roles = new ArrayList<>();
	  
	public UserDTO() {}

	
	public UserDTO(Long id, String name, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	// SOMENTE O GET NAS LISTAS
	public List<RoleDTO> getRoles() { 
		return roles;
	}



	// construtor implantado na classe UserService
	public UserDTO(User entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.email = entity.getEmail();

		entity.getRoles().forEach(rol -> this.roles.add(new RoleDTO(rol)));
	}


	public UserDTO(User entity, Set<Role> roles) {
		this(entity); 
		roles.forEach(rol -> this.roles.add(new RoleDTO(rol))); 
	}
}

package com.proyectoSegunda.Javier.proyectoJavier.Model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.proyectoSegunda.Javier.proyectoJavier.Entity.UserRole;

public class UserModel {

	private String username;
	private String password;
	private boolean enabled;
	private String nombre;
	private String apellidos;
	private String email;
	private String telefono;
	private Set<UserRole> userRole= new HashSet<UserRole>();
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public Set<UserRole> getUserRole() {
		return userRole;
	}
	public void setUserRole(Set<UserRole> userRole) {
		this.userRole = userRole;
	}
	@Override
	public String toString() {
		return "UserModel [username=" + username + ", password=" + password + ", enabled=" + enabled + ", nombre="
				+ nombre + ", apellidos=" + apellidos + ", email=" + email + ", telefono=" + telefono + ", userRole="
				+ userRole + "]";
	}
	public UserModel(String username, String password, boolean enabled, String nombre, String apellidos, String email,
			String telefono, Set<UserRole> userRole) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.telefono = telefono;
		this.userRole = userRole;
	}
	public UserModel() {
		super();
	}
	
	 

}

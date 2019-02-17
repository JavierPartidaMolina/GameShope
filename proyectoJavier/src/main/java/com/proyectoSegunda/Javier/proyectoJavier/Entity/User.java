package com.proyectoSegunda.Javier.proyectoJavier.Entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.proyectoSegunda.Javier.proyectoJavier.Entity.UserRole;

@Entity
@Table(name="users")
public class User {

	@Id
	@Column(name="username", unique=true, nullable=false, length=45)
	private String username;
	
	@Column(name="password", nullable=false, length=60)
	private String password;
	
	@Column(name="enabled")
	private boolean enabled;
	
	@Column(name="nombre", nullable=false, length=60)
	private String nombre;
	
	@Column(name="apellidos", nullable=false, length=60)
	private String apellidos;
	
	@Column(name="email", nullable=false, length=60)
	private String email;
	
	@Column(name="telefono", nullable=false, length=60)
	private String telefono;
	
	@OneToMany(fetch=FetchType.EAGER,mappedBy="user")
	private Set<UserRole> userRole= new HashSet<UserRole>();
	
	@OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
    private List<Ventas> Ventas = new ArrayList<Ventas>();
	
	@OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
    private List<Alquileres> Alquileres = new ArrayList<Alquileres>();
	
	@OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
    private List<Participaciones> participaciones = new ArrayList<Participaciones>();

	public List<Participaciones> getParticipaciones() {
		return participaciones;
	}

	public void setParticipaciones(List<Participaciones> participaciones) {
		this.participaciones = participaciones;
	}
	
	public List<Ventas> getVentas() {
		return Ventas;
	}

	public void setVentas(List<Ventas> ventas) {
		Ventas = ventas;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public Set<UserRole> getUserRole() {
		return userRole;
	}

	public void setUserRole(Set<UserRole> userRole) {
		this.userRole = userRole;
	}
	

	public User(String username, String password, boolean enabled, String nombre, String apellidos, String email,
			String telefono, Set<UserRole> userRole,
			List<com.proyectoSegunda.Javier.proyectoJavier.Entity.Ventas> ventas) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.telefono = telefono;
		this.userRole = userRole;
		Ventas = ventas;
	}

	public User() {

	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", enabled=" + enabled + ", nombre=" + nombre
				+ ", apellidos=" + apellidos + ", email=" + email + ", telefono=" + telefono + 
				"]";
	}

	public List<Alquileres> getAlquileres() {
		return Alquileres;
	}

	public void setAlquileres(List<Alquileres> alquileres) {
		Alquileres = alquileres;
	}



	
	
}


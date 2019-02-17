package com.proyectoSegunda.Javier.proyectoJavier.Entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="categorias")
public class Categorias {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", unique=true, nullable=false)
	public int id;
	
	@Column(name="nombre", nullable=false, length=60)
	private String nombre;
	
	@ManyToMany(mappedBy="categorias")
	private List<Juegos> juegos = new ArrayList<>();

	public List<Juegos> getJuegos() {
		return juegos;
	}

	public void setJuego(List<Juegos> juego) {
		this.juegos = juego;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Categorias(int id, String nombre, List<Juegos> juego) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.juegos = juego;
	}

	@Override
	public String toString() {
		return "Categorias [id=" + id + ", nombre=" + nombre + "]";
	}

	public Categorias() {
		super();
	}
	
}
package com.proyectoSegunda.Javier.proyectoJavier.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CategoriasModel {
	
	public int id;
	private String nombre;
	private List<JuegosModel> juegos = new ArrayList<>();
	
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
	
	public List<JuegosModel> getJuegos() {
		return juegos;
	}
	
	public void setJuegos(List<JuegosModel> juego) {
		this.juegos = juego;
	}
	
	@Override
	public String toString() {
		return "PlataformasModel [id=" + id + ", nombre=" + nombre + "]";
	}

	public CategoriasModel(int id, String nombre, List<JuegosModel> juego) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.juegos = juego;
	}

	public CategoriasModel() {
		super();
	}	
}

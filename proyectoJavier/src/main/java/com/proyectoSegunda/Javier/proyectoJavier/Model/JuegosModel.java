package com.proyectoSegunda.Javier.proyectoJavier.Model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;

public class JuegosModel {

	public int id;
	private String titulo;
	private String descripcion;
	private Date lanzamiento;
	private String pegi;
	private String tipo;
	private boolean alquilado;
	private String caratula;
	private int precio;
	private int stock;
	private String[] categorias;
	private String[] plataformas;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	

	public String[] getCategorias() {
		return categorias;
	}
	
	public void setCategorias(String[] categorias) {
		this.categorias = categorias;
	}
	
	public String[] getPlataformas() {
		return plataformas;
	}
	
	public void setPlataformas(String[] plataformas) {
		this.plataformas = plataformas;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getLanzamiento() {
		return lanzamiento;
	}

	public void setLanzamiento(java.sql.Date date) {
		this.lanzamiento = date;
	}

	public String getPegi() {
		return pegi;
	}

	public void setPegi(String pegi) {
		this.pegi = pegi;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public boolean isAlquilado() {
		return alquilado;
	}

	public void setAlquilado(boolean alquilado) {
		this.alquilado = alquilado;
	}

	public String getCaratula() {
		return caratula;
	}

	public void setCaratula(String caratula) {
		this.caratula = caratula;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "JuegosModel [id=" + id + ", titulo=" + titulo + ", descripcion=" + descripcion + ", lanzamiento="
				+ lanzamiento + ", pegi=" + pegi + ", tipo=" + tipo + ", alquilado=" + alquilado + ", caratula="
				+ caratula + ", precio=" + precio + ", stock=" + stock + ", categorias=" + Arrays.toString(categorias)
				+ ", plataformas=" + Arrays.toString(plataformas) + "]";
	}

	public JuegosModel(int id, String titulo, String descripcion, Date lanzamiento, String pegi, String tipo,
			boolean alquilado, String caratula, int precio, int stock, String[] categorias, String[] plataformas) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.lanzamiento = lanzamiento;
		this.pegi = pegi;
		this.tipo = tipo;
		this.alquilado = alquilado;
		this.caratula = caratula;
		this.precio = precio;
		this.stock = stock;
		this.categorias = categorias;
		this.plataformas = plataformas;
	}

	public JuegosModel() {
		super();
	}
	
}

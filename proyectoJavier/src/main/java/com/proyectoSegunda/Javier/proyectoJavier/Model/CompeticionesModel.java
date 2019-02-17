package com.proyectoSegunda.Javier.proyectoJavier.Model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.proyectoSegunda.Javier.proyectoJavier.Entity.Participaciones;

public class CompeticionesModel {
	
	public int id;
	private String nombre;
	private Date fecha;
	private String lugar;
	private String descripcion;
    private List<Participaciones> participaciones = new ArrayList<Participaciones>();
    
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
	
	public Date getFecha() {
		return fecha;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public String getLugar() {
		return lugar;
	}
	
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public List<Participaciones> getParticipaciones() {
		return participaciones;
	}
	
	public void setParticipaciones(List<Participaciones> participaciones) {
		this.participaciones = participaciones;
	}
	
	public CompeticionesModel(int id, String nombre, Date fecha, String lugar, String descripcion,
			List<Participaciones> participaciones) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fecha = fecha;
		this.lugar = lugar;
		this.descripcion = descripcion;
		this.participaciones = participaciones;
	}
	
	public CompeticionesModel() {
		super();
	}
	
	@Override
	public String toString() {
		return "CompeticionesModel [id=" + id + ", nombre=" + nombre + ", fecha=" + fecha + ", lugar=" + lugar
				+ ", descripcion=" + descripcion + ", participaciones=" + participaciones + "]";
	}
    
    
}

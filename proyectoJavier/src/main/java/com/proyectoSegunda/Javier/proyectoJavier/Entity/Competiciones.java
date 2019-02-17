package com.proyectoSegunda.Javier.proyectoJavier.Entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="competiciones")
public class Competiciones {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", unique=true, nullable=false)
	public int id;
	
	@Column(name="nombre", nullable=false)
	private String nombre;

	@Column(name="fecha", nullable=false)
	private Date fecha;
	
	@Column(name="lugar", nullable=false)
	private String lugar;
	
	@Column(name="descripcion", nullable=false)
	private String descripcion;
	
	@OneToMany(mappedBy = "competicion",cascade=CascadeType.ALL)
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

	public Competiciones(int id, String nombre, Date fecha, String lugar, String descripcion,
			List<Participaciones> participaciones) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fecha = fecha;
		this.lugar = lugar;
		this.descripcion = descripcion;
		this.participaciones = participaciones;
	}

	public Competiciones() {
		super();
	}

	@Override
	public String toString() {
		return "Competiciones [id=" + id + ", nombre=" + nombre + ", fecha=" + fecha + ", lugar=" + lugar
				+ ", descripcion=" + descripcion + "]";
	}
	
}

package com.proyectoSegunda.Javier.proyectoJavier.Model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.proyectoSegunda.Javier.proyectoJavier.Entity.Juegos;
import com.proyectoSegunda.Javier.proyectoJavier.Entity.User;

public class ventasModel {
	
	private int id;
    private Date fecha;
    private Juegos juego;
    private User user;
    
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Date getFecha() {
		return fecha;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public Juegos getJuego() {
		return juego;
	}
	
	public void setJuego(Juegos juego) {
		this.juego = juego;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public String toString() {
		return "ventasModel [id=" + id + ", fecha=" + fecha + ", juego=" + juego + ", user=" + user + "]";
	}
	
	public ventasModel(int id, Date fecha, Juegos juego, User user) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.juego = juego;
		this.user = user;
	}
	
	public ventasModel() {
		super();
	}
    
    
}

package com.proyectoSegunda.Javier.proyectoJavier.Model;

import com.proyectoSegunda.Javier.proyectoJavier.Entity.Competiciones;
import com.proyectoSegunda.Javier.proyectoJavier.Entity.User;

public class ParticipacionesModel {

	private int id;
    private int posicion;
    private Competiciones competicion;  
    private User user;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPosicion() {
		return posicion;
	}
	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}
	public Competiciones getCompeticion() {
		return competicion;
	}
	public void setCompeticion(Competiciones competicion) {
		this.competicion = competicion;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public ParticipacionesModel(int id, int posicion, Competiciones competicion, User user) {
		super();
		this.id = id;
		this.posicion = posicion;
		this.competicion = competicion;
		this.user = user;
	}
	
	public ParticipacionesModel() {
		super();
	}
	
	@Override
	public String toString() {
		return "ParticipacionesModel [id=" + id + ", posicion=" + posicion + ", competicion=" + competicion + ", user="
				+ user + "]";
	}
    
	
    
}

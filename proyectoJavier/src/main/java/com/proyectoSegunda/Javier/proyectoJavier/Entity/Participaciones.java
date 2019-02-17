package com.proyectoSegunda.Javier.proyectoJavier.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="participaciones")
public class Participaciones {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="posicion")
    private int posicion;
	
    @ManyToOne
    @JoinColumn(name = "competicion")
    private Competiciones competicion;  

    @ManyToOne
    @JoinColumn(name = "user")
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

	public Participaciones(int id, int posicion, Competiciones competicion, User user) {
		super();
		this.id = id;
		this.posicion = posicion;
		this.competicion = competicion;
		this.user = user;
	}

	public Participaciones() {
		super();
	}

	@Override
	public String toString() {
		return "Participaciones [id=" + id + ", posicion=" + posicion + ", competicion=" + competicion + ", user="
				+ user + "]";
	}
    
    
}

package com.proyectoSegunda.Javier.proyectoJavier.Entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="ventas")
public class Ventas {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="fecha")
    private Date fecha;
	
    @ManyToOne
    @JoinColumn(name = "idjuego")
    private Juegos juego;
    
    public Juegos getJuego() {
		return juego;
	}

	public void setJuego(Juegos juego) {
		this.juego = juego;
	}

    @ManyToOne
    @JoinColumn(name = "username")
    private User user;
    
    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

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

	@Override
	public String toString() {
		return "Ventas [id=" + id + ", fecha=" + fecha + "]";
	}

	public Ventas(int id, Date fecha, Juegos juego, User user) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.juego = juego;
		this.user = user;
	}

	public Ventas() {
		super();
	}	
    
}

package com.proyectoSegunda.Javier.proyectoJavier.Entity;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="juegos")
public class Juegos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", unique=true, nullable=false)
	public int id;
	
	@Column(name="titulo", nullable=false, length=60)
	private String titulo;
	
	@Column(name="stock", nullable=false)
	private int stock;
	
	@Column(name="descripcion", nullable=false, length=900)
	private String descripcion;
	
	@Column(name="lanzamiento",nullable=true)
	private Date lanzamiento;
	
	@Column(name="pegi", nullable=false, length=10)
	private String pegi;
	
	@Column(name="tipo", nullable=false, length=40)
	private String tipo;

	@Column(name="alquilado")
	private boolean alquilado;

	@Column(name="caratula", nullable=false, length=40)
	private String caratula;
	
	@Column(name="precio", nullable=false)
	private int precio;
	
	@OneToMany(mappedBy = "juego",cascade=CascadeType.ALL)
    private List<Ventas> Ventas = new ArrayList<Ventas>();
	
	@OneToMany(mappedBy = "juego",cascade=CascadeType.ALL)
    private List<Alquileres> Alquileres = new ArrayList<Alquileres>();
	
	 @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
		    @JoinTable(name = "juegoCategorias",
		        joinColumns = @JoinColumn(name = "idJuegos"),
		        inverseJoinColumns = @JoinColumn(name = "idCategorias")
		    )
	private List<Categorias> categorias = new ArrayList<>();
	
	 @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
		    @JoinTable(name = "juegoPlataforma",
		        joinColumns = @JoinColumn(name = "idJuegos"),
		        inverseJoinColumns = @JoinColumn(name = "idPlataformas")
		    )
	private List<Plataformas> plataformas = new ArrayList<>();
	 
	

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

	public List<Categorias> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categorias> categorias) {
		this.categorias = categorias;
	}

	public List<Plataformas> getPlataformas() {
		return plataformas;
	}

	public void setPlataformas(List<Plataformas> plataformas) {
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

	public void setLanzamiento(Date lanzamiento) {
		this.lanzamiento = lanzamiento;
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
	
	public List<Ventas> getVentas() {
		return Ventas;
	}

	public void setVentas(List<Ventas> ventas) {
		Ventas = ventas;
	}

	public Juegos(int id, String titulo, int stock, String descripcion, Date lanzamiento, String pegi, String tipo,
			boolean alquilado, String caratula, int precio, List<Categorias> categorias,
			List<Plataformas> plataformas) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.stock = stock;
		this.descripcion = descripcion;
		this.lanzamiento = lanzamiento;
		this.pegi = pegi;
		this.tipo = tipo;
		this.alquilado = alquilado;
		this.caratula = caratula;
		this.precio = precio;
		this.categorias = categorias;
		this.plataformas = plataformas;
	}

	public Juegos() {
		super();
	}

	@Override
	public String toString() {
		return "Juegos [id=" + id + ", titulo=" + titulo + ", stock=" + stock + ", descripcion=" + descripcion
				+ ", lanzamiento=" + lanzamiento + ", pegi=" + pegi + ", tipo=" + tipo + ", alquilado=" + alquilado
				+ ", caratula=" + caratula + ", precio=" + precio + ", Ventas=" + Ventas + ", categorias=" + categorias
				+ ", plataformas=" + plataformas + "]";
	}

	public List<Alquileres> getAlquileres() {
		return Alquileres;
	}

	public void setAlquileres(List<Alquileres> alquileres) {
		Alquileres = alquileres;
	}

}




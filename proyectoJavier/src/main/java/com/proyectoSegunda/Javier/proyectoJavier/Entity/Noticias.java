package com.proyectoSegunda.Javier.proyectoJavier.Entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="noticias")
public class Noticias {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", unique=true, nullable=false)
	public int id;
	
	@Column(name="titulo", nullable=false, length=60)
	private String titulo;
	
	@Column(name="resumen", nullable=false, length=120)
	private String resumen;
	
	@Column(name="cuerpo", nullable=false, length=900)
	private String cuerpo;
	
	@Column(name="foto", nullable=false, length=900)
	private String foto;

	
	public int getId() {
		return id;
	}

	public void setId(int noticiaId) {
		this.id = noticiaId;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getResumen() {
		return resumen;
	}

	public void setResumen(String resumen) {
		this.resumen = resumen;
	}

	public String getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}
	
	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	@Override
	public String toString() {
		return "Noticias [id=" + id + ", titulo=" + titulo + ", resumen=" + resumen + ", cuerpo=" + cuerpo + ", foto="
				+ foto + "]";
	}

	public Noticias(int id, String titulo, String resumen, String cuerpo, String foto) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.resumen = resumen;
		this.cuerpo = cuerpo;
		this.foto = foto;
	}

	public Noticias() {
		super();
	}

	
	
}



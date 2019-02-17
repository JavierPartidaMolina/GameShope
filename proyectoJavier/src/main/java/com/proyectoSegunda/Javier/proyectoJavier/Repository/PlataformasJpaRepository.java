package com.proyectoSegunda.Javier.proyectoJavier.Repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyectoSegunda.Javier.proyectoJavier.Entity.Noticias;
import com.proyectoSegunda.Javier.proyectoJavier.Entity.Plataformas;

@Repository("PlataformasJpaRepository")
public interface PlataformasJpaRepository extends JpaRepository<Plataformas, Serializable>{
	
	public abstract Plataformas findByNombre(String nombre);
	public abstract Plataformas findById(int id);
}


package com.proyectoSegunda.Javier.proyectoJavier.Repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.proyectoSegunda.Javier.proyectoJavier.Entity.Categorias;

@Repository("CategoriasJpaRepository")
public interface CategoriasJpaRepository extends JpaRepository<Categorias, Serializable>{
	
	public abstract Categorias findByNombre(String nombre);
	public abstract Categorias findById(int id);
}
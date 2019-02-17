package com.proyectoSegunda.Javier.proyectoJavier.Repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyectoSegunda.Javier.proyectoJavier.Entity.Categorias;
import com.proyectoSegunda.Javier.proyectoJavier.Entity.Juegos;

@Repository("JuegosJpaRepository")
public interface JuegosJpaRepository extends JpaRepository<Juegos, Serializable>{

	public abstract Juegos findById(int id);
}

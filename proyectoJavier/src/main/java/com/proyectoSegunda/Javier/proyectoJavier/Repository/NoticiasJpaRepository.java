package com.proyectoSegunda.Javier.proyectoJavier.Repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyectoSegunda.Javier.proyectoJavier.Entity.Noticias;

@Repository("NoticiasJpaRepository")
public interface NoticiasJpaRepository extends JpaRepository<Noticias, Serializable> {
	
	public abstract Noticias findById(int id);
	
}


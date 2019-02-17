package com.proyectoSegunda.Javier.proyectoJavier.Repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyectoSegunda.Javier.proyectoJavier.Entity.Ventas;

@Repository("ventasJpaReposotory")
public interface ventasJpaReposotory extends JpaRepository<Ventas, Serializable> {
	public abstract Ventas findByUser(String name);
	public abstract Ventas findById(int id);
}

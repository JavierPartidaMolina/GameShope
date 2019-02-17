package com.proyectoSegunda.Javier.proyectoJavier.Repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.proyectoSegunda.Javier.proyectoJavier.Entity.Alquileres;

@Repository("alquileresJpaRepository")
public interface alquileresJpaRepository extends JpaRepository<Alquileres, Serializable> {
	public abstract Alquileres findByUser(String name);
	public abstract Alquileres findById(int id);
}

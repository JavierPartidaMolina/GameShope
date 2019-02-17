package com.proyectoSegunda.Javier.proyectoJavier.Repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyectoSegunda.Javier.proyectoJavier.Entity.Competiciones;

@Repository("CompeticionesJpaRepository")
public interface CompeticionesJpaRepository extends JpaRepository<Competiciones, Serializable> {
	
		public abstract Competiciones findById(int id);
}

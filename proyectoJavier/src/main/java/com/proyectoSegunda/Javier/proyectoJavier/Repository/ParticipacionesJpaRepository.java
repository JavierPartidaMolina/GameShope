package com.proyectoSegunda.Javier.proyectoJavier.Repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyectoSegunda.Javier.proyectoJavier.Controller.UserController;
import com.proyectoSegunda.Javier.proyectoJavier.Entity.Competiciones;
import com.proyectoSegunda.Javier.proyectoJavier.Entity.Participaciones;
import com.proyectoSegunda.Javier.proyectoJavier.Entity.User;

@Repository("ParticipacionesJpaRepository")
public interface ParticipacionesJpaRepository extends JpaRepository<Participaciones, Serializable> {
	
	public abstract Participaciones findById(int id);
	public abstract Participaciones findByUser(User user);
}

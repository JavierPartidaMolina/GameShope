package com.proyectoSegunda.Javier.proyectoJavier.Service;

import java.io.IOException;
import java.util.List;

import com.proyectoSegunda.Javier.proyectoJavier.Entity.Alquileres;
import com.proyectoSegunda.Javier.proyectoJavier.Model.JuegosModel;
import com.proyectoSegunda.Javier.proyectoJavier.Model.alquileresModel;

public interface alquileresService {
	
	public abstract Alquileres findAlquilersByUser(String username);
	public abstract List<alquileresModel> ListAllAlquileres();
	public abstract alquileresModel findAlquileressByUserModel(String username);
	public abstract Alquileres addAlquiler(alquileresModel alquileresModel, JuegosModel juego);
	public abstract void devolver(int alquiler_id) throws IOException;
	public abstract List<alquileresModel> ListAlquileresUser();
}

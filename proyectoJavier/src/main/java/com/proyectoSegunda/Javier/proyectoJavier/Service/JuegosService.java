package com.proyectoSegunda.Javier.proyectoJavier.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.proyectoSegunda.Javier.proyectoJavier.Entity.Juegos;
import com.proyectoSegunda.Javier.proyectoJavier.Model.JuegosModel;

public interface JuegosService {
	
	public abstract List<JuegosModel> listAllJuegos();
	public abstract Juegos addJuegos(JuegosModel Juegos);
	public abstract void removeJuegos(int id) throws IOException;
	public abstract JuegosModel findJuegosByJuegosIdModel(int juegos_id);
	public abstract Juegos findJuegosByJuegosId(int juegos_id);
	public abstract Juegos findJuegosByTitulo(String titulo);
	public abstract JuegosModel findJuegosByTituloModel(String titulo);
	public abstract void savePhoto(MultipartFile file) throws IOException;
	List<JuegosModel> listAlquilerJuegos();
	List<JuegosModel> listVentaJuegos();
	
}

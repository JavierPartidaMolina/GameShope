package com.proyectoSegunda.Javier.proyectoJavier.Service;

import java.io.IOException;
import java.util.List;

import com.proyectoSegunda.Javier.proyectoJavier.Entity.Ventas;
import com.proyectoSegunda.Javier.proyectoJavier.Model.JuegosModel;
import com.proyectoSegunda.Javier.proyectoJavier.Model.ventasModel;

public interface ventasService {
	
	public abstract Ventas findVentassByUser(String username);
	public abstract List<ventasModel> ListAllVentas();
	public abstract ventasModel findVentassByUserModel(String username);
	public abstract Ventas addVentas(ventasModel ventasmodel, JuegosModel juego);
	public abstract void devolver(int venta_id) throws IOException;
	public abstract List<ventasModel> ListComprasUser();
}

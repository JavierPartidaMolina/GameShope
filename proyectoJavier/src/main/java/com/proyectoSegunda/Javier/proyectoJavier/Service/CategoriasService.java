package com.proyectoSegunda.Javier.proyectoJavier.Service;

import java.io.IOException;
import java.util.List;

import com.proyectoSegunda.Javier.proyectoJavier.Entity.Categorias;
import com.proyectoSegunda.Javier.proyectoJavier.Model.CategoriasModel;

public interface CategoriasService {
	public abstract List<CategoriasModel> listAllCategorias();
	public abstract Categorias addCategorias(CategoriasModel Categorias);
	public abstract void removeCategorias(int Categorias_id) throws IOException;
	public abstract CategoriasModel findCategoriasByCategoriasIdModel(int Categorias_id);
	public abstract Categorias findCategoriasByCategoriasId(int Categoriass_id);
}

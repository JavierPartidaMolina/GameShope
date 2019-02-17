package com.proyectoSegunda.Javier.proyectoJavier.Converter;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

import com.proyectoSegunda.Javier.proyectoJavier.Entity.Categorias;
import com.proyectoSegunda.Javier.proyectoJavier.Model.CategoriasModel;

@Component("CategoriasConverter")
public class CategoriasConverter {
	
	DozerBeanMapper mapper = new DozerBeanMapper();
	
	public CategoriasModel entity2model(Categorias categoria) {
		return mapper.map(categoria,CategoriasModel.class);
	}
	public Categorias model2entity(CategoriasModel categoriaModel) {
		return mapper.map(categoriaModel, Categorias.class);
	}
}

package com.proyectoSegunda.Javier.proyectoJavier.Service.Impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.proyectoSegunda.Javier.proyectoJavier.Converter.CategoriasConverter;
import com.proyectoSegunda.Javier.proyectoJavier.Entity.Categorias;
import com.proyectoSegunda.Javier.proyectoJavier.Model.CategoriasModel;
import com.proyectoSegunda.Javier.proyectoJavier.Repository.CategoriasJpaRepository;
import com.proyectoSegunda.Javier.proyectoJavier.Service.CategoriasService;

@Service("CategoriasServiceImpl")
public class CategoriasServiceImpl implements CategoriasService{

	@Autowired
	@Qualifier("CategoriasConverter")
	private CategoriasConverter CategoriasConverter;
	
	@Autowired
	@Qualifier("CategoriasJpaRepository")
	private CategoriasJpaRepository CategoriasJpaRepository;
	
	@Override
	public List<CategoriasModel> listAllCategorias() {
		List<CategoriasModel> Categoriaslist = new ArrayList<>();
		CategoriasJpaRepository.findAll().forEach(Categorias ->{Categoriaslist.add(CategoriasConverter.entity2model(Categorias));});
		return Categoriaslist;
	}

	@Override
	public Categorias addCategorias(CategoriasModel Categorias) {
		Categorias categoria = new Categorias();
		categoria = CategoriasConverter.model2entity(Categorias);
		return CategoriasJpaRepository.save(categoria);
	}

	@Override
	public void removeCategorias(int Categorias_id) throws IOException {
		Categorias categoria = CategoriasJpaRepository.findById(Categorias_id);
		CategoriasJpaRepository.delete(categoria);
		
	}

	@Override
	public CategoriasModel findCategoriasByCategoriasIdModel(int Categorias_id) {
		return CategoriasConverter.entity2model(findCategoriasByCategoriasId(Categorias_id));
	}

	@Override
	public Categorias findCategoriasByCategoriasId(int Categorias_id) {
		Categorias categoria = CategoriasJpaRepository.findById(Categorias_id);
		return categoria;
	}

}

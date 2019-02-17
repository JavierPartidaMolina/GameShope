package com.proyectoSegunda.Javier.proyectoJavier.Service.Impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.proyectoSegunda.Javier.proyectoJavier.Converter.PlataformasConverter;
import com.proyectoSegunda.Javier.proyectoJavier.Entity.Plataformas;
import com.proyectoSegunda.Javier.proyectoJavier.Model.NoticiasModel;
import com.proyectoSegunda.Javier.proyectoJavier.Model.PlataformasModel;
import com.proyectoSegunda.Javier.proyectoJavier.Repository.PlataformasJpaRepository;
import com.proyectoSegunda.Javier.proyectoJavier.Service.PlataformasService;

@Service("PlataformasServiceImpl")
public class PlataformasServiceImpl implements PlataformasService{

	@Autowired
	@Qualifier("PlataformasConverter")
	private PlataformasConverter PlataformasConverter;
	
	@Autowired
	@Qualifier("PlataformasJpaRepository")
	private PlataformasJpaRepository PlataformasJpaRepository;
	
	@Override
	public List<PlataformasModel> listAllPlataformas() {
		List<PlataformasModel> plataformaslist = new ArrayList<>();
		PlataformasJpaRepository.findAll().forEach(plataformas ->{plataformaslist.add(PlataformasConverter.entity2model(plataformas));});
		return plataformaslist;
	}

	@Override
	public Plataformas addPlataformas(PlataformasModel plataformas) {
		Plataformas plataforma = new Plataformas();
		plataforma = PlataformasConverter.model2entity(plataformas);
		return PlataformasJpaRepository.save(plataforma);
	}

	@Override
	public void removePlataformas(int Plataformas_id) throws IOException {
		Plataformas plataforma = PlataformasJpaRepository.findById(Plataformas_id);
		PlataformasJpaRepository.delete(plataforma);
	}

	@Override
	public PlataformasModel findPlataformasByPlataformasIdModel(int Plataformas_id) {
		return PlataformasConverter.entity2model(findPlataformasByPlataformasId(Plataformas_id));
	}

	@Override
	public Plataformas findPlataformasByPlataformasId(int Plataformas_id) {
		Plataformas plataforma = PlataformasJpaRepository.findById(Plataformas_id);
		return plataforma;
	}

}

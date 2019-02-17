package com.proyectoSegunda.Javier.proyectoJavier.Service.Impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.proyectoSegunda.Javier.proyectoJavier.Converter.CompeticionesConverter;
import com.proyectoSegunda.Javier.proyectoJavier.Entity.Competiciones;
import com.proyectoSegunda.Javier.proyectoJavier.Model.CompeticionesModel;
import com.proyectoSegunda.Javier.proyectoJavier.Repository.CompeticionesJpaRepository;
import com.proyectoSegunda.Javier.proyectoJavier.Service.CompeticionesService;

@Service("CompeticionesServiceImpl")
public class CompeticionesServiceImpl implements CompeticionesService{

	@Autowired
	@Qualifier("CompeticionesConverter")
	private CompeticionesConverter CompeticionesConverter;
	
	@Autowired
	@Qualifier("CompeticionesJpaRepository")
	private CompeticionesJpaRepository CompeticionesJpaRepository;
	
	@Override
	public List<CompeticionesModel> listAllCompeticiones() {
		List<CompeticionesModel> Competicioneslist = new ArrayList<>();
		CompeticionesJpaRepository.findAll().forEach(Competiciones ->{Competicioneslist.add(CompeticionesConverter.entity2model(Competiciones));});
		return Competicioneslist;
	}

	@Override
	public Competiciones addCompeticiones(CompeticionesModel Competiciones) {
		Competiciones competicion = new Competiciones();
		competicion = CompeticionesConverter.model2entity(Competiciones);
		return CompeticionesJpaRepository.save(competicion);
	}

	@Override
	public void removeCompeticiones(int Competiciones_id) throws IOException {
		Competiciones competicion = CompeticionesJpaRepository.findById(Competiciones_id);
		CompeticionesJpaRepository.delete(competicion);
		
	}

	@Override
	public CompeticionesModel findCompeticionesByCompeticionesIdModel(int Competiciones_id) {
		return CompeticionesConverter.entity2model(findCompeticionesByCompeticionesId(Competiciones_id));
	}

	@Override
	public Competiciones findCompeticionesByCompeticionesId(int Competiciones_id) {
		Competiciones competicion = CompeticionesJpaRepository.findById(Competiciones_id);
		return competicion;
	}

}

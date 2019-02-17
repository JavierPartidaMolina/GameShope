package com.proyectoSegunda.Javier.proyectoJavier.Converter;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;
import com.proyectoSegunda.Javier.proyectoJavier.Entity.Competiciones;
import com.proyectoSegunda.Javier.proyectoJavier.Model.CompeticionesModel;

@Component("CompeticionesConverter")
public class CompeticionesConverter {

	DozerBeanMapper mapper = new DozerBeanMapper();
	
	public CompeticionesModel entity2model(Competiciones Competiciones) {
		return mapper.map(Competiciones,CompeticionesModel.class);
	}
	public Competiciones model2entity(CompeticionesModel CompeticionesModel) {
		return mapper.map(CompeticionesModel, Competiciones.class);
	}
}

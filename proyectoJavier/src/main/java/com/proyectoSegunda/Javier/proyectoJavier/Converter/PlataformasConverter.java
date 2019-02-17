package com.proyectoSegunda.Javier.proyectoJavier.Converter;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

import com.proyectoSegunda.Javier.proyectoJavier.Entity.Plataformas;
import com.proyectoSegunda.Javier.proyectoJavier.Model.PlataformasModel;

@Component("PlataformasConverter")
public class PlataformasConverter {

	DozerBeanMapper mapper = new DozerBeanMapper();
	
	public PlataformasModel entity2model(Plataformas plataforma) {
		return mapper.map(plataforma,PlataformasModel.class);
	}
	public Plataformas model2entity(PlataformasModel plataformaModel) {
		return mapper.map(plataformaModel, Plataformas.class);
	}
}

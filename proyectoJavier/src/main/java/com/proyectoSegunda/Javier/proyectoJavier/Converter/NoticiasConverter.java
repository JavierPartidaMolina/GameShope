package com.proyectoSegunda.Javier.proyectoJavier.Converter;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

import com.proyectoSegunda.Javier.proyectoJavier.Entity.Noticias;
import com.proyectoSegunda.Javier.proyectoJavier.Model.NoticiasModel;

@Component("NoticiasConverter")
public class NoticiasConverter {
	
	DozerBeanMapper mapper = new DozerBeanMapper();
	
	public NoticiasModel entity2model(Noticias noticias) {
		return mapper.map(noticias,NoticiasModel.class);
	}
	public Noticias model2entity(NoticiasModel noticiasmodel) {
		return mapper.map(noticiasmodel, Noticias.class);
	}
}
	

package com.proyectoSegunda.Javier.proyectoJavier.Converter;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

import com.proyectoSegunda.Javier.proyectoJavier.Entity.Alquileres;
import com.proyectoSegunda.Javier.proyectoJavier.Model.alquileresModel;

@Component("AlquileresConverter")
public class AlquileresConverter {

	DozerBeanMapper mapper = new DozerBeanMapper();
	
	public alquileresModel entity2model(Alquileres alquileres) {
		return mapper.map(alquileres,alquileresModel.class);
	}
	public Alquileres model2entity(alquileresModel alquileresmodel) {
		return mapper.map(alquileresmodel, Alquileres.class);
	}
}

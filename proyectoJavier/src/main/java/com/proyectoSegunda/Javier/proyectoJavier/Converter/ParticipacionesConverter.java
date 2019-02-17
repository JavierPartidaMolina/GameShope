package com.proyectoSegunda.Javier.proyectoJavier.Converter;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;
import com.proyectoSegunda.Javier.proyectoJavier.Entity.Participaciones;
import com.proyectoSegunda.Javier.proyectoJavier.Model.ParticipacionesModel;

@Component("ParticipacionesConverter")
public class ParticipacionesConverter {

	DozerBeanMapper mapper = new DozerBeanMapper();
	
	public ParticipacionesModel entity2model(Participaciones Participaciones) {
		return mapper.map(Participaciones,ParticipacionesModel.class);
	}
	public Participaciones model2entity(ParticipacionesModel ParticipacionesModel) {
		return mapper.map(ParticipacionesModel, Participaciones.class);
	}
}

package com.proyectoSegunda.Javier.proyectoJavier.Service;

import java.io.IOException;
import java.util.List;

import com.proyectoSegunda.Javier.proyectoJavier.Entity.Participaciones;
import com.proyectoSegunda.Javier.proyectoJavier.Model.ParticipacionesModel;

public interface ParticipacionesService {

	public abstract List<ParticipacionesModel> listAllParticipaciones();
	public abstract Participaciones addParticipaciones(ParticipacionesModel Participaciones);
	public abstract void removeParticipaciones(int Participaciones_id) throws IOException;
	public abstract ParticipacionesModel findParticipacionesByParticipacionesIdModel(int Participaciones_id);
	public abstract Participaciones findParticipacionesByParticipacionesId(int Participaciones_id);
	List<ParticipacionesModel> listAllParticipacionesUser();
}

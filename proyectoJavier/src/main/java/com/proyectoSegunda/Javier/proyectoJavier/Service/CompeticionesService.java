package com.proyectoSegunda.Javier.proyectoJavier.Service;

import java.io.IOException;
import java.util.List;

import com.proyectoSegunda.Javier.proyectoJavier.Entity.Competiciones;
import com.proyectoSegunda.Javier.proyectoJavier.Model.CompeticionesModel;

public interface CompeticionesService {

	public abstract List<CompeticionesModel> listAllCompeticiones();
	public abstract Competiciones addCompeticiones(CompeticionesModel Competiciones);
	public abstract void removeCompeticiones(int Competiciones_id) throws IOException;
	public abstract CompeticionesModel findCompeticionesByCompeticionesIdModel(int Competiciones_id);
	public abstract Competiciones findCompeticionesByCompeticionesId(int Competiciones_id);
}

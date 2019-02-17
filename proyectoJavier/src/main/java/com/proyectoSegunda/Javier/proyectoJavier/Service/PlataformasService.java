package com.proyectoSegunda.Javier.proyectoJavier.Service;

import java.io.IOException;
import java.util.List;


import com.proyectoSegunda.Javier.proyectoJavier.Entity.Plataformas;
import com.proyectoSegunda.Javier.proyectoJavier.Model.PlataformasModel;

public interface PlataformasService {
	
	public abstract List<PlataformasModel> listAllPlataformas();
	public abstract Plataformas addPlataformas(PlataformasModel plataformas);
	public abstract void removePlataformas(int Plataformas_id) throws IOException;
	public abstract PlataformasModel findPlataformasByPlataformasIdModel(int Plataformas_id);
	public abstract Plataformas findPlataformasByPlataformasId(int Plataformas_id);
}

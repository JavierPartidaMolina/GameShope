package com.proyectoSegunda.Javier.proyectoJavier.Service.Impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.proyectoSegunda.Javier.proyectoJavier.Converter.ParticipacionesConverter;
import com.proyectoSegunda.Javier.proyectoJavier.Entity.Participaciones;
import com.proyectoSegunda.Javier.proyectoJavier.Model.ParticipacionesModel;
import com.proyectoSegunda.Javier.proyectoJavier.Repository.ParticipacionesJpaRepository;
import com.proyectoSegunda.Javier.proyectoJavier.Repository.UsersJpaRepository;
import com.proyectoSegunda.Javier.proyectoJavier.Service.ParticipacionesService;

@Service("ParticipacionesServiceImpl")
public class ParticipacionesServiceImpl implements ParticipacionesService{
	
	@Autowired
	@Qualifier("ParticipacionesConverter")
	private ParticipacionesConverter ParticipacionesConverter;
	
	@Autowired
	@Qualifier("ParticipacionesJpaRepository")
	private ParticipacionesJpaRepository ParticipacionesJpaRepository;
	
	@Autowired
	@Qualifier("UsersJpaRepository")
	private UsersJpaRepository UsersJpaRepository;
	
	@Override
	public List<ParticipacionesModel> listAllParticipaciones() {
		List<ParticipacionesModel> Participacioneslist = new ArrayList<>();
		ParticipacionesJpaRepository.findAll().forEach(Participaciones ->{Participacioneslist.add(ParticipacionesConverter.entity2model(Participaciones));});
		return Participacioneslist;
	}
	
	@Override
	public List<ParticipacionesModel> listAllParticipacionesUser() {
		List<ParticipacionesModel> Participacioneslist = new ArrayList<>();
		ParticipacionesJpaRepository.findAll().forEach(Participaciones ->{
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if(Participaciones.getUser().getUsername().equals(user.getUsername())) {
				Participacioneslist.add(ParticipacionesConverter.entity2model(Participaciones));
			};
		});
		return Participacioneslist;
	}

	@Override
	public Participaciones addParticipaciones(ParticipacionesModel Participaciones) {
		Participaciones participacion = new Participaciones();
		participacion = ParticipacionesConverter.model2entity(Participaciones);
		return ParticipacionesJpaRepository.save(participacion);
	}

	@Override
	public void removeParticipaciones(int Participaciones_id) throws IOException {
		Participaciones participacion = ParticipacionesJpaRepository.findById(Participaciones_id);
		ParticipacionesJpaRepository.delete(participacion);
		
	}

	@Override
	public ParticipacionesModel findParticipacionesByParticipacionesIdModel(int Participaciones_id) {
		return ParticipacionesConverter.entity2model(findParticipacionesByParticipacionesId(Participaciones_id));
	}

	@Override
	public Participaciones findParticipacionesByParticipacionesId(int Participaciones_id) {
		Participaciones participacion = ParticipacionesJpaRepository.findById(Participaciones_id);
		return participacion;
	}
}

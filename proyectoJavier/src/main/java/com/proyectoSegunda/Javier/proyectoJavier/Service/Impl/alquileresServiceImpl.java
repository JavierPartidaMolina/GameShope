package com.proyectoSegunda.Javier.proyectoJavier.Service.Impl;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.proyectoSegunda.Javier.proyectoJavier.Converter.AlquileresConverter;
import com.proyectoSegunda.Javier.proyectoJavier.Converter.JuegosConverter;
import com.proyectoSegunda.Javier.proyectoJavier.Entity.Alquileres;
import com.proyectoSegunda.Javier.proyectoJavier.Entity.Juegos;
import com.proyectoSegunda.Javier.proyectoJavier.Model.JuegosModel;
import com.proyectoSegunda.Javier.proyectoJavier.Model.alquileresModel;
import com.proyectoSegunda.Javier.proyectoJavier.Repository.JuegosJpaRepository;
import com.proyectoSegunda.Javier.proyectoJavier.Repository.alquileresJpaRepository;
import com.proyectoSegunda.Javier.proyectoJavier.Service.alquileresService;

@Service("alquileresServiceImpl")
public class alquileresServiceImpl implements alquileresService{

	@Autowired
	@Qualifier("JuegosConverter")
	private JuegosConverter JuegosConverter;
	
	@Autowired
	@Qualifier("JuegosJpaRepository")
	private JuegosJpaRepository JuegosJpaRepository;
	
	@Autowired
	@Qualifier("JuegosServiceImpl")
	private JuegosServiceImpl JuegosServiceImpl;
	
	@Autowired
	@Qualifier("UsersServicePlusImpl")
	private UsersServicePlusImpl UsersServicePlusImpl;
	
	@Autowired
	@Qualifier("AlquileresConverter")
	private AlquileresConverter AlquileresConverter;
	
	@Autowired
	@Qualifier("alquileresJpaRepository")
	private alquileresJpaRepository alquileresJpaRepository;
	
	@Override
	public Alquileres findAlquilersByUser(String username) {
		return alquileresJpaRepository.findByUser(username);
	}

	private static final Log LOG = LogFactory.getLog(alquileresServiceImpl.class);
	
	@Override
	public List<alquileresModel> ListAllAlquileres() {
		List<alquileresModel> alquilerlist = new ArrayList<>();
		alquileresJpaRepository.findAll().forEach(alquiler ->{alquilerlist.add(AlquileresConverter.entity2model(alquiler));});
		LOG.info(alquilerlist);
		return alquilerlist;
	}

	@Override
	public alquileresModel findAlquileressByUserModel(String username) {
		return AlquileresConverter.entity2model(findAlquilersByUser(username));
	}

	@Override
	public Alquileres addAlquiler(alquileresModel alquileresModel, JuegosModel juegos) {
		Alquileres alquiler = new Alquileres();
		alquiler = AlquileresConverter.model2entity(alquileresModel);
		Juegos juego = new Juegos();
		juego = JuegosConverter.model2entity(juegos);
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		com.proyectoSegunda.Javier.proyectoJavier.Entity.User user2 = UsersServicePlusImpl.findUsersByUsername(user.getUsername());
		alquiler.setJuego(juego);
		alquiler.setUser(user2);
		long time = System.currentTimeMillis();
		alquiler.setFecha(new Date(time));
		juego.setAlquilado(true);
		JuegosJpaRepository.save(juego);
		return alquileresJpaRepository.save(alquiler);
	}

	@Override
	public void devolver(int alquiler_id) throws IOException {
		Alquileres alquiler = alquileresJpaRepository.findById(alquiler_id);
		Juegos juego = JuegosServiceImpl.findJuegosByJuegosId(alquiler.getJuego().getId());
		juego.setAlquilado(false);
		alquileresJpaRepository.delete(alquiler);
	}

	@Override
	public List<alquileresModel> ListAlquileresUser() {
		List<alquileresModel> alquilerlist = new ArrayList<>();
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		alquileresJpaRepository.findAll().forEach(alquiler ->{
			LOG.info(alquiler.getUser());
			if(alquiler.getUser().getUsername().equals(user.getUsername())) {
				alquilerlist.add(AlquileresConverter.entity2model(alquiler));
			}
		});
		LOG.info(alquilerlist);
		return alquilerlist;
	}
	
	
	public boolean ControlarAlquileresUser() {
		List<alquileresModel> alquilerlist = new ArrayList<>();
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		alquileresJpaRepository.findAll().forEach(alquiler ->{
			LOG.info(alquiler.getUser());
			if(alquiler.getUser().getUsername().equals(user.getUsername())) {
				alquilerlist.add(AlquileresConverter.entity2model(alquiler));
			}
		});
		boolean resp = false;
		for (int i = 0; i < alquilerlist.size(); i++) {	
			long time = System.currentTimeMillis();
			Date fechaInicial=alquilerlist.get(i).getFecha();
			Date fechaFinal=  new Date(time);
			int dias=(int) ((fechaFinal.getTime()-fechaInicial.getTime())/86400000);
			if(dias > 3) {
				LOG.info("mayor");
				resp = true;
			}
		}
		
		return resp;
	}
}

package com.proyectoSegunda.Javier.proyectoJavier.Service.Impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.proyectoSegunda.Javier.proyectoJavier.Controller.JuegosController;
import com.proyectoSegunda.Javier.proyectoJavier.Converter.JuegosConverter;
import com.proyectoSegunda.Javier.proyectoJavier.Entity.Categorias;
import com.proyectoSegunda.Javier.proyectoJavier.Entity.Juegos;
import com.proyectoSegunda.Javier.proyectoJavier.Entity.Plataformas;
import com.proyectoSegunda.Javier.proyectoJavier.Model.CategoriasModel;
import com.proyectoSegunda.Javier.proyectoJavier.Model.JuegosModel;
import com.proyectoSegunda.Javier.proyectoJavier.Repository.JuegosJpaRepository;
import com.proyectoSegunda.Javier.proyectoJavier.Service.JuegosService;

@Service("JuegosServiceImpl")
public class JuegosServiceImpl implements JuegosService{

	private static final Log LOG = LogFactory.getLog(JuegosController.class);
	
	@Autowired
	@Qualifier("JuegosConverter")
	private JuegosConverter JuegosConverter;
	
	@Autowired
	@Qualifier("JuegosJpaRepository")
	private JuegosJpaRepository JuegosJpaRepository;
	
	@Override
	public List<JuegosModel> listAllJuegos() {
		List<JuegosModel> JuegosList = new ArrayList<>();
		LOG.info("FIND ALL" + JuegosJpaRepository.findAll());
		JuegosJpaRepository.findAll().forEach(Juegos ->{JuegosList.add(JuegosConverter.entity2model(Juegos));});
		LOG.info("ARRAY ALL" + JuegosList);
		return JuegosList;
	}
	
	@Override
	public List<JuegosModel> listAlquilerJuegos() {
		List<JuegosModel> JuegosList = new ArrayList<>();
		LOG.info("FIND ALL" + JuegosJpaRepository.findAll());
		JuegosJpaRepository.findAll().forEach(Juegos ->{
			
			if(Juegos.getTipo().equals("1") && !Juegos.isAlquilado() ) {
			JuegosList.add(JuegosConverter.entity2model(Juegos));
			}
		});
		LOG.info("ARRAY ALL" + JuegosList);
		return JuegosList;
	}

	@Override
	public List<JuegosModel> listVentaJuegos() {
		List<JuegosModel> JuegosList = new ArrayList<>();
		LOG.info("FIND ALL" + JuegosJpaRepository.findAll());
		JuegosJpaRepository.findAll().forEach(Juegos ->{
			
			if(Juegos.getTipo().equals("0") && Juegos.getStock()>0 ) {
			JuegosList.add(JuegosConverter.entity2model(Juegos));
			}
		});
		LOG.info("ARRAY ALL" + JuegosList);
		return JuegosList;
	}
	
	@Override
	public Juegos addJuegos(JuegosModel Juegos) {
		Juegos juego = new Juegos();
		juego = JuegosConverter.model2entity(Juegos);
		return JuegosJpaRepository.save(juego);
	}

	@Override
	public void removeJuegos(int id) throws IOException {
		Juegos Juegos = JuegosJpaRepository.findById(id);
		JuegosJpaRepository.delete(Juegos);
		
	}

	@Override
	public JuegosModel findJuegosByJuegosIdModel(int juegos_id) {
		return JuegosConverter.entity2model(findJuegosByJuegosId(juegos_id));
	}

	@Override
	public Juegos findJuegosByJuegosId(int juegos_id) {
		Juegos juego = JuegosJpaRepository.findById(juegos_id);
		return juego;

	}

	@Override
	public Juegos findJuegosByTitulo(String titulo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JuegosModel findJuegosByTituloModel(String titulo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void savePhoto(MultipartFile file) throws IOException {
		if(!file.isEmpty()) {
			byte[] bytes = file.getBytes();
			String ruta = ".//src//main//resources//static//images//";
			Path path = Paths.get(ruta + file.getOriginalFilename());
			Files.write(path, bytes);

		}	
		
	}

}

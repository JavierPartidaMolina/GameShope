package com.proyectoSegunda.Javier.proyectoJavier.Converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.proyectoSegunda.Javier.proyectoJavier.Controller.JuegosController;
import com.proyectoSegunda.Javier.proyectoJavier.Entity.Categorias;
import com.proyectoSegunda.Javier.proyectoJavier.Entity.Juegos;
import com.proyectoSegunda.Javier.proyectoJavier.Entity.Plataformas;
import com.proyectoSegunda.Javier.proyectoJavier.Model.JuegosModel;
import com.proyectoSegunda.Javier.proyectoJavier.Repository.CategoriasJpaRepository;
import com.proyectoSegunda.Javier.proyectoJavier.Repository.PlataformasJpaRepository;

@Component("JuegosConverter")
public class JuegosConverter {
	
	private static final Log LOG = LogFactory.getLog(JuegosController.class);

	
	@Autowired
	@Qualifier("CategoriasJpaRepository")
	private CategoriasJpaRepository CategoriasJpaRepository;
	
	@Autowired
	@Qualifier("PlataformasJpaRepository")
	private PlataformasJpaRepository PlataformasJpaRepository;
	
	public JuegosModel entity2model(Juegos juegos) {
		JuegosModel juegomodel = new JuegosModel();
		juegomodel.setId(juegos.getId());
		juegomodel.setTitulo(juegos.getTitulo());
		juegomodel.setTipo(juegos.getTipo());
		juegomodel.setPrecio(juegos.getPrecio());
		juegomodel.setPegi(juegos.getPegi());
		juegomodel.setLanzamiento(juegos.getLanzamiento());
		juegomodel.setDescripcion(juegos.getDescripcion());
		juegomodel.setCaratula(juegos.getCaratula());
		juegomodel.setAlquilado(juegos.isAlquilado());
		juegomodel.setStock(juegos.getStock());
		
		List<Categorias> categorias = juegos.getCategorias();  
		String[] arrayCategorias = new String[categorias.size()];
		
		for (int i = 0; i <categorias.size(); i++) {
			Categorias categoria = categorias.get(i);
			
			arrayCategorias[i] = categoria.getNombre();
		}
		
		juegomodel.setCategorias(arrayCategorias);
		
		List<Plataformas> plataformas = juegos.getPlataformas();  
		String[] arrayPlataformas = new String[plataformas.size()];
		
		for (int i = 0; i <plataformas.size(); i++) {
			Plataformas plataforma = plataformas.get(i);
			arrayPlataformas[i] = plataforma.getNombre();
		}
		
		juegomodel.setPlataformas(arrayPlataformas);
		
		return juegomodel;
	}
	public Juegos model2entity(JuegosModel juegosmodel) {
		Juegos juego = new Juegos();
		juego.setId(juegosmodel.getId());
		juego.setTitulo(juegosmodel.getTitulo());
		juego.setTipo(juegosmodel.getTipo());
		juego.setPrecio(juegosmodel.getPrecio());
		juego.setPegi(juegosmodel.getPegi());
		juego.setLanzamiento(juegosmodel.getLanzamiento());
		juego.setDescripcion(juegosmodel.getDescripcion());
		juego.setCaratula(juegosmodel.getCaratula());
		juego.setAlquilado(juegosmodel.isAlquilado());
		juego.setStock(juegosmodel.getStock());
		
		List<Categorias> categorias = new ArrayList<Categorias>();  
		String[] arrayCategorias = juegosmodel.getCategorias();
		for (int i = 0; i < arrayCategorias.length; i++) {
			String name = arrayCategorias[i];
			
			categorias.add(CategoriasJpaRepository.findByNombre(name));
		}
		
		juego.setCategorias(categorias);
		
		List<Plataformas> plataformas = new ArrayList<Plataformas>();  
		String[] arrayPlataformas = juegosmodel.getPlataformas();
		
		for (int i = 0; i <arrayPlataformas.length; i++) {
			String name = arrayPlataformas[i];
			plataformas.add(PlataformasJpaRepository.findByNombre(name));
		}	

		
		juego.setPlataformas(plataformas);
		
		return juego;
		
	}
}

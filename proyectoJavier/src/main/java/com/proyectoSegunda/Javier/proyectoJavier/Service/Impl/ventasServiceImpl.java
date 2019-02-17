package com.proyectoSegunda.Javier.proyectoJavier.Service.Impl;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.proyectoSegunda.Javier.proyectoJavier.Controller.JuegosController;
import com.proyectoSegunda.Javier.proyectoJavier.Converter.JuegosConverter;
import com.proyectoSegunda.Javier.proyectoJavier.Converter.VentasConverter;
import com.proyectoSegunda.Javier.proyectoJavier.Entity.Juegos;
import com.proyectoSegunda.Javier.proyectoJavier.Entity.Ventas;
import com.proyectoSegunda.Javier.proyectoJavier.Model.JuegosModel;
import com.proyectoSegunda.Javier.proyectoJavier.Model.ventasModel;
import com.proyectoSegunda.Javier.proyectoJavier.Repository.JuegosJpaRepository;
import com.proyectoSegunda.Javier.proyectoJavier.Repository.ventasJpaReposotory;
import com.proyectoSegunda.Javier.proyectoJavier.Service.ventasService;

@Service("ventasServiceImpl")
public class ventasServiceImpl implements ventasService{

	private static final Log LOG = LogFactory.getLog(JuegosController.class);
	
	@Autowired
	@Qualifier("ventasJpaReposotory")
	private ventasJpaReposotory ventasJpaRepository;
	
	@Autowired
	@Qualifier("VentasConverter")
	private VentasConverter ventasConverter;
	
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
	
	@Override
	public Ventas findVentassByUser(String username) {
		return ventasJpaRepository.findByUser(username);
	}

	@Override
	public List<ventasModel> ListAllVentas() {
		List<ventasModel> ventaslist = new ArrayList<>();
		ventasJpaRepository.findAll().forEach(venta ->{ventaslist.add(ventasConverter.entity2model(venta));});
		LOG.info(ventaslist);
		return ventaslist;
	}

	@Override
	public ventasModel findVentassByUserModel(String username) {
		return ventasConverter.entity2model(findVentassByUser(username));
	}

	@Override
	public Ventas addVentas(ventasModel ventasmodel, JuegosModel juegos) {
		Ventas venta = new Ventas();
		venta = ventasConverter.model2entity(ventasmodel);
		Juegos juego = new Juegos();
		juego = JuegosConverter.model2entity(juegos);
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		com.proyectoSegunda.Javier.proyectoJavier.Entity.User user2 = UsersServicePlusImpl.findUsersByUsername(user.getUsername());
		venta.setJuego(juego);
		venta.setUser(user2);
		long time = System.currentTimeMillis();
		venta.setFecha(new Date(time));
		juego.setStock(juego.getStock()-1);
		JuegosJpaRepository.save(juego);
		return ventasJpaRepository.save(venta);
	}

	@Override
	public void devolver(int venta_id) throws IOException {
		Ventas Ventas = ventasJpaRepository.findById(venta_id);
		Juegos juego = JuegosServiceImpl.findJuegosByJuegosId(Ventas.getJuego().getId());
		juego.setStock(juego.getStock()+1);
		ventasJpaRepository.delete(Ventas);
	}

	@Override
	public List<ventasModel> ListComprasUser() {
		List<ventasModel> ventaslist = new ArrayList<>();
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ventasJpaRepository.findAll().forEach(venta ->{
			LOG.info(venta.getUser());
			if(venta.getUser().getUsername().equals(user.getUsername())) {
			ventaslist.add(ventasConverter.entity2model(venta));
			}
		});
		LOG.info(ventaslist);
		return ventaslist;
	}
	

}

package com.proyectoSegunda.Javier.proyectoJavier.Controller;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.proyectoSegunda.Javier.proyectoJavier.Constants.views;
import com.proyectoSegunda.Javier.proyectoJavier.Model.JuegosModel;
import com.proyectoSegunda.Javier.proyectoJavier.Service.Impl.CategoriasServiceImpl;
import com.proyectoSegunda.Javier.proyectoJavier.Service.Impl.JuegosServiceImpl;
import com.proyectoSegunda.Javier.proyectoJavier.Service.Impl.PlataformasServiceImpl;

@Controller
@RequestMapping("/juegos")
public class JuegosController {

	private static final Log LOG = LogFactory.getLog(JuegosController.class);

	
	@Autowired
	@Qualifier("JuegosServiceImpl")
	private JuegosServiceImpl JuegosServiceImpl;
	
	@Autowired
	@Qualifier("CategoriasServiceImpl")
	private CategoriasServiceImpl CategoriasServiceImpl;
	
	@Autowired
	@Qualifier("PlataformasServiceImpl")
	private PlataformasServiceImpl PlataformasServiceImpl;
	
	@GetMapping("/listado")
	public ModelAndView show() {
		LOG.info("MOSTRANDO LOS JUEGOS VIEW: LIST_JUEGOS");
		ModelAndView mav = new ModelAndView(views.LIST_JUEGOS);
		mav.addObject("juegos", JuegosServiceImpl.listAllJuegos());
		LOG.info(JuegosServiceImpl.listAllJuegos());
		return mav;
	}
	
	@GetMapping("/alquiler")
	public ModelAndView alquiler() {
		LOG.info("MOSTRANDO LOS JUEGOS PARA ALQUILAR VIEW: LIST_ALQUILER");
		ModelAndView mav = new ModelAndView(views.LIST_ALQUILER);
		mav.addObject("juegos", JuegosServiceImpl.listAlquilerJuegos());
		LOG.info(JuegosServiceImpl.listAlquilerJuegos());
		return mav;
	}
	
	@GetMapping("/venta")
	public ModelAndView venta() {
		LOG.info("MOSTRANDO LOS JUEGOS PARA VENTA VIEW: LIST_VENTA");
		ModelAndView mav = new ModelAndView(views.LIST_VENTA);
		mav.addObject("juegos", JuegosServiceImpl.listVentaJuegos());
		LOG.info(JuegosServiceImpl.listVentaJuegos());
		return mav;
	}
	
	@GetMapping("/eliminar/{id}")
	public ModelAndView delete_juego(@PathVariable(name="id") int id) throws IOException {
		LOG.info("ELIMINANDO JUEGO " + id );
		JuegosServiceImpl.removeJuegos(id);
		return show();
	}
	
	@GetMapping("/editar/{id}")
	public ModelAndView editar(Model model, @PathVariable(name="id") int id) {
		JuegosModel juego = new JuegosModel();
		LOG.info("EDITAR JUEGO " + id );
		ModelAndView mav = new ModelAndView(views.EDIT_JUEGOS);
		mav.addObject("categoria",CategoriasServiceImpl.listAllCategorias());
		mav.addObject("plataforma",PlataformasServiceImpl.listAllPlataformas());
		juego = JuegosServiceImpl.findJuegosByJuegosIdModel(id);
		model.addAttribute("juego", juego);
		return mav;
	}
	
	@GetMapping("/verJuego/{id}")
	public String verJuego(Model model, @PathVariable(name="id") Integer id) {
		LOG.info("VER JUEGO " + id );
		JuegosModel juego = new JuegosModel();
		juego = JuegosServiceImpl.findJuegosByJuegosIdModel(id);
		LOG.info(juego);
		model.addAttribute("juego", juego);
		return views.VER_JUEGO;
	}
	
	@RequestMapping("/addjuegos")
	public ModelAndView addjuegos(Model model) {
		LOG.info("FORMULARIO AÑADIR JUEGO ADD_JUEGOS" );
		ModelAndView mav = new ModelAndView(views.ADD_JUEGOS);
		mav.addObject("categoria",CategoriasServiceImpl.listAllCategorias());
		mav.addObject("plataforma",PlataformasServiceImpl.listAllPlataformas());
		model.addAttribute("juego", new JuegosModel());
		return mav;
	}
	
	@PostMapping("/addJuegosform")
	public String AddJuegos2(Model model,@ModelAttribute(name="juego") JuegosModel juego,@RequestParam("file") MultipartFile file) throws IOException {
		LOG.info("AÑADIENDO juego " + juego.getTitulo());
			JuegosServiceImpl.savePhoto(file);

			LOG.info("NOMBRE: " + file.getOriginalFilename());
			if(juego.getTitulo().isEmpty()) {
				model.addAttribute("juego", new JuegosModel());
				return "redirect:/juegos/addjuegos";
			}else {
				if(file.getOriginalFilename().isEmpty()) {
					if(juego.getCaratula().isEmpty()) {
						juego.setCaratula("nophoto.PNG");
					}
				}else {
					juego.setCaratula(file.getOriginalFilename());
				}
				LOG.info("NOMBRE: " + juego);
				JuegosServiceImpl.addJuegos(juego);
				return "redirect:/juegos/listado";
			}
	}
}

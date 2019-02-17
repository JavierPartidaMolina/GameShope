package com.proyectoSegunda.Javier.proyectoJavier.Controller;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyectoSegunda.Javier.proyectoJavier.Constants.views;
import com.proyectoSegunda.Javier.proyectoJavier.Model.JuegosModel;
import com.proyectoSegunda.Javier.proyectoJavier.Model.alquileresModel;
import com.proyectoSegunda.Javier.proyectoJavier.Service.Impl.JuegosServiceImpl;
import com.proyectoSegunda.Javier.proyectoJavier.Service.Impl.alquileresServiceImpl;

@Controller
@RequestMapping("/alquiler")
public class AlquileresController {

	private static final Log LOG = LogFactory.getLog(AlquileresController.class);

	
	@Autowired
	@Qualifier("JuegosServiceImpl")
	private JuegosServiceImpl JuegosServiceImpl;
	
	@Autowired
	@Qualifier("alquileresServiceImpl")
	private alquileresServiceImpl alquileresServiceImpl;
	
	@GetMapping("/listado")
	public ModelAndView show() {
		LOG.info("LISTANDO LOS ALQUILERES VIEW: LIST_ALQUILERES");
		ModelAndView mav = new ModelAndView(views.LIST_ALQUILERES);
		mav.addObject("alquileres", alquileresServiceImpl.ListAllAlquileres());
		return mav;
	}
	@GetMapping("/alquiler/{idjuego}")
	public String comprar(RedirectAttributes mensaje, @PathVariable(name="idjuego") Integer idjuego,@ModelAttribute(name="alquiler") alquileresModel alquiler) {
		LOG.info("Alquilar UN JUEGO " + idjuego);
		mensaje.addFlashAttribute("comprado", 1);
		JuegosModel juego = JuegosServiceImpl.findJuegosByJuegosIdModel(idjuego);
		alquileresServiceImpl.addAlquiler(alquiler, juego);
		return "redirect:/alquiler/alquileresUser";
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')") 
	@GetMapping("/devolver/{idalquiler}")
	public String devovler(RedirectAttributes mensaje, @PathVariable(name="idalquiler") Integer idalquiler) throws IOException {
		LOG.info("devolver UN JUEGO" + idalquiler);
		mensaje.addFlashAttribute("devuelto", 1);
		alquileresServiceImpl.devolver(idalquiler);
		return "redirect:/alquiler/listado";
	}
	@GetMapping("/alquileresUser")
	public ModelAndView comprasUser() {
		LOG.info("LISTANDO LOS ALQUILERES DEL USUARIO " + " LIST_ALQUILER_USER");
		ModelAndView mav = new ModelAndView(views.LIST_ALQUILER_USER);
		mav.addObject("alquileres", alquileresServiceImpl.ListAlquileresUser());
		return mav;
	}
}

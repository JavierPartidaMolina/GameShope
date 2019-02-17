package com.proyectoSegunda.Javier.proyectoJavier.Controller;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyectoSegunda.Javier.proyectoJavier.Constants.views;
import com.proyectoSegunda.Javier.proyectoJavier.Model.ParticipacionesModel;
import com.proyectoSegunda.Javier.proyectoJavier.Repository.ParticipacionesJpaRepository;
import com.proyectoSegunda.Javier.proyectoJavier.Repository.UsersJpaRepository;
import com.proyectoSegunda.Javier.proyectoJavier.Service.Impl.CompeticionesServiceImpl;
import com.proyectoSegunda.Javier.proyectoJavier.Service.Impl.ParticipacionesServiceImpl;

@Controller
@RequestMapping("/Participaciones")
public class ParticipacionesController {
	
	private static final Log LOG = LogFactory.getLog(ParticipacionesController.class);

	@Autowired
	@Qualifier("ParticipacionesServiceImpl")
	private ParticipacionesServiceImpl ParticipacionesService;
	
	@Autowired
	@Qualifier("ParticipacionesJpaRepository")
	private ParticipacionesJpaRepository ParticipacionesJpaRepository;
	
	@Autowired
	@Qualifier("CompeticionesServiceImpl")
	private CompeticionesServiceImpl CompeticionesServiceImpl;
	
	@Autowired
	@Qualifier("UsersJpaRepository")
	private UsersJpaRepository UsersJpaRepository;
	
	@GetMapping("/listado")
	public ModelAndView show() {
		LOG.info("MOSTRANDO TODAS LAS PARTICIPACIONES VIEW: LIST_PARTICIPACIONES");
		ModelAndView mav = new ModelAndView(views.LIST_PARTICIPACIONES);
		mav.addObject("Participaciones", ParticipacionesService.listAllParticipaciones());
		return mav;
	}
	
	@GetMapping("/listadouser")
	public ModelAndView showuser(Model model) {
		LOG.info("MOSTRANDO TODAS LAS PARTICIPACIONES DE UN USUARIO VIEW: LIST_PARTICIPACIONES");
		ModelAndView mav = new ModelAndView(views.LIST_PARTICIPACIONES);
		model.addAttribute("asignar", 0);
		mav.addObject("Participaciones", ParticipacionesService.listAllParticipacionesUser());
		return mav;
	}
	
	
	@GetMapping("/addParticipaciones")
	public String addParticipaciones(Model model) {
		LOG.info("MOSTRANDO FORMULARIO AÑADIR PARTICIPACION VIEW: ADD_PARTICIPACIONES");
		model.addAttribute("participacion", new ParticipacionesModel());
		return views.ADD_PARTICIPACIONES;
	}
	
	@GetMapping("/addParticipacionesForm/{idcompeticion}")
	public String addParticipacionesForm(RedirectAttributes mensaje,@PathVariable(name="idcompeticion") Integer idcompeticion, @ModelAttribute(name="Participacion") ParticipacionesModel Participaciones) {
		LOG.info("AÑADIENDO PARTICIPACION A COMPETICION " + idcompeticion);
		Participaciones.setCompeticion(CompeticionesServiceImpl.findCompeticionesByCompeticionesId(idcompeticion));
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Participaciones.setUser(UsersJpaRepository.findByUsername(user.getUsername()));
		mensaje.addFlashAttribute("participa", 1);
		ParticipacionesService.addParticipaciones(Participaciones);
		return "redirect:/Participaciones/listadouser";
	}
	
	@PostMapping("/addParticipacionesForm2/{id}")
	public String addParticipacionesForm2(RedirectAttributes mensaje,@PathVariable(name="id") Integer id, @ModelAttribute(name="part") ParticipacionesModel Participaciones) {
		LOG.info("AÑADIENDO posicion  a PARTICIPACION " + id);
		ParticipacionesModel partici = new ParticipacionesModel();
		partici = ParticipacionesService.findParticipacionesByParticipacionesIdModel(id);
		partici.setPosicion(Participaciones.getPosicion());
		ParticipacionesService.addParticipaciones(partici);
		mensaje.addFlashAttribute("participa", 2);
		String url = "redirect:/Competiciones/verParticipaciones/".concat(Integer.toString(partici.getCompeticion().getId()));
		return url;
	}
	
	@GetMapping("/editParticipaciones/{id}")
	public String editParticipaciones(Model model, @PathVariable(name="id") int id) {
		LOG.info("editar PARTICIPACION  " + id);
		ParticipacionesModel Participaciones = new ParticipacionesModel();
		Participaciones =ParticipacionesService.findParticipacionesByParticipacionesIdModel(id);
		model.addAttribute("Participaciones", Participaciones);
		return views.EDIT_PARTICIPACIONES;
	}
	
	@GetMapping("/removeParticipaciones/{id}")
	public ModelAndView delete_noticia(@PathVariable(name="id") int id) throws IOException {
		LOG.info("ELIMINAR PARTICIPACION  " + id);
		ParticipacionesService.removeParticipaciones(id);
		return show();
	}
}

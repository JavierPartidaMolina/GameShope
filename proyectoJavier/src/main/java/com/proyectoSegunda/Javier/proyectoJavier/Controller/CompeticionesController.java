package com.proyectoSegunda.Javier.proyectoJavier.Controller;

import java.io.IOException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
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

import com.proyectoSegunda.Javier.proyectoJavier.Constants.views;
import com.proyectoSegunda.Javier.proyectoJavier.Entity.Participaciones;
import com.proyectoSegunda.Javier.proyectoJavier.Model.CompeticionesModel;
import com.proyectoSegunda.Javier.proyectoJavier.Model.ParticipacionesModel;
import com.proyectoSegunda.Javier.proyectoJavier.Repository.ParticipacionesJpaRepository;
import com.proyectoSegunda.Javier.proyectoJavier.Repository.UsersJpaRepository;
import com.proyectoSegunda.Javier.proyectoJavier.Service.Impl.CompeticionesServiceImpl;

@Controller
@RequestMapping("/Competiciones")
public class CompeticionesController {

	private static final Log LOG = LogFactory.getLog(CompeticionesController.class);
	
	@Autowired
	@Qualifier("CompeticionesServiceImpl")
	private CompeticionesServiceImpl CompeticionesService;
	
	@Autowired
	@Qualifier("ParticipacionesJpaRepository")
	private ParticipacionesJpaRepository ParticipacionesJpaRepository;
	
	@Autowired
	@Qualifier("UsersJpaRepository")
	private UsersJpaRepository UsersJpaRepository;
	

	@GetMapping("/listado")
	public ModelAndView show() {
		LOG.info("Ver competiciones VIEW: LIST_COMPETICIONES");
		ModelAndView mav = new ModelAndView(views.LIST_COMPETICIONES);
		mav.addObject("Competiciones", CompeticionesService.listAllCompeticiones());
		return mav;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')") 
	@GetMapping("/listadoAdmin")
	public ModelAndView showAdmin() {
		LOG.info("Ver competiciones LIST_COMPETICIONES_ADMIN");
		ModelAndView mav = new ModelAndView(views.LIST_COMPETICIONES_ADMIN);
		mav.addObject("Competiciones", CompeticionesService.listAllCompeticiones());
		return mav;
	}

	@GetMapping("/verCompeticion/{id}")
	public String verCompeticion(Model model, @PathVariable(name="id") int id) {
		LOG.info("Ver competicion " + id + " VER_COMPETICION_VIEW");
		CompeticionesModel CompeticionesModel = new CompeticionesModel();
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Participaciones> participaciones = UsersJpaRepository.findByUsername(user.getUsername()).getParticipaciones();
		int participa = 0;
		for (int i = 0; i < participaciones.size(); i++) {
			if(participaciones.get(i).getUser().getUsername().equals(user.getUsername()) && participaciones.get(i).getCompeticion().getId() == id) {
				participa = 1;
				break;
			}
		}
		model.addAttribute("Participacion", new ParticipacionesModel());
		model.addAttribute("participa", participa);
		CompeticionesModel = CompeticionesService.findCompeticionesByCompeticionesIdModel(id);
		model.addAttribute("competicio", CompeticionesModel);
		return views.VER_COMPETICION_VIEW;
	}
	
	@GetMapping("/verParticipaciones/{id}")
	public String verParticipaciones(Model model, @PathVariable(name="id") int id) {
		LOG.info("Ver verParticipaciones " + id + " VER_verParticipaciones_VIEW");
		List<Participaciones> participaciones  = CompeticionesService.findCompeticionesByCompeticionesIdModel(id).getParticipaciones();
		model.addAttribute("Participaciones", participaciones);
		model.addAttribute("part", new ParticipacionesModel());
		model.addAttribute("asignar", 1);
		return views.LIST_PARTICIPACIONES;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')") 
	@GetMapping("/addCompeticiones")
	public String addCompeticiones(Model model) {
		LOG.info("FORMULARIO AÑADIR COMPETICION VIEW: ADD_COMPETICIONES");
		model.addAttribute("competicion", new CompeticionesModel());
		return views.ADD_COMPETICIONES;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')") 
	@PostMapping("/addCompeticionesForm")
	public String addCompeticionesForm(@ModelAttribute(name="Competiciones") CompeticionesModel Competiciones) {
		LOG.info(" AÑADIR COMPETICION " + Competiciones);
		CompeticionesService.addCompeticiones(Competiciones);
		return "redirect:/Competiciones/listadoAdmin";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')") 
	@GetMapping("/editCompeticiones/{id}")
	public String editCompeticiones(Model model, @PathVariable(name="id") int id) {
		
		CompeticionesModel Competiciones = new CompeticionesModel();
		Competiciones =CompeticionesService.findCompeticionesByCompeticionesIdModel(id);
		model.addAttribute("Competiciones", Competiciones);
		LOG.info(" EDITAR COMPETICION " + Competiciones);
		return views.EDIT_COMPETICIONES;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')") 
	@GetMapping("/removeCompeticiones/{id}")
	public ModelAndView delete_noticia(@PathVariable(name="id") int id) throws IOException {
		LOG.info("ELIMINAR COMPETICION " + id);
		CompeticionesService.removeCompeticiones(id);
		return showAdmin();
	}
}

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
import org.springframework.web.servlet.ModelAndView;

import com.proyectoSegunda.Javier.proyectoJavier.Constants.views;
import com.proyectoSegunda.Javier.proyectoJavier.Model.PlataformasModel;
import com.proyectoSegunda.Javier.proyectoJavier.Service.Impl.PlataformasServiceImpl;

@Controller
@RequestMapping("/plataformas")
public class PlataformasController {

	private static final Log LOG = LogFactory.getLog(PlataformasController.class);
	
	@Autowired
	@Qualifier("PlataformasServiceImpl")
	private PlataformasServiceImpl PlataformasService;
	
	@GetMapping("/listado")
	public ModelAndView show() {
		LOG.info("LISTANDO PLATAFORMAS  VIEW: LIST_PLATAFORMAS");
		ModelAndView mav = new ModelAndView(views.LIST_PLATAFORMAS);
		mav.addObject("plataformas", PlataformasService.listAllPlataformas());
		return mav;
	}
	
	@GetMapping("/addPlataformas")
	public String addPlataformas(Model model) {
		LOG.info("FORMULARIO PLATAFORMAS  VIEW: ADD_PLATAFORMAS");
		model.addAttribute("plataforma", new PlataformasModel());
		return views.ADD_PLATAFORMAS;
	}
	
	@PostMapping("/addPlataformasForm")
	public String addPlataformasForm(@ModelAttribute(name="plataforma") PlataformasModel plataforma) {
		LOG.info("AÑADIENDO PLATAFORMA " + plataforma);
		PlataformasService.addPlataformas(plataforma);
		return "redirect:/plataformas/listado";
	}
	
	@GetMapping("/editPlataformas/{id}")
	public String editPlataformas(Model model, @PathVariable(name="id") int id) {
		LOG.info("FORMULARIO EDITAR PLATAFORMAS  VIEW: EDIT_PLATAFORMAS");
		PlataformasModel plataforma = new PlataformasModel();
		plataforma =PlataformasService.findPlataformasByPlataformasIdModel(id);
		model.addAttribute("plataforma", plataforma);
		return views.EDIT_PLATAFORMAS;
	}
	
	@GetMapping("/removePlataformas/{id}")
	public ModelAndView delete_noticia(@PathVariable(name="id") int id) throws IOException {
		LOG.info("ELIMINANDO PLATAFORMA " + id);
		PlataformasService.removePlataformas(id);
		return show();
	}
}

package com.proyectoSegunda.Javier.proyectoJavier.Controller;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.proyectoSegunda.Javier.proyectoJavier.Constants.views;
import com.proyectoSegunda.Javier.proyectoJavier.Model.CategoriasModel;
import com.proyectoSegunda.Javier.proyectoJavier.Service.Impl.CategoriasServiceImpl;

@Controller
@RequestMapping("/categorias")
public class CategoriasController {

	private static final Log LOG = LogFactory.getLog(CategoriasController.class);
	
	@Autowired
	@Qualifier("CategoriasServiceImpl")
	private CategoriasServiceImpl CategoriasService;
	
	@PreAuthorize("hasRole('ROLE_ADMIN')") 
	@GetMapping("/listado")
	public ModelAndView show() {
		LOG.info("LISTA DE CATEGORIAS VIEW: LIST_CATEGORIAS");
		ModelAndView mav = new ModelAndView(views.LIST_CATEGORIAS);
		mav.addObject("categorias", CategoriasService.listAllCategorias());
		return mav;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')") 
	@GetMapping("/addCategorias")
	public String addCategorias(Model model) {
		LOG.info("FORMULARIO AÑADIR CATEGORIA VIEW: ADD_CATEGORIAS");
		model.addAttribute("categoria", new CategoriasModel());
		return views.ADD_CATEGORIAS;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')") 
	@PostMapping("/addCategoriasForm")
	public String addCategoriasForm(@ModelAttribute(name="categorias") CategoriasModel categorias) {
		LOG.info("AÑADIR CATEGORIA " + categorias);
		CategoriasService.addCategorias(categorias);
		return "redirect:/categorias/listado";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')") 
	@GetMapping("/editCategorias/{id}")
	public String editCategorias(Model model, @PathVariable(name="id") int id) {
		CategoriasModel categorias = new CategoriasModel();
		categorias =CategoriasService.findCategoriasByCategoriasIdModel(id);
		model.addAttribute("categorias", categorias);
		LOG.info("EDITAR CATEGORIA " + categorias);
		return views.EDIT_CATEGORIAS;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')") 
	@GetMapping("/removeCategorias/{id}")
	public ModelAndView delete_noticia(@PathVariable(name="id") int id) throws IOException {
		LOG.info("ELIMINAR CATEGORIA " + id);
		CategoriasService.removeCategorias(id);
		return show();
	}
}

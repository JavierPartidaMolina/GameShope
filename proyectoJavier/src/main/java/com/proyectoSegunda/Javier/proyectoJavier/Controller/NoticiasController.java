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
import com.proyectoSegunda.Javier.proyectoJavier.Model.NoticiasModel;
import com.proyectoSegunda.Javier.proyectoJavier.Service.NoticiasService;


@Controller
@RequestMapping("/noticias")
public class NoticiasController {

	@Autowired
	@Qualifier("NoticiasServiceImpl")
	private NoticiasService NoticiasService;
	
	private static final Log LOG = LogFactory.getLog(NoticiasController.class);
	
	@GetMapping("/opciones")
	public ModelAndView show() {
		LOG.info("Opciones Noticias " + " NOTICIAS_OPTIONS");
		ModelAndView mav = new ModelAndView(views.NOTICIAS_OPTIONS);
		mav.addObject("noticias", NoticiasService.listAllNoticias());
		return mav;
	}
	
	@GetMapping("/addNoticias")
	public String addNoticias(Model model) {
		LOG.info("añadir noticias " + " ADD_NOTICIAS_VIEW");
		model.addAttribute("noticia", new NoticiasModel());
		return views.ADD_NOTICIAS_VIEW;
	}
	
	@PostMapping("/addNoticiasForm")
	public String addNoticiasForm(Model model, @ModelAttribute(name="noticia") NoticiasModel noticiasModel, @RequestParam("file") MultipartFile file) throws IOException {
			LOG.info("AÑADIENDO NOTICIA " + noticiasModel);
			NoticiasService.savePhoto(file);
			LOG.info("NOMBRE: " + file.getOriginalFilename());
			if(noticiasModel.getTitulo().isEmpty()) {
				model.addAttribute("noticia", new NoticiasModel());
				return "redirect:/noticias/addNoticias";
			}else {
				if(file.getOriginalFilename().isEmpty()) {
					if(noticiasModel.getFoto().isEmpty()) {
						noticiasModel.setFoto("nophoto.PNG");
					}
				}else {
					noticiasModel.setFoto(file.getOriginalFilename());
				}

				NoticiasService.addNoticias(noticiasModel);
				return "redirect:/noticias/opciones";
			}
	}
	
	@GetMapping("/editNoticias/{id}")
	public String editNoticias(Model model, @PathVariable(name="id") int id) {
		LOG.info("editar noticia " + id + " CAR_EDIT");
		NoticiasModel NotModel = new NoticiasModel();
		NotModel = NoticiasService.findNoticiasByNoticiaIdModel(id);
		model.addAttribute("noticia", NotModel);
		return views.EDIT_NOTICIAS;
	}

	
	@GetMapping("/removeNoticias/{id}")
	public ModelAndView delete_noticia(@PathVariable(name="id") int id) throws IOException {
		LOG.info("Eliminando noticia " + id);
		NoticiasService.removeNoticias(id);
		return show();
	}
}

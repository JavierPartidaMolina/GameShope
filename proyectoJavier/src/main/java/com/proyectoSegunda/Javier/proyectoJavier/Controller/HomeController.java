package com.proyectoSegunda.Javier.proyectoJavier.Controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyectoSegunda.Javier.proyectoJavier.Constants.views;
import com.proyectoSegunda.Javier.proyectoJavier.Model.NoticiasModel;
import com.proyectoSegunda.Javier.proyectoJavier.Model.UserModel;
import com.proyectoSegunda.Javier.proyectoJavier.Service.NoticiasService;
import com.proyectoSegunda.Javier.proyectoJavier.Service.UsersServicePlus;
import com.proyectoSegunda.Javier.proyectoJavier.Service.Impl.UsersService;
import com.proyectoSegunda.Javier.proyectoJavier.Service.Impl.UsersServicePlusImpl;
import com.proyectoSegunda.Javier.proyectoJavier.Service.Impl.alquileresServiceImpl;

@Controller
@RequestMapping("/proyecto")
public class HomeController {
		
	@Autowired
	@Qualifier("NoticiasServiceImpl")
	private NoticiasService NoticiasService;
	
	@Autowired
	@Qualifier("UsersService")
	private UsersService UsersService;
	
	@Autowired
	@Qualifier("UsersServicePlusImpl")
	private UsersServicePlusImpl UsersServicePlusImpl;
	
	@Autowired
	@Qualifier("alquileresServiceImpl")
	private alquileresServiceImpl alquileresServiceImpl;
	
	private static final Log LOG = LogFactory.getLog(HomeController.class);
	
	@GetMapping("/home")
	public ModelAndView home() {
		LOG.info("Listando NOTICIAS " + NoticiasService.listAllNoticias()  + " NOTICIAS_VIEW");	
		ModelAndView mav = new ModelAndView(views.HOME);
		mav.addObject("noticias", NoticiasService.listAllNoticias());
		return mav;
	}
	
	@GetMapping("/verNoticia/{noticia_id}")
	public String Ver_Noticia(Model model, @PathVariable(name="noticia_id") Integer noticia_id) {
		LOG.info("Ver noticia " + noticia_id + " VER_NOTICIA_VIEW");
		NoticiasModel noticiasModel = new NoticiasModel();
		noticiasModel = NoticiasService.findNoticiasByNoticiaIdModel(noticia_id);
		model.addAttribute("noticiasModel", noticiasModel);
		return views.VER_NOTICIA;
	}
	@GetMapping("/perfil")
	public String perfil(Model model, RedirectAttributes mensaje) {
		LOG.info("Mostrando perfil  PERFIL_VIEW");	
		UserModel userModel = new UserModel();
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(alquileresServiceImpl.ControlarAlquileresUser()) {
				LOG.info("holaaaaaaaaaaaaaaaaaaaaaaaaa");
				mensaje.addFlashAttribute("alquiler", 1);
		}else {
				mensaje.addFlashAttribute("alquiler", 0);
		}
		userModel = UsersServicePlusImpl.findUsersByUsernameModel(user.getUsername());
		model.addAttribute("user", userModel);
		return "redirect:/proyecto/perfil2";
	}
	@GetMapping("/perfil2")
	public String perfil2(Model model) {
		LOG.info("Mostrando perfil2  PERFIL_VIEW");	
		UserModel userModel = new UserModel();
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		userModel = UsersServicePlusImpl.findUsersByUsernameModel(user.getUsername());
		model.addAttribute("user", userModel);
		return views.PERFIL;
	}	
}

package com.proyectoSegunda.Javier.proyectoJavier.Controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyectoSegunda.Javier.proyectoJavier.Constants.views;
import com.proyectoSegunda.Javier.proyectoJavier.Entity.User;
import com.proyectoSegunda.Javier.proyectoJavier.Service.Impl.UsersService;
import com.proyectoSegunda.Javier.proyectoJavier.Service.Impl.UsersServicePlusImpl;

@Controller
@RequestMapping("/users")
public class UserController {

	private static final Log LOG = LogFactory.getLog(UserController.class);
	
	@Autowired
	@Qualifier("UsersServicePlusImpl")
	private UsersServicePlusImpl UsersServicePlusImpl;
	
	@Autowired
	@Qualifier("UsersService")
	private UsersService UsersService;
	
	@PostMapping("/editperfil")
	public String perfil(@ModelAttribute(name="usuario") com.proyectoSegunda.Javier.proyectoJavier.Entity.User use,
			RedirectAttributes mensaje) {
			LOG.info("Editando perfil  PERFIL_VIEW" + use.getUsername());	
			use.setEnabled(true);
			UsersService.saveNoEncode(use);
			mensaje.addFlashAttribute("editar",1);
			return "redirect:/proyecto/perfil";
	}
	
	@GetMapping("/listusers")
	public ModelAndView show_users() {
		LOG.info("Listando usuarios "  + " Users_VIEW");
		ModelAndView mav = new ModelAndView(views.USERS_VIEW);
		mav.addObject("users", UsersServicePlusImpl.ListAllUsers());
		return mav;
	}
	
	@GetMapping("/habilitar/{usuario}")
	public String habilitar(@PathVariable(name="usuario") String usuario,
			RedirectAttributes mensaje) {
			User use = UsersServicePlusImpl.findUsersByUsername(usuario);
			use.setEnabled(true);
			UsersService.saveNoEncode(use);
			return "redirect:/users/listusers";
		}
	
	@GetMapping("/deshabilitar/{usuario}")
	public String deshabilitar(@PathVariable(name="usuario") String usuario,RedirectAttributes mensaje) {
			User use = UsersServicePlusImpl.findUsersByUsername(usuario);
			use.setEnabled(false);
			LOG.info("habilitado?  " + use.isEnabled());
			UsersService.saveNoEncode(use);
			return "redirect:/users/listusers";
	}
	
	@GetMapping("/eliminar/{usuario}")
	public String eliminar(@PathVariable(name="usuario") String usuario,
			RedirectAttributes mensaje) {
			LOG.info("ELIMINAR USUARIO " + usuario);
			User use = UsersServicePlusImpl.findUsersByUsername(usuario);
			UsersService.remove(use);
			return "redirect:/users/listusers";
	}
}

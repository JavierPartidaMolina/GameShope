package com.proyectoSegunda.Javier.proyectoJavier.Controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyectoSegunda.Javier.proyectoJavier.Constants.views;
import com.proyectoSegunda.Javier.proyectoJavier.Entity.User;
import com.proyectoSegunda.Javier.proyectoJavier.Repository.UsersJpaRepository;
import com.proyectoSegunda.Javier.proyectoJavier.Service.Impl.UsersService;
import com.proyectoSegunda.Javier.proyectoJavier.Service.Impl.alquileresServiceImpl;

@Controller
@RequestMapping("/log")
public class LoginController {
	
	private static final Log LOG = LogFactory.getLog(LoginController.class);
	
	@Autowired
	@Qualifier("UsersJpaRepository")
	private UsersJpaRepository UsersJpaRepository;
	
	@Autowired
	@Qualifier("UsersService")
	private UsersService UsersService;
	
	@Autowired
	@Qualifier("alquileresServiceImpl")
	private alquileresServiceImpl alquileresServiceImpl;
	
	@GetMapping("/login")
	public String showLoginForm(Model model,@RequestParam(name="error", required=false) String error,
			@RequestParam(name="logout", required=false) String logout) {
		LOG.info("ABRIENDO LOGIN VIEW: LOGIN");
		model.addAttribute("error", error);
		model.addAttribute("logout", logout);
		return views.LOGIN;
	}
	
	@GetMapping("/loginsuccess")
	public String loginCheck() {
		LOG.info("Logeado correctamente");
		
		return views.HOME;
	}
	
	@GetMapping("/registro")
	public String registro(Model model) {
		LOG.info("Entrando al formulario de registro");
		model.addAttribute("usuario", new User());
		return views.REGISTRO;
	}
	
	
	@PostMapping("/registrar")
	public String registrar(@ModelAttribute(name="usuario") User user,Model model,RedirectAttributes mensaje) {
		LOG.info("Usuario registrado correctamente volviendo a Login");
		
		if(UsersJpaRepository.findByUsername(user.getUsername())!=null) {
			mensaje.addFlashAttribute("registrar",1);
			return "redirect:/log/registro";
		}else if(user.getUsername().isEmpty() || user.getPassword().isEmpty() || user.getNombre().isEmpty()
				|| user.getApellidos().isEmpty() || user.getEmail().isEmpty()){
			mensaje.addFlashAttribute("registrar",3);
			return "redirect:/log/registro";
		}else {
			mensaje.addFlashAttribute("registrar",2);
			UsersService.save(user);
			return "redirect:/log/login";
		}
		
	}

}

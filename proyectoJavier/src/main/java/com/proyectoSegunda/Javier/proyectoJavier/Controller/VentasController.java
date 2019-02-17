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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyectoSegunda.Javier.proyectoJavier.Constants.views;
import com.proyectoSegunda.Javier.proyectoJavier.Model.JuegosModel;
import com.proyectoSegunda.Javier.proyectoJavier.Model.ventasModel;
import com.proyectoSegunda.Javier.proyectoJavier.Service.Impl.JuegosServiceImpl;
import com.proyectoSegunda.Javier.proyectoJavier.Service.Impl.ventasServiceImpl;

@Controller
@RequestMapping("/ventas")
public class VentasController {

	private static final Log LOG = LogFactory.getLog(VentasController.class);

	@Autowired
	@Qualifier("ventasServiceImpl")
	private ventasServiceImpl ventasServiceImpl;
	
	@Autowired
	@Qualifier("JuegosServiceImpl")
	private JuegosServiceImpl JuegosServiceImpl;
	
	@GetMapping("/listado")
	public ModelAndView show() {
		LOG.info("LISTANDO LAS VENTAS");
		ModelAndView mav = new ModelAndView(views.LIST_VENTAS);
		mav.addObject("ventas", ventasServiceImpl.ListAllVentas());
		return mav;
	}
	
	@GetMapping("/comprar/{idjuego}")
	public String comprar(RedirectAttributes mensaje, @PathVariable(name="idjuego") Integer idjuego,@ModelAttribute(name="venta") ventasModel venta) {
		LOG.info("COMPRA UN JUEGO");
		mensaje.addFlashAttribute("comprado", 1);
		JuegosModel juego = JuegosServiceImpl.findJuegosByJuegosIdModel(idjuego);
		ventasServiceImpl.addVentas(venta, juego);
		return "redirect:/ventas/comprasUser";
	}
	
	@GetMapping("/devolver/{idventa}")
	public String devovler(RedirectAttributes mensaje, @PathVariable(name="idventa") Integer idventa) throws IOException {
		LOG.info("devolver UN JUEGO");
		mensaje.addFlashAttribute("devuelto", 1);
		ventasServiceImpl.devolver(idventa);
		return "redirect:/ventas/listado";
	}
	
	@GetMapping("/comprasUser")
	public ModelAndView comprasUser() {
		LOG.info("LISTANDO LAS COMPRAS DEL USUARIO");
		ModelAndView mav = new ModelAndView(views.LIST_COMPRAS_USER);
		mav.addObject("ventas", ventasServiceImpl.ListComprasUser());
		return mav;
	}
}

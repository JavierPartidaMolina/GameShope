package com.proyectoSegunda.Javier.proyectoJavier.Controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyectoSegunda.Javier.proyectoJavier.Entity.Noticias;
import com.proyectoSegunda.Javier.proyectoJavier.Model.NoticiasModel;
import com.proyectoSegunda.Javier.proyectoJavier.Service.NoticiasService;

@RestController
@RequestMapping("/RestNocitias")
public class ApiRestNoticiasController {
	
	@Autowired
	@Qualifier("NoticiasServiceImpl")
	private NoticiasService NoticiasService;
	
	
	@GetMapping("/noticias")
	public List<NoticiasModel> getNoticias(){
		return NoticiasService.listAllNoticias();
	}
	
	@GetMapping("/noticias/{id}")
	public Noticias getNoticias2(@PathVariable int id){
		return NoticiasService.findNoticiasByNoticiaId(id);
	}
	
	@PostMapping("/add")
	public Noticias addNoticiasForm(@RequestBody NoticiasModel noticia) throws IOException {
			return NoticiasService.addNoticias(noticia);
	}
	
	@PutMapping("/update/{id}")
	public Noticias updateNoticiasForm(@PathVariable int id,@RequestBody @Valid NoticiasModel noticia) throws IOException {
			NoticiasModel not = NoticiasService.findNoticiasByNoticiaIdModel(id);
			not.setTitulo(noticia.getTitulo());
			not.setCuerpo(noticia.getCuerpo());
			not.setFoto(noticia.getFoto());
			not.setResumen(noticia.getResumen());
			return NoticiasService.addNoticias(not);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteNoticia(@PathVariable int id) throws IOException {
		NoticiasService.removeNoticias(id);
	}
}

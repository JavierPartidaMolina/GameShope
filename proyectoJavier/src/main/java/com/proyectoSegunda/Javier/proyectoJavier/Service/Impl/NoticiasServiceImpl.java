package com.proyectoSegunda.Javier.proyectoJavier.Service.Impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.proyectoSegunda.Javier.proyectoJavier.Converter.NoticiasConverter;
import com.proyectoSegunda.Javier.proyectoJavier.Entity.Noticias;
import com.proyectoSegunda.Javier.proyectoJavier.Model.NoticiasModel;
import com.proyectoSegunda.Javier.proyectoJavier.Repository.NoticiasJpaRepository;
import com.proyectoSegunda.Javier.proyectoJavier.Service.NoticiasService;

@Service("NoticiasServiceImpl")
public class NoticiasServiceImpl implements NoticiasService{
	
	private static final Log LOG = LogFactory.getLog(NoticiasServiceImpl.class);
		
	@Autowired
	@Qualifier("NoticiasConverter")
	private NoticiasConverter NoticiasConverter;
	
	@Autowired
	@Qualifier("NoticiasJpaRepository")
	private NoticiasJpaRepository NoticiasJpaRepository;

	@Override
	public List<NoticiasModel> listAllNoticias() {
		List<NoticiasModel> noticiaslist = new ArrayList<>();
		NoticiasJpaRepository.findAll().forEach(Noticia ->{noticiaslist.add(NoticiasConverter.entity2model(Noticia));});
		return noticiaslist;
	}

	@Override
	public Noticias addNoticias(NoticiasModel noticiasModel) {
		Noticias noticia = new Noticias();
		noticia = NoticiasConverter.model2entity(noticiasModel);
		return NoticiasJpaRepository.save(noticia);
	}

	@Override
	public void removeNoticias(int id) throws IOException {
		LOG.info("Eliminar recibe id: " + id);
		Noticias noticia = NoticiasJpaRepository.findById(id);
		NoticiasJpaRepository.delete(noticia);
		
	}

	@Override
	public Noticias findNoticiasByTitulo(String titulo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NoticiasModel findNoticiasByTituloModel(String titulo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void savePhoto(MultipartFile file) throws IOException {
		if(!file.isEmpty()) {
			byte[] bytes = file.getBytes();
			String ruta = ".//src//main//resources//static//images//";
			Path path = Paths.get(ruta + file.getOriginalFilename());
			Files.write(path, bytes);
			LOG.info(ruta + file.getOriginalFilename());
		}	
		
	}

	@Override
	public NoticiasModel findNoticiasByNoticiaIdModel(int noticia_id) {
		return NoticiasConverter.entity2model(findNoticiasByNoticiaId(noticia_id));
	}

	@Override
	public Noticias findNoticiasByNoticiaId(int noticia_id) {
		Noticias noticia = NoticiasJpaRepository.findById(noticia_id);
		return noticia;
	}
}


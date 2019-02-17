package com.proyectoSegunda.Javier.proyectoJavier.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.proyectoSegunda.Javier.proyectoJavier.Entity.Noticias;
import com.proyectoSegunda.Javier.proyectoJavier.Model.NoticiasModel;


public interface NoticiasService {
	
	public abstract List<NoticiasModel> listAllNoticias();
	public abstract Noticias addNoticias(NoticiasModel noticias);
	public abstract void removeNoticias(int id) throws IOException;
	public abstract NoticiasModel findNoticiasByNoticiaIdModel(int noticia_id);
	public abstract Noticias findNoticiasByNoticiaId(int noticia_id);
	public abstract Noticias findNoticiasByTitulo(String titulo);
	public abstract NoticiasModel findNoticiasByTituloModel(String titulo);
	public abstract void savePhoto(MultipartFile file) throws IOException;
}

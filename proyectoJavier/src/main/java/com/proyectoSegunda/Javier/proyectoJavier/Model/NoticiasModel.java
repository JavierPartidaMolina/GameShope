package com.proyectoSegunda.Javier.proyectoJavier.Model;


public class NoticiasModel {
	
		public int id;
		private String titulo;
		private String resumen;
		private String cuerpo;
		private String foto;
		
		
		
		public int getId() {
			return id;
		}
		public void setId(int noticias_id) {
			this.id = noticias_id;
		}
		public String getTitulo() {
			return titulo;
		}
		public void setTitulo(String titulo) {
			this.titulo = titulo;
		}
		public String getResumen() {
			return resumen;
		}
		public void setResumen(String resumen) {
			this.resumen = resumen;
		}
		public String getCuerpo() {
			return cuerpo;
		}
		public void setCuerpo(String cuerpo) {
			this.cuerpo = cuerpo;
		}
		public String getFoto() {
			return foto;
		}
		public void setFoto(String foto) {
			this.foto = foto;
		}
		@Override
		public String toString() {
			return "NoticiasModel [id=" + id + ", titulo=" + titulo + ", resumen=" + resumen + ", cuerpo=" + cuerpo
					+ ", foto=" + foto + "]";
		}
		public NoticiasModel(int id, String titulo, String resumen, String cuerpo, String foto) {
			super();
			this.id = id;
			this.titulo = titulo;
			this.resumen = resumen;
			this.cuerpo = cuerpo;
			this.foto = foto;
		}
		public NoticiasModel() {
			super();
		}
		
		

		
		

}

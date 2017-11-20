package es.ayesa.ejemploVaadin;

import java.io.Serializable;

public class Libro implements Serializable, Cloneable{
	private Long id;
	private String titulo;
	private String autor;
	private Cliente cliente;
	
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getAutor() {
		return autor;
	}
	
	public void setAutor(String autor) {
		this.autor = autor;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
	public Libro clone() throws CloneNotSupportedException{
		return (Libro) super.clone();
	}
	
	@Override
	public int hashCode() {
		int hash = 5;
		
		hash = 43 * hash + (id == null ? 0 : id.hashCode());
		
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (this.id == null) {
			return false;
		}
		if(obj instanceof Libro && obj.getClass().equals(getClass())) {
			return this.id.equals(((Libro) obj).id);
		}
		
		return false;
	}

	@Override
	public String toString() {
		return titulo + "; " + autor;
	}
}

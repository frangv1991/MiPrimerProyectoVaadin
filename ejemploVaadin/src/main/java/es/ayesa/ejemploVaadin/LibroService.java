package es.ayesa.ejemploVaadin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LibroService {
	private static LibroService instancia;
	private final Map<Long, Libro> libreria = new HashMap<>();
	private long siguienteId = 0;
	
	private LibroService() {}
	
	public static LibroService getInstancia() {
		if(instancia == null) {
			instancia = new LibroService();
			instancia.generarDatos();
		}
		
		return instancia;
	}

	private void generarDatos() {
		if(findAll().isEmpty()) {
			final String[] libros = new String[] {"El Señor de los Anillos;Tolkien", "Harry Potter;Rowling", "El nombre del viento;Rothfuss"};
			
			for(String titulo: libros) {
				String[] separados = titulo.split(";");
				
				Libro libro = new Libro();
				libro.setTitulo(separados[0]);
				libro.setAutor(separados[1]);
				
				guardar(libro);
			}
		}
	}
	
	public List<Libro> findAll() {
		return findAll(null);
	}

	public List<Libro> findAll(String cadena) {
		return libreria.values().stream().filter(libro -> {
			return (cadena == null || cadena.isEmpty()) || (libro.getTitulo().toLowerCase().contains(cadena.toLowerCase()) || libro.getAutor().toLowerCase().contains(cadena.toLowerCase()));
		}).collect(Collectors.toList());
	}

	public void guardar(Libro libro) {
		if(libro == null) {
			return;
		}
		else {
			if(libro.getId() == null) {
				libro.setId(siguienteId++);
			}
		}
		
		try {
			libro = libro.clone();
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
		
		libreria.put(libro.getId(), libro);
	}
	
	public long count() {
		return libreria.size();
	}
	
	public void borrar (Libro libro) {
		libreria.remove(libro.getId());
	}
	
}

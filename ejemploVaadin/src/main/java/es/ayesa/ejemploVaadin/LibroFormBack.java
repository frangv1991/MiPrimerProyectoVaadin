package es.ayesa.ejemploVaadin;

import com.vaadin.data.Binder;

@SuppressWarnings("serial")
public class LibroFormBack extends LibroForm {
	private LibroService libroService = LibroService.getInstancia();
	private ClienteService clienteService = ClienteService.getInstancia();
	private Binder<Libro> binder = new Binder<>(Libro.class);
	private Libro libro;
	private LibroView myUi;
	
	public LibroFormBack(LibroView myUi) {
		
		this.myUi = myUi;
		
		cliente.setItems(clienteService.getContactos().values());
		
		binder.bindInstanceFields(this);
		
		prestar.addClickListener(e -> this.prestar());
	}
	
	public void setLibro(Libro libro) {
		this.libro = libro;
		binder.setBean(libro);
		setVisible(true);
	}

	private void prestar() {
		libroService.guardar(libro);
		myUi.actualizarTabla();
		setVisible(false);
	}
}

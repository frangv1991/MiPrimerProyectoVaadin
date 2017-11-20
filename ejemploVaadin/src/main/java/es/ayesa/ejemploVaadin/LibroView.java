package es.ayesa.ejemploVaadin;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings({ "deprecation", "serial" })
public class LibroView extends VerticalLayout implements View{
	private LibroService libroService = LibroService.getInstancia();
	final TextField filterText = new TextField();
	private Grid<Libro> grid = new Grid<>(Libro.class);
	private LibroFormBack form = new LibroFormBack(this);
	private Navigator navigator;
	
	public static final String NAME = "";
	
	public LibroView(Navigator navigator) {
		this.navigator = navigator;
		
		form.setVisible(false);
        form.setSizeFull();
        
        grid.setSelectionMode(SelectionMode.SINGLE);
        grid.setColumns("titulo", "autor", "cliente");
        grid.setSizeFull();
        
        actualizarTabla();
        
        Button borrarFiltro = new Button(FontAwesome.TIMES);
        borrarFiltro.setDescription("Borrar filtro");
        borrarFiltro.addClickListener(e -> filterText.clear());
        
        filterText.setPlaceholder("Filtrar por título:");
        filterText.addValueChangeListener(e -> actualizarTabla());
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        
        CssLayout filtrado = new CssLayout();
        filtrado.addComponents(filterText, borrarFiltro);
        filtrado.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        
        
        HorizontalLayout main = new HorizontalLayout();
        main.addComponents(grid, form);
        main.setSizeFull();
        main.setExpandRatio(grid, 2);
        main.setExpandRatio(form, 1);
        
        grid.asSingleSelect().addValueChangeListener(evento -> {
        	if(evento.getValue() == null) {
        		form.setVisible(false);
        	}
        	else {
        		form.setLibro(evento.getValue());
        	}
        });
        
        
        HorizontalLayout botonera = new HorizontalLayout();
        
        Button navegar = new Button("Clientes");
        navegar.addClickListener(event -> this.navigator.navigateTo(ClienteView.NAME));
        
        botonera.addComponents(filtrado, navegar);
        
        this.addComponents(botonera, main);
	}
	
	public void actualizarTabla() {
    	grid.setItems(libroService.findAll(filterText.getValue()));
    }
}

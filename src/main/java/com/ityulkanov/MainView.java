package com.ityulkanov;

import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.router.Route;

import java.util.List;

/**
 * The main view contains a button and a click listener.
 */
@Route
@PWA(name = "My Application", shortName = "My Application")
public class MainView extends VerticalLayout {
    private CustomerService service = CustomerService.getInstance();
    private Grid<Customer> grid = new Grid<>( Customer.class );
    private TextField filterText = new TextField();
    private CustomerForm form = new CustomerForm( this );

    public MainView() {
        filterText.setPlaceholder( "filter by name " );
        filterText.setClearButtonVisible( true );
        filterText.setValueChangeMode( ValueChangeMode.EAGER );
        filterText.addValueChangeListener( e -> updateList() );

        updateList( );
        grid.setColumns( "firstName", "lastName", "email");
        HorizontalLayout mainContent = new HorizontalLayout( grid, form );
        mainContent.setSizeFull();
        grid.setSizeFull( );
        add( filterText, mainContent );
    }

    public void updateList() {
        List<Customer> customers = service.findAll(filterText.getValue());
        grid.setItems( customers );
    }
}

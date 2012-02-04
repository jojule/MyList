package org.vaadin.mylist;

import com.vaadin.Application;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Window;

/**
 * The Application's "main" class
 */
@SuppressWarnings("serial")
public class WidgetTestApplication extends Application
{
    private Window window;
    private MyList carList = new MyList();

    @Override
    public void init()
    {
        window = new Window("MyList Test");
        setMainWindow(window);
        
        window.addComponent(carList);
        carList.addItem("Audi");
        carList.addItem("BMW");
        carList.addItem("Chrysler");
        carList.addItem("Datsun");
        carList.addItem("Ford");
        carList.addItem("Honda");
        carList.addItem("Kia");
        carList.addListener(new ValueChangeListener() {			
			public void valueChange(ValueChangeEvent event) {
				window.showNotification("You have chosen a " + carList.getValue());
			}
		});
    }
    
}

package org.vaadin.mylist;

import org.vaadin.mylist.gwt.client.VMyList;

import com.vaadin.terminal.PaintException;
import com.vaadin.terminal.PaintTarget;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.ClientWidget;

/**
 * Server side component for the VMyComponent widget.
 */
@ClientWidget(VMyList.class)
public class MyList extends AbstractSelect {
}

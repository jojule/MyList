package org.vaadin.mylist.gwt.client;

import java.util.Iterator;
import java.util.LinkedList;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.ListBox;
import com.vaadin.terminal.gwt.client.ApplicationConnection;
import com.vaadin.terminal.gwt.client.Paintable;
import com.vaadin.terminal.gwt.client.UIDL;

public class VMyList extends ListBox implements Paintable, ChangeHandler {

	/** Set the CSS class name to allow styling. */
	public static final String CLASSNAME = "v-mylist";

	/** The client side widget identifier */
	protected String paintableId;

	/** Reference to the server connection object. */
	ApplicationConnection client;

	/** Selection option keys */
	private LinkedList<String> keys = new LinkedList<String>();

	/**
	 * The constructor should first call super() to initialize the component and
	 * then handle any initialization relevant to Vaadin.
	 */
	public VMyList() {
		// This method call of the Paintable interface sets the component
		// style name in DOM tree
		setStyleName(CLASSNAME);

		addChangeHandler(this);
	}

	/**
	 * Called whenever an update is received from the server
	 */
	public void updateFromUIDL(UIDL uidl, ApplicationConnection client) {
		// This call should be made first.
		// It handles sizes, captions, tooltips, etc. automatically.
		if (client.updateComponent(this, uidl, true)) {
			// If client.updateComponent returns true there has been no changes
			// and we
			// do not need to update anything.
			return;
		}

		// Save reference to server connection object to be able to send
		// user interaction later
		this.client = client;

		// Save the client side identifier (paintable id) for the widget
		paintableId = uidl.getId();

		// Update the state of the widget from UIDL
		UIDL options = uidl.getChildByTagName("options");
		if (options != null) {

			// Reset the list of options in the widget
			clear();
			keys.clear();
			for (Iterator<Object> i = options.getChildIterator(); i.hasNext();) {
				UIDL option = (UIDL) i.next();
				if (options != null) {
					keys.add(option.getStringAttribute("key"));
					addItem(option.getStringAttribute("caption"));
					if (options.getBooleanAttribute("selected")) {
						setSelectedIndex(keys.size() - 1);
					}
				}
			}

		}
	}

	/** When something is selected from the list */
	public void onChange(ChangeEvent event) {
		client.updateVariable(paintableId, "selected",
				new String[] { keys.get(getSelectedIndex()) }, true);
	}

}

package figglewatts.SEGment;

import java.util.ArrayList;
import java.util.List;

/**
 * Add this to a base object class to be able to access it's components.<br>
 * Add it so that it has <br><code>public ComponentDock components = new ComponentDock();</code><br>
 * So that the object's components can be accessed by calling <br><code>ObjectName.components.get(whatever);</code>
 * @author Sam Gibson
 */
public class ComponentDock {
	private List<Component> componentList = new ArrayList<Component>();
	
	public Component get(long identifier) {
		for (Component component : componentList) {
			if (component.getIdentifier() == identifier) {
				return component;
			}
		}
		return null;
	}
	public Component get(String name) {
		for (Component component : componentList) {
			if (component.getName() == name) {
				return component;
			}
		}
		return null;
	}
	
	/**
	 * Add a component to the dock.
	 * @param component The component to add.
	 * @throws Exception
	 */
	public void add(Component component) throws Exception {
		this.componentList.add(component);
	}
	
	/**
	 * Get the components (<code>BaseComponent</code>) in the dock as an <code>ArrayList</code>.
	 * @return Components in dock.
	 */
	public ArrayList<Component> list() {
		return (ArrayList<Component>)componentList;
	}
}

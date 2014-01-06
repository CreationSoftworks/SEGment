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
	
	public void add(Component component) throws Exception {
		this.componentList.add(component);
	}
	
	public ArrayList<Component> list() {
		return (ArrayList<Component>)componentList;
	}
}

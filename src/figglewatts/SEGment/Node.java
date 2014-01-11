package figglewatts.SEGment;

import java.util.ArrayList;
import java.util.List;

public class Node {
	public String[] dependencies; // put the names of any components that this depends on here (e.g. "Position", and "Display")
	public List<Component> components = new ArrayList<Component>();
	
	private String name;
	
	public String getName() {
		return name;
	}

	public String[] getDependencies() {
		return this.dependencies;
	}
	
	/**
	 * Get an instance of this.
	 * @return An instance of this class.
	 */
	public Node Instance() {
		return this;
	}
	/**
	 * Get an instance of this with the components specified attatched to it.
	 * @param componentArray The components to attatch.
	 * @return The instance with attatched components.
	 */
	public Node Instance(Component[] componentArray) {
		Node instance = this.Instance();
		for (Component component : componentArray) {
			instance.components.add(component);
		}
		return instance;
	}
	
	public Node(String name, Component... componentArguments) {
		this.name = name;
		for (Component component : componentArguments) {
			this.components.add(component);
		}
	}
}

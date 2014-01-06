package figglewatts.SEGment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ComponentEngine {
	private ComponentEngine() { }

	public static Map<String, Long> componentIdentifiers = new HashMap<String, Long>();
	
	private static Map<String[], Node> nodes = new HashMap<String[], Node>();
	
	private static List<BaseObject> objects = new ArrayList<BaseObject>();
	
	public static long getIdentifier(String componentName) {
		if (!componentIdentifiers.containsKey(componentName)) {
			long uniqueID = UUID.randomUUID().getLeastSignificantBits();
			componentIdentifiers.put(componentName, uniqueID);
			return uniqueID;
		} else {
			return componentIdentifiers.get(componentName);
		}
	}
	
	public static void registerNode(Node node) {
		nodes.put(node.dependencies, node);
	}
	
	public static void registerObject(BaseObject object) {
		objects.add(object); // add to list of objects
		
		NodeList objectNodes = new NodeList(); // create instance of nodelist to store this object's nodes in
		String[][] dependencies; // temporary array for each Node's component dependencies
		for (int i = 0; i < object.components.list().size(); i++) { // for each of the object's components
			for (int j = 0; j < object.components.list().size(); i++) { // for each of the object's other components
				if (object.components.list().get(i) == object.components.list().get(j)) { // if the component in this loop is the same as that of the outer loop
					continue; // go to the next iteration, because we can't check to see if it depends on itself
				} else {
					dependencies[i][j] = object.components.list().get
				}
			}
		}
		for (Component component : object.components.list()) {
			String[][] dependenciesArray;
			for (Component i : object.components.list()) {
				
			}
		}
	}
	
	public String[] checkDependencies(ArrayList<Component> components) {
		for (Component component : components) {
			
		}
	}
}

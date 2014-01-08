package figglewatts.SEGment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ComponentEngine {
	private ComponentEngine() { }
	
	// used to identify components using their name
	public static Map<String, Long> componentIdentifiers = new HashMap<String, Long>();
	
	// a store of all nodes in the engine, used when deciding what nodes an object has
	private static Map<String[], Node> nodeCache = new HashMap<String[], Node>();
	
	// this relates object IDs to lists of nodes, used when getting nodes associated with an object
	private static Map<Long, NodeList> nodes = new HashMap<Long, NodeList>();
	
	// a list of all objects currently in the engine
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
		nodeCache.put(node.dependencies, node);
	}
	
	public static void registerObject(BaseObject object) {
		objects.add(object); // add to list of objects
		
		NodeList objectNodes = new NodeList(); // create instance of nodelist to store this object's nodes in
		String[][] dependencies = checkDependencies(object.components.list()); // get 2D array of component names
		int j = 0;
		for (int i = 0; i < object.components.list().size(); i++) { // for every component attatched to object
			while (dependencies[i][j] != null) { // while the next dependency is not null
				for (String[] dependency : nodeCache.keySet()) { // for every dependency cached in nodeCache
					if (dependencies[i] == dependency) { // if generated dependencies match cached dependency
						objectNodes.nodes.add(nodeCache.get(dependency).Instance()); // add an instance of appropriate node to NodeList
					}
				}
			}
			j++; // go to next index of 2nd dimension in dependency array
		}
		nodes.put(object.getID(), objectNodes); // put the object into the component engine
	}
	
	public static void removeObject(BaseObject object) {
		objects.remove(object);
	}
	// WARNING: could be slow; getObjectFromID iterates through every object in the engine
	public static void removeObject(long ID) {
		objects.remove(Util.getObjectFromID(ID));
	}
	
	public static String[][] checkDependencies(ArrayList<Component> components) {
		String[][] dependencies = new String[components.size()][]; // temporary array for each Node's component dependencies
		for (int i = 0; i < components.size(); i++) { // for each of the object's components
			for (int j = 0; j < components.size(); i++) { // for each of the object's other components
				if (components.get(i) == components.get(j)) { // if the component in this loop is the same as that of the outer loop
					continue; // go to the next iteration, because we can't check to see if it depends on itself
				} else {
					// put the name of the component in the 2D array
					dependencies[i][j] = components.get(j).getName();
				}
			}
		}
		if (dependencies.length != 0) { // if we have found dependencies (which we should have!)
			return dependencies;
		} else {
			return null;
		}
	}
	
	public static List<BaseObject> objectList () {
		return objects;
	}
}

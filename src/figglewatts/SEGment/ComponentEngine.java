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
	
	/**
	 * Get the identifier (unique) of a component with it's name.
	 * @param componentName The name of the component.
	 * @return The component identifier associated with component with name <code>componentName</code>
	 */
	public static long getIdentifier(String componentName) {
		if (!componentIdentifiers.containsKey(componentName)) {
			long uniqueID = UUID.randomUUID().getLeastSignificantBits();
			componentIdentifiers.put(componentName, uniqueID);
			return uniqueID;
		} else {
			return componentIdentifiers.get(componentName);
		}
	}
	
	/**
	 * Register a node with the engine. <br />
	 * This needs to be done to every node you create. Simply create an instance of
	 * the node you have created, then call <code>ComponentEngine.registerNode(nodeInstance);</code>. <br />
	 * It will then add the node to a cache, containing the node's dependencies as well as the instance of the node.
	 * <b>IMPORTANT:</b> Please ensure the node is fully initialized before adding it to the engine with this.
	 * @param node An instance of the node to register.
	 */
	public static void registerNode(Node node) {
		nodeCache.put(node.dependencies, node);
	}
	
	/**
	 * Register an object with the engine. <br />
	 * This function adds the object to a list of objects, then calculates each permutation of it's components.
	 * After this, it checks each permutation against the cached list of dependencies for each node,
	 * and if it finds any matches, it will create an instance of the appropriate node and add it to the
	 * object's list of nodes.
	 * @param object The object to add to system.
	 */
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
			j++; // go to next index of 2nd dimension in dependency 2D array
		}
		nodes.put(object.getID(), objectNodes); // put the object into the component engine
	}
	
	/**
	 * Remove an object from the engine.
	 * @param object The object to remove.
	 */
	public static void removeObject(BaseObject object) {
		objects.remove(object);
	}
	/**
	 * Remove an object from the engine. <br />
	 * <b>WARNING:</b> This could be slow, as it uses <code>Util.getObjectFromID()</code>, which needs to
	 * iterate through every object in the system to find the correct one.
	 * @param ID The UUID of the object to remove.
	 */
	public static void removeObject(long ID) {
		objects.remove(Util.getObjectFromID(ID));
	}
	
	/**
	 * Javadoc not required due to it not being exposed to the users. <br />
	 * /sunglasses /thuglyfe
	 * @param components
	 * @return
	 */
	private static DependencyList checkDependencies(ArrayList<Component> components) {
		DependencyList dependencies = new DependencyList();
		for (int i = 0; i < components.size(); i++) { // for each of the object's components
			ArrayList<String> subDependencies = new ArrayList<String>();
			for (int j = 0; j < components.size(); i++) { // for each of the object's other components
				if (components.get(i) == components.get(j)) { // if the component in this loop is the same as that of the outer loop
					continue; // go to the next iteration, because we can't check to see if it depends on itself
				} else {
					// put the name of the component in the 2D array
					subDependencies.add(components.get(j).getName());
				}
			}
			dependencies.dependencies.add(subDependencies);
		}
		if (dependencies.dependencies.isEmpty()) { // if we have found dependencies (which we should have!)
			return dependencies;
		} else {
			return null;
		}
	}
	
	/**
	 * Get the list of objects currently in the engine.
	 * @return The list of objects currently in the engine.
	 */
	public static List<BaseObject> objectList () {
		return objects;
	}
}

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
	private static Map<Node, String[]> nodeCache = new HashMap<Node, String[]>();
	
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
		nodeCache.put(node, node.dependencies);
	}
	
	/**
	 * Register an object with the engine. <br />
	 * This function adds the object to a list of objects, then goes through each node
	 * cached in the system and checks the node's dependencies against the object's components.
	 * If it finds a match, then it creates an instance of that node with the object's components
	 * attatched to it. Finally, it then adds the object's nodes to the engine.
	 * @param object The object to add to the engine.
	 */
	public static void registerObject(BaseObject object) {
		objects.add(object); // add to list of objects in engine
		
		NodeList objectNodes = new NodeList(); // create instance of nodelist to store this object's nodes in
		
		// for each node cached in the engine
		for (Node node : nodeCache.keySet()) {
			// create a temporary list of components to attatch to the node, if it's appropriate for the object
			ArrayList<Component> componentsToAttatch = new ArrayList<Component>();
			// for each of this node's dependencies
			for (int i = 0; i < node.dependencies.length; i++) {
				if (!object.hasComponent(node.dependencies[i])) { // if object doesn't have this dependency
					break; // we don't want to make this node
				} else { // if it does have this dependency
					// add this component to the list of components to attatch
					componentsToAttatch.add(object.getComponent(node.dependencies[i]));
					if (i != node.dependencies.length-1) { // if we're not at the last iteration
						continue; // go to the next iteration
					}
				}
				// object has necessary components for this node! Create it!
				objectNodes.nodes.add(node.Instance((Component[])componentsToAttatch.toArray()));
			}
		}
		nodes.put(object.getID(), objectNodes); // add the object's nodes into the engine
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
	 * Get the list of objects currently in the engine.
	 * @return The list of objects currently in the engine.
	 */
	public static List<BaseObject> objectList () {
		return objects;
	}
}

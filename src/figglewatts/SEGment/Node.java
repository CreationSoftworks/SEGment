package figglewatts.SEGment;

import java.util.ArrayList;
import java.util.List;

public class Node {
	public String[] dependencies; // put the names of any components that this depends on here (e.g. "Position", and "Display")
	public List<Component> components = new ArrayList<Component>();
	
	public Node Instance() {
		return this;
	}
}

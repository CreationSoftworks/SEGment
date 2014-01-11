package figglewatts.SEGment;

public abstract class BaseObject {
	public ComponentDock components = new ComponentDock();
	public String name;
	private long ID;
	
	public long getID() {
		return this.ID;
	}
	
	public boolean hasComponent(String name) {
		if (components.get(name) != null) {
			return true;
		} else {
			return false;
		}
	}
	
	public Component getComponent(String name) {
		return components.get(name);
	}
	
	public BaseObject() {
		this.ID = java.util.UUID.randomUUID().getMostSignificantBits();
	}
}

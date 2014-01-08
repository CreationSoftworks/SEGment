package figglewatts.SEGment;

public abstract class BaseObject {
	public ComponentDock components = new ComponentDock();
	public String name;
	private long ID;
	
	public long getID() {
		return this.ID;
	}
	
	public BaseObject() {
		this.ID = java.util.UUID.randomUUID().getMostSignificantBits();
	}
}

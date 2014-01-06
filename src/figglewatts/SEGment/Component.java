package figglewatts.SEGment;

public class Component {
	private long identifier;
	private String name;

	public long getIdentifier() {
		return identifier;
	}

	public void setIdentifier(long identifier) {
		this.identifier = identifier;
	}
	
	public String getName() {
		return name;
	}
	
	public Component(String componentName) throws Exception {
		this.name = componentName;
		this.identifier = ComponentEngine.getIdentifier(componentName);
		System.out.println("Created new component \"" + this.getName() + "\" with Identifier: " + this.getIdentifier());
	}
}

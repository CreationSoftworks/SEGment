package figglewatts.SEGment;

public class Util {
	private Util() { } // static class
	
	public static BaseObject getObjectFromID(long ID) {
		for (BaseObject object : ComponentEngine.objectList()) {
			if (object.getID() == ID) {
				return object;
			}
		}
		return null;
	}
}

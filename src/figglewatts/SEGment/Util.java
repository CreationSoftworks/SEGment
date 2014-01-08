package figglewatts.SEGment;

public class Util {
	private Util() { } // static class
	
	/**
	 * Get an object in the system from it's ID. <br />
	 * <b>WARNING:</b> This may be quite slow, as it needs to iterate through every object in the system to find
	 * the correct one.
	 * @param ID The UUID of the object in question.
	 * @return The object with this UUID.
	 */
	public static BaseObject getObjectFromID(long ID) {
		for (BaseObject object : ComponentEngine.objectList()) {
			if (object.getID() == ID) {
				return object;
			}
		}
		return null;
	}
}

package de.teamteamteam.spacescooter.level;

/**
 * RuntimeException thrown when parsing (or subsequent actions concerning the LevelConfig)
 * go horribly wrong.
 */
public class LevelConfigException extends RuntimeException {
	
	/**
	 * Default serial Version UID.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Message the Exception carries with it.
	 */
	private String message;
	
	
	/**
	 * Constructor passing on the message.
	 */
	public LevelConfigException(String msg) {
		this.message = msg;
	}

	/**
	 * Add our custom message to the Exceptions representation.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.message);
		sb.append("\n");
		sb.append(super.toString());
		return sb.toString();
	}
	
}

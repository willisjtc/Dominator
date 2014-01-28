package dominion.application.command;

public enum ECommand {
	START_GAME ("start game"),
	BUY ("buy"),
	PLAY ("play"),
	TRASH ("trash"),
	END ("end");
	
	private String strVersion;
	
	private ECommand(String strVersion) {
		this.strVersion = strVersion;
	}
	
	public ECommand getCommandFromValue(String command) {
		for (ECommand eCommand : values()) {
			if (eCommand.strVersion.equals(command)) {
				return eCommand;
			}
		}
		return null;
	}
}

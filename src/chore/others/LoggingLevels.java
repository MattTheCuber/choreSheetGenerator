package chore.others;

public class LoggingLevels {
	public static int FATAL = 0;
	public static int ERROR = 1;
	public static int WARNING = 2;
	public static int EVENT = 3;
	public static int DATA = 4;
	public static int INFO = 5;

	public String levelName[];
	private final int levelCode;

	public void setLevelString() {
		levelName = new String[6];
		levelName[0] = "*!Fatal!*";
		levelName[1] = "*Error*";
		levelName[2] = "Warning!";
		levelName[3] = "Event";
		levelName[4] = "Data";
		levelName[5] = "Info";
	}

	LoggingLevels(int levelCode) {
		this.levelCode = levelCode;
	}

	public int getLevelCode() {
		return this.levelCode;
	}
}

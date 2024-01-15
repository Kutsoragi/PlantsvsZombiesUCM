package tp.p1.logic;

public enum Level {
	EASY(3, 0.1f), HARD(5, 0.2f), INSANE(10, 0.3f);
	
	private int numberOfZombies;
	private float zombieFrequency;
	
	
	private Level(int zombieNum, float zombieFreq){
		numberOfZombies = zombieNum;
		zombieFrequency = zombieFreq;
	}
	public int getAmount() {
		return numberOfZombies;
	}
	public float getFreq() {
		return zombieFrequency;
	}
	public static Level parse(String inputString) {
		for (Level level : Level.values() )
			if (level.name().equalsIgnoreCase(inputString)) return level;
		return null;
	}
	public static String all (String separator) {
		StringBuilder sb = new StringBuilder();
		for (Level level : Level.values() )
			sb.append(level.name() + separator);
		String allLevels = sb.toString();
		return allLevels.substring(0, allLevels.length() - separator.length());
	}
}
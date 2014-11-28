package de.teamteamteam.spacescooter.brain;

/**
 * Score Class to represent the Players Score
 */
public class Score {
	
	/**
	 * Score upper and lower boundaries.
	 */
	private static int score = 0;
	private static int maxScore = 99999999;
	
	
	/**
	 * Private constructor, this class will never be instantiated.
	 */
	private Score() {}
	
	
	/**
	 * Getter for the Score.
	 */
	public static int getScore() {
		return score;
	}

	/**
	 * Setter for the Score.
	 */
	public static void setScore(int score) {
		Score.score = score;
	}

	/**
	 * Method for adding Score, capping it at the maximum value.
	 */
	public static void addScore(int score) {
		Score.score += score;
		if (Score.score >= Score.maxScore) {
			Score.setScore(Score.maxScore);
		}
	}

	/**
	 * Method for removing Score, capping it at zero.
	 */
	public static void removeScore(int score) {
		if (Score.score - score <= 0) {
			Score.setScore(0);
		} else if (Score.score != 0) {
			Score.score -= score;
		}
	}

}

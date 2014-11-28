package de.teamteamteam.spacescooter.brain;

/**
 * Score Class to represent the Player's Score
 */
public class Score {

	/**
	 * Score can be between 0 and 99999999
	 */
	private static int score = 0;
	private static int maxScore = 99999999;

	/**
	 * Getter for the Score
	 */
	public static int getScore() {
		return score;
	}

	/**
	 * Setter for the Score
	 */
	public static void setScore(int score) {
		Score.score = score;
	}

	/**
	 * Method for adding Score
	 */
	public static void addScore(int score) {
		if (Score.score + score >= Score.maxScore) {
			Score.setScore(Score.maxScore);
		} else if (Score.score != Score.maxScore) {
			Score.score += score;
		}
	}

	/**
	 * Method for removing Score
	 */
	public static void removeScore(int score) {
		if (Score.score - score <= 0) {
			Score.setScore(0);
		} else if (Score.score != 0) {
			Score.score -= score;
		}
	}

}

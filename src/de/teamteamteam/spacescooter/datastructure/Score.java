package de.teamteamteam.spacescooter.datastructure;

public class Score {
	
	private static int score = 0;

	public static int getScore() {
		return score;
	}

	public static void setScore(int score) {
		Score.score = score;
	}
	
	public static void addScore(int score) {
		if (Score.score != 99999999)
		Score.score += score;
	}
	
	public static void removeScore(int score) {
		if (Score.score != 0)
		Score.score -= score;
	}

}

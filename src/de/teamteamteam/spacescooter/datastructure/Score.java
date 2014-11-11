package de.teamteamteam.spacescooter.datastructure;

public class Score {
	
	private static int score = 0;
	private static int maxScore = 99999999;

	public static int getScore() {
		return score;
	}

	public static void setScore(int score) {
		Score.score = score;
	}
	
	public static void addScore(int score) {
		if (Score.score + score >= Score.maxScore) {
			Score.setScore(Score.maxScore);
		} else if (Score.score != Score.maxScore) {
			Score.score += score;
		}
	}
	
	public static void removeScore(int score) {
		if (Score.score - score <= 0) {
			Score.setScore(0);
		} else if (Score.score != 0) {
			Score.score -= score;
		}
	}

}

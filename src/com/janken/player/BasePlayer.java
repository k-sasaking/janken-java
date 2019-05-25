package com.janken.player;

public abstract class BasePlayer implements Player {

	protected int hand = -1;
	protected String name;
	private int winCount;
	private int loseCount;
	private int drawCount;

	public String getName() {
		return this.name;
	}

	public int getHand() {
		return this.hand;
	}

	public void win() {
		winCount++;
	}

	public void lose() {
		loseCount++;
	}

	public void draw() {
		drawCount++;
	}

	public void printScore() {
		System.out.println(this.name + "WIN: " + winCount + ", DRAW: " + drawCount + ", LOSE: " + loseCount);
	}

	public int getWinCount() {
		return winCount;
	}

	public int getLoseCount() {
		return loseCount;
	}

	@Override
	public String getHandName() {

		return Hand.getHandStr(this.hand);
	}

	/*
	 * これが内部クラスです。基本Player側からしかアクセスできないようになっています。
	 * 考え方的には、Playerが手を持っているので、内部クラスとしてもよいですが、
	 * ジャンケンのルールを統括するという意味のHand classなら、本来は外だししてもよい。
	 * (むしろ、そうするほうがよかったかなと作ってて思いました。笑)
	 */
	protected static class Hand {
		/*
		 * 例えば 0:グー, 1:チョキ, 2:パーというように扱う。
		 */
		static final String[] hands = { "グー", "チョキ", "パー" };
		private int index;

		boolean setHand(int index) {
			if (index < 0 || 2 < index)
				return false;

			this.index = index;
			return true;
		}

		protected static String getHandStr(int index) {
			return hands[index];
		}

	}

	/* 余談 */
	// enum型という型で定義することもできます。
	// プジェクトによっては、このように書いて扱うことがある場合もあります。
	/*
	 * static enum Hand{
	 *	GU,
	 *	CHOKI,
	 *	PA;
	 * }
	*/

}

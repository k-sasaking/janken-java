package com.janken.strategy;

import java.util.Random;

public class RandomStrategy implements Strategy {

	private Random random;

	@Override
	public int nextHand() {
		// 乱数で値を取得
		random = new Random();
		return random.nextInt(3);
	}

}

package com.janken.strategy;

import java.util.Random;

public class NotPrevStrategy implements Strategy {

	private Random random;
	private int prevHand = -1;

	@Override
	public int nextHand() {
		random = new Random();
		// 前だした手以外の値を乱数で取得
		int nextHand = (prevHand + random.nextInt(2) + 1) % 3;
		// 前回だした手の設定
		prevHand = nextHand;
		return nextHand;
	}

}

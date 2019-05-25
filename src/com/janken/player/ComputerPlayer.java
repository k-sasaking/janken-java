package com.janken.player;

import com.janken.strategy.Strategy;

public class ComputerPlayer extends BasePlayer {

	private static final String DEFAULT_NAME = "COM";
	private static int comPlayerCount;// COMのインスタンス数を数える
	private Strategy strategy;// 戦略をもたせる

	/*
	 *  Computerを定義する際は、戦略クラスを持たせる。
	 *  nameは、COM0,COM1,COM2...のようにする。
	 */

	public ComputerPlayer(Strategy strategy) {
		super();
		this.strategy = strategy;
		comPlayerCount++;
		super.name = DEFAULT_NAME + comPlayerCount;
	}

	public int outHand() {
		super.hand = strategy.nextHand();
		return hand;
	}

}

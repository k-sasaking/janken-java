package com.janken.player;

public class UserPlayer extends BasePlayer{

	public UserPlayer(String name){
		super.name = name;
	}

	public void setHand(int hand) {
		super.hand = hand;
	}

	@Override
	public int outHand() {
		return super.hand;
	}

}

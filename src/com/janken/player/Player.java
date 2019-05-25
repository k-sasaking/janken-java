package com.janken.player;

public interface Player {

	// Player名を取得
	String getName();

	// 出した手を取得
	int getHand();
	// 手を出す
	int outHand();

	// 出した手の名前を取得
	String getHandName();

	// 勝った時の処理
	void win();

	// 負けた時の処理
	void lose();

	// 引き分け時の処理
	void draw();

	// 個人の戦績を表示
	void printScore();

	// 勝利数合計を取得
	int getWinCount();

	// 敗北数合計を取得
	int getLoseCount();

}

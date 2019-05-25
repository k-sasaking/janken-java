package com.janken.main;

import com.janken.player.ComputerPlayer;
import com.janken.player.Player;
import com.janken.player.UserPlayer;
import com.janken.strategy.RandomStrategy;

public class JankenManager {
	/*
	 * ジャンケンゲームを制御するクラス
	 * 主に、ゲーム回数を設定したり、ゲーム開始、ゲーム終了の合図をする役割
	 *
	 */

	private static final int WIN_MAX_COUNT = 3;// 何勝したらゲーム終了か設定
	private Janken janken;
	private Player player1;
	private Player player2;

	private static final String START_MESSAGE = "じゃんけん勝負しましょう。" + WIN_MAX_COUNT + "勝したら勝ち";
	private static final String END_MESSAGE = "終了";
	final String winMessage = "あなたが" + WIN_MAX_COUNT + "勝したので終わり";
	final String loseMessage = "コンピュータが" + WIN_MAX_COUNT + "勝したので終わり";

	// コンストラクタでユーザー名を取得し、プレーヤを設定
	JankenManager(String userName) {
		// ユーザー名を設定し、UserPlayerのインスタンスを生成
		this.player1 = new UserPlayer(userName);
		// COMを自動生成、今回は、RandomStrategyを設定(Strategyクラスを変更すると、Computerに戦略を持たせることが可能)
		this.player2 = new ComputerPlayer(new RandomStrategy());
	}

	// ゲームを行うクラス
	void doGame() {

		// ゲーム開始
		printStartMessage();

		// 設定したゲーム回数まで続ける
		for (int gameCount = 0; getMaxWinCount() < WIN_MAX_COUNT; gameCount++) {
			// 何戦目か表示
			printGameCount(gameCount);
			// 戦うプレーヤを設定
			janken = new Janken(player1, player2);
			// ジャンケンをする
			janken.doJanken();
			// 結果を表示
			printScore();
		}

		// ゲーム終了
		printEndMessage();
	}

	// 勝利数の最大値を取得
	int getMaxWinCount() {
		int result = 0;
		result = Math.max(player1.getWinCount(), player2.getWinCount());
		return result;

	}

	// 開始合図
	void printStartMessage() {
		System.out.println("##############################ゲーム開始##############################");
		printMessage(player1.getName() + "さん、" + START_MESSAGE);
		System.out.println();
	}

	// 終了合図
	void printEndMessage() {
		System.out.println();
		printMessage(END_MESSAGE);
		System.out.println("##############################ゲーム終了##############################");
	}

	// 何戦目か表示
	void printGameCount(int gameCount) {
		System.out.println("> " + gameCount + "回戦目");
	}

	// メッセージを表示するときにデコレーションする際に使用
	void printMessage(String message) {
		System.out.println("> " + message);
	}

	// 戦績を表示
	void printScore() {
		System.out.println();
		System.out.println("====================" + player1.getName() + "さんの戦績====================");
		System.out.println("勝ち： " + player1.getWinCount() + ", 負け: " + player1.getLoseCount());
		System.out.println(
				"====================" + getStrSizeChar(player1.getName() + "さんの戦績", '=') + "====================");
		System.out.println();
	}

	// 引数strの文字数分だけ、引数ｃを表示する
	String getStrSizeChar(String str, char c) {
		int length = str.getBytes().length;
		String result = "";
		for (int i = 0; i < length; i++) {
			result += c;
		}
		return result;
	}

}

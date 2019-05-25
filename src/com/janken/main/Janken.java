package com.janken.main;

import java.util.Scanner;

import com.janken.player.Player;
import com.janken.player.UserPlayer;

public class Janken {

	private Player userPlayer;
	private Player comPlayer;
	private Battle battle;

	final String MENU_MESSAGE = "0:グー 1:チョキ 2:パー";
	final String ERROR_MESSAGE = "正しく数値を入力してください";
	final String BATTLE_MESSAGE = "じゃんけん";
	final String AIKO_MESSAGE = "あいこで";

	// コンストラクタでプレーヤを設定
	Janken(Player player1, Player player2) {
		this.userPlayer = player1;
		this.comPlayer = player2;
	}

	// ジャンケンを行う
	void doJanken() {

		// 引き分けフラグをfalseに初期化
		boolean isDraw = false;

		// 開始処理
		start();
		// 引き分けの場合は、ループする
		while (battle == null || isDraw) {

			// かけ声
			printSayJankenOrAiko(isDraw);

			// 説明
			introduction();

			// ジャンケンで出す手を設定
			((UserPlayer) userPlayer).setHand(inputCommand());

			// ジャンケンをする
			battle = new Battle(userPlayer.outHand(), comPlayer.outHand());
			battle.doBattle();

			// 引き分けの場合は、もう一度
			isDraw = battle.isDraw();
		}

		// 終了処理
		finish();

	}

	// ユーザー入力
	int inputCommand() {
		Integer result = null;
		// 値を入力
		Scanner sc = new Scanner(System.in);
		result = checkInput(sc.nextInt());
		while (result == -1) {
			printMessage(ERROR_MESSAGE);
			printMessage(MENU_MESSAGE);
			sc = new Scanner(System.in);
			result = checkInput(sc.nextInt());
		}

		return (int) result;
	}

	// 入力チェック
	int checkInput(int command) {
		if ((0 <= command && command < 3)) {
			return command;
		}

		return -1;
	}

	// 説明
	void introduction() {
		printMessage(MENU_MESSAGE);
	}

	// 掛け声
	void printSayJankenOrAiko (boolean isDraw) {
		if(isDraw)
			printMessage(AIKO_MESSAGE);
		else
			printMessage(BATTLE_MESSAGE);
	}


	// 開始処理
	void start() {
		battle = null;
	}

	// 終了処理
	void finish() {
		printScore();
	}

	// 結果表示
	void printScore() {
		String winnerName = "";// 勝者
		String loserName = "";// 敗者
		// スコアを設定
		switch (battle.score) {
		case 0:
			winnerName += userPlayer.getName();
			loserName += comPlayer.getName();
			break;
		case 1:
			winnerName += comPlayer.getName();
			loserName += userPlayer.getName();
			break;
		}
		// 結果を表示
		System.out.println();
		System.out.println("----------結果----------");
		System.out.println("勝ち " + winnerName);
		System.out.println("負け " + loserName);
		System.out.println("------------------------");
	}

	void printMessage(String message) {
		System.out.println(">> " + message);
	}

	// 内部クラス 1回あたりの対戦のクラス
	class Battle {

		int hand1;
		int hand2;
		int score = -1;// -1:Draw, 0:Player1 1:Player2

		// コンストラクタで、手の値を設定
		public Battle(int hand1, int hand2) {
			this.hand1 = hand1;
			this.hand2 = hand2;
		}

		// 戦う
		void doBattle() {

			// 出した手を表示
			printHand();

			// 結果を設定
			setScore();
		}

		// 引き分けフラグ
		boolean isDraw() {
			return hand1 == hand2;
		}

		// score getter
		int getScore() {
			return this.score;
		}

		// 結果処理 -1:引き分け、0:player1 win 1:player2 win
		void setScore() {

			// 引き分け
			if (isDraw()) {
				score = -1;

			// Player1の勝ち
			} else if ((hand1 + 1) % 3 == hand2) {
				userPlayer.win();
				comPlayer.lose();
				score = 0;

				// Player2の勝ち
			} else {
				userPlayer.lose();
				comPlayer.win();
				score = 1;
			}
		}

		// 出した手を表示
		void printHand() {
			System.out.println();
			System.out.println(">> " + userPlayer.getName() + ": " + userPlayer.getHandName());
			System.out.println(">> " + comPlayer.getName() + ": " + comPlayer.getHandName());
			System.out.println();
		}

	}
}

package com.janken.main;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		// ユーザー名を設定
		System.out.println("ユーザー名を入力してください。");
		Scanner sc = new Scanner(System.in);
		String user = sc.nextLine();
		JankenManager jankenManager = new JankenManager(user);

		// ゲーム開始
		jankenManager.doGame();

	}

}

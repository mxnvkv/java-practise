package com.tasks.Tasks_from_work.Min_Coins;

import java.util.ArrayList;
import java.util.List;

public class Main {
  private static final int[] COINS_DENOMINATION = {1, 3, 9, 27, 81};
  private static final int MONEY_COUNT = 100;

  public static void main(String[] args) {
    getMinAmountOfCoins();
  }

  private static void getMinAmountOfCoins() {
    for (int i = 1; i <= MONEY_COUNT; i++) {
      int amount = i;
      List<Integer> list = new ArrayList<>();

      while (amount > 0) {
        int maxCoin = getMaxCoinForAmount(amount);
        list.add(maxCoin);
        amount -= maxCoin;
      }

      System.out.println(i + "$ : " + list);
    }
  }

  private static int getMaxCoinForAmount(int amount) {
    int maxCoin = COINS_DENOMINATION[0];

    // assuming that coins sorted in ascending order
    for (int j = (COINS_DENOMINATION.length - 1); j >= 0; j--) {
      if (COINS_DENOMINATION[j] <= amount) {
        maxCoin = COINS_DENOMINATION[j];
        break;
      }
    }

    return maxCoin;
  }
}

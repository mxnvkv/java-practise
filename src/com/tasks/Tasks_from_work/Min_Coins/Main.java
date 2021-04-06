package com.tasks.Tasks_from_work.Min_Coins;

public class Main {
  private static final int[] COINS_DENOMINATION = {1, 3, 9, 27, 81};
  private static final int MONEY_COUNT = 100;

  public static void main(String[] args) {
    System.out.println("Minimum amount of coins required: " + getMinAmountOfCoins());
  }

  private static int getMinAmountOfCoins() {
    int minAmountCounter = 0;

    for (int i = 1; i <= MONEY_COUNT; i++) {
      int amount = i;

      while (amount > 0) {
        amount -= getMaxCoinForAmount(amount);

        minAmountCounter = minAmountCounter + 1;
      }
    }

    return minAmountCounter;
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

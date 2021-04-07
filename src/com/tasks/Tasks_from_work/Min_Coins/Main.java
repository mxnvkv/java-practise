package com.tasks.Tasks_from_work.Min_Coins;

import java.util.ArrayList;
import java.util.List;

public class Main {
  private static List<Integer> coins = new ArrayList<>();
  private static final int[] COINS_DENOMINATION = {1, 3, 9, 27, 81};
  private static final int MONEY_COUNT = 100;

  public static void main(String[] args) {
    getMinAmountOfCoins();
    System.out.println(coins);
  }

  private static void getMinAmountOfCoins() {
    for (int i = 1; i <= MONEY_COUNT; i++) {
      int amount = i;

      // go through list, try to find all necessary coins for the amount
      if (!doWeHaveEnoughCoins(amount, coins)) {
        // if we lack of them, get list of necessary coins for the amount,
        // remove from it coins that we have and add remaining to the current list
        coins.addAll(subtract(getListOfNecessaryCoins(amount), coins));
      }

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

  private static int getMaxCoinForAmount(int amount, List<Integer> coinsList) {
    int maxCoin = coinsList.get(0);

    // assuming that coins sorted in ascending order
    for (int j = (coinsList.size() - 1); j >= 0; j--) {
      if (coinsList.get(j) <= amount) {
        maxCoin = coinsList.get(j);
        break;
      }
    }

    return maxCoin;
  }

  private static boolean doWeHaveEnoughCoins(int amount, List<Integer> coinsList) {
    List<Integer> coinsCopy = new ArrayList<>(coinsList);

    while (coinsCopy.size() > 0 && amount > 0) {
      int maxCoin = getMaxCoinForAmount(amount, coinsCopy);
      amount -= maxCoin;
      coinsCopy.remove(coinsCopy.indexOf(maxCoin));
    }

    return amount == 0;
  }

  private static List<Integer> getListOfNecessaryCoins(int amount) {
    List<Integer> list = new ArrayList<>();

    while (amount > 0) {
      int maxCoin = getMaxCoinForAmount(amount);
      amount -= maxCoin;
      list.add(maxCoin);
    }

    return list;
  }

  private static List<Integer> subtract(
    List<Integer> necessaryCoinsList,
    List<Integer> coinsList
  ) {
    coinsList.forEach(necessaryCoinsList::remove);

    return necessaryCoinsList;
  }
}

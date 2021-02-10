package Tasks_from_work.Apples_and_peaches;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ApplesAndPeaches {
  public static void main(String[] args) {
    /*
      input: 8, 2, 26

      result:
        8 * (0) + 2 * (13)
        8 * (1) + 2 * (9)
        8 * (2) + 2 * (5)
        8 * (3) + 2 * (1)
    */

    calculateFruitsPrice(8, 2, 26);
  }

  public static void calculateFruitsPrice(
    int applesPrice,
    int peachesPrice,
    int totalPrice
  ) {
    int applePriceMaxMultiplier = 0;
    List<List<Integer>> resultList = new ArrayList<>();

    // TODO: find out, why it always adds +1 to the largestPriceMaxMultiplier
    while (applesPrice * applePriceMaxMultiplier <= totalPrice) {
      applePriceMaxMultiplier++;
    }

    // remove!
    applePriceMaxMultiplier--;

    for (int i = 0; i <= applePriceMaxMultiplier; i++) {
      int peachesPriceCounter = 0;

      while (true) {
        if (applesPrice * i + peachesPrice * peachesPriceCounter == totalPrice) {
          resultList.add(new ArrayList<>(Arrays.asList(i, peachesPriceCounter)));
        } else if (applesPrice * i + peachesPrice * peachesPriceCounter > totalPrice) {
          break;
        }

        peachesPriceCounter++;
      }
    }

    printResult(applesPrice, peachesPrice, resultList);
  }

  public static void printResult(
    int applesPrice,
    int peachesPrice,
    List<List<Integer>> resultList
  ) {
    for (List<Integer> list : resultList) {
      System.out.println(String.format(
        "%d apples for $%d and %d peaches for $%d",
        list.get(0),
        applesPrice,
        list.get(1),
        peachesPrice
      ));
    }
  }
}

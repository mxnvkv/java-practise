package Tasks_from_work.Apples_and_peaches;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ApplesAndPeaches {
  public static void main(String[] args) {
    readFromConsole();
  }

  public static void readFromConsole() {
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

      System.out.println("Enter apples price:");
      int applesPrice = Integer.parseInt(br.readLine());

      System.out.println("Enter peaches price:");
      int peachesPrice = Integer.parseInt(br.readLine());

      System.out.println("Enter total price:");
      int totalPrice = Integer.parseInt(br.readLine());

      calculateFruitsPrice(applesPrice, peachesPrice, totalPrice);
    } catch (IOException e) {
      e.printStackTrace();
    }
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

package com.tasks.Tasks_from_work.Furios_Pig;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FuriousPig {
  private int positiveN;
  private int negativeN;
  private int stepsCounter = 0;
  private int pigPosition = 0;
  private int pigSpeed = 1;
  private ArrayList<ArrayList<Integer>> list = new ArrayList<>();
  private final int MAX_STEPS = 10;
  private final int ITERATIONS = 1_000_000;

  FuriousPig() {}

  FuriousPig(int n) {
    this.positiveN = Math.abs(n);
    this.negativeN = -Math.abs(n);
  }

  public void getExpectedStepsV1() {
    getLeftPath(pigPosition, pigSpeed, stepsCounter);
    getRightPath(pigPosition, pigSpeed, stepsCounter);

    double expectedSteps = 0;

    for (int i = 0; i < list.size(); i++) {
      double multiplier = 1.0 / Math.pow(2, list.get(i).get(2));
      expectedSteps += multiplier * list.get(i).get(2);
    }

    System.out.println("Expected steps: " + expectedSteps);
  }

  public void getExpectedStepsV2() {
    ArrayList<Integer> list = new ArrayList<>();

    for (int i = 0; i < ITERATIONS; i++) {
      escape();
      list.add(stepsCounter);
      resetPigData();
    }

    Double average = list.stream().mapToInt(i -> i).average().orElse(0.0);
    System.out.println("Expected steps: " + average);
  }

  public void getExpectedStepsV3() {
    List<List<List<Double>>> list = fillList();

    // s - steps
    // v - speed
    // k - position

    for (int s = 0; s < MAX_STEPS; s++) {
      for (int v = 0; Math.pow(2, v) <= (2 * positiveN); v++) {
        for (int k = negativeN + 1; k < positiveN; k++) {
          int index = k + positiveN;
          double multiplier = 1.0 / 2;
          double speed = Math.pow(2, v);
          double nextStepSpeed = k >= 0 ? Math.pow(2, v + 2) : Math.max(speed / 2, 1); // v

          // s + 1 always
          // v = k >= 0 ? v * 2 : Math.max(v / 2, 1)
          // k = k + v || k - v

          list.get(s).get(v).remove(index);
          list.get(s).get(v).add(
            index,
            multiplier * list.get(s + 1).get(getSpeedIndex(k, v, list.get(s).get(v))).get(getPositionIndex((int) (index - nextStepSpeed)))
              + multiplier * list.get(s + 1).get(getSpeedIndex(k, v, list.get(s).get(v))).get(getPositionIndex((int) (index + nextStepSpeed)))
          );
          // list.get(s).get(v).add(index, -1.0);

          // list.get(s).get(v).remove(index);
          // list.get(s).get(v).add(
          //   index,
          //   (multiplier * list.get(s + 1).get((int) speed).get((int) (k - speed))
          //     + multiplier * list.get(s + 1).get((int) speed).get((int) (k + speed)))
          // );
        }

        System.out.println(String.format(
          "Steps: %d, speed: %f, list of positions: %s",
          s,
          Math.pow(2, v),
          list.get(s).get(v)
        ));
      }
    }
  }

  private List<List<List<Double>>> fillList() {
    List<List<List<Double>>> list = new ArrayList<>();

    for (int iS = 0; iS <= MAX_STEPS; iS++) {
      List<List<Double>> listS = new ArrayList<>();
      list.add(listS);
      for (int iV = 0; Math.pow(2, iV) <= (2 * positiveN); iV++) {
        List<Double> listV = new ArrayList<>();
        list.get(iS).add(listV);
        for (int iK = negativeN; iK <= positiveN; iK++) {
          list.get(iS).get(iV).add(0.0);
        }

        list.get(iS).get(iV).remove(0);
        list.get(iS).get(iV).add(0, 1.0);
        list.get(iS).get(iV).remove(list.get(iS).get(iV).size() - 1);
        list.get(iS).get(iV).add(1.0);
      }
    }

    return list;
  }

  private int getSpeedIndex(int k, int v, List<Double> list) {
    int index;

    if (k >= 0) {
      index = Math.min(v + 1, list.size() - 1);
    } else {
      index = Math.max(v - 1, 0);
    }

    return index;
  }

  private int getPositionIndex(int position) {
    int indexPosition;

    if (position < 0) {
      indexPosition = 0;
    } else {
      indexPosition = positiveN;
    }

    return indexPosition;
  }

  private void getLeftPath(int pigPosition, int pigSpeed, int stepsCounter) {
    if (!isPigStillAlive(stepsCounter)) {
      return;
    }

    stepsCounter++;
    pigPosition -= pigSpeed;
    pigSpeed = adjustPigSpeed(pigSpeed, pigPosition);

    if (hasPigEscaped(pigPosition)) {
      ArrayList<Integer> dataList = new ArrayList<>();
      dataList.add(pigPosition);
      dataList.add(pigSpeed);
      dataList.add(stepsCounter);

      list.add(dataList);

      return;
    }

    getLeftPath(pigPosition, pigSpeed, stepsCounter);
    getRightPath(pigPosition, pigSpeed, stepsCounter);
  }

  private void getRightPath(int pigPosition, int pigSpeed, int stepsCounter) {
    if (!isPigStillAlive(stepsCounter)) {
      return;
    }

    stepsCounter++;
    pigPosition += pigSpeed;
    pigSpeed = adjustPigSpeed(pigSpeed, pigPosition);

    if (hasPigEscaped(pigPosition)) {
      ArrayList<Integer> dataList = new ArrayList<>();
      dataList.add(pigPosition);
      dataList.add(pigSpeed);
      dataList.add(stepsCounter);

      list.add(dataList);

      return;
    }

    getLeftPath(pigPosition, pigSpeed, stepsCounter);
    getRightPath(pigPosition, pigSpeed, stepsCounter);
  }

  private void escape() {
    while (!hasPigEscaped()) {
      movePig();

      if (!isPigStillAlive()) {
        break;
      }
    }
  }

  private void movePig() {
    Random random = new Random();

    if (random.nextBoolean()) {
      goLeft();
    } else {
      goRight();
    }
  }

  private void goLeft() {
    increaseStepsCounter();
    pigPosition -= pigSpeed;
    adjustPigSpeed();
  }

  private void goRight() {
    increaseStepsCounter();
    pigPosition += pigSpeed;
    adjustPigSpeed();
  }

  private boolean hasPigEscaped() {
    return pigPosition >= positiveN || pigPosition <= negativeN;
  }

  private boolean hasPigEscaped(int pigPosition) {
    return pigPosition >= positiveN || pigPosition <= negativeN;
  }

  private boolean isPigStillAlive() {
    return stepsCounter < MAX_STEPS;
  }

  private boolean isPigStillAlive(int stepsCounter) {
    return stepsCounter < MAX_STEPS;
  }

  private void adjustPigSpeed() {
    if (pigPosition > 0) {
      pigSpeed *= 2;
    } else if (pigPosition < 0) {
      pigSpeed = pigSpeed > 1
        ? pigSpeed / 2
        : pigSpeed;
    }
  }

  private int adjustPigSpeed(int pigSpeed, int pigPosition) {
    int adjustedSpeed = pigSpeed;

    if (pigPosition > 0) {
      adjustedSpeed *= 2;
    } else if (pigPosition < 0) {
      adjustedSpeed = pigSpeed > 1
        ? pigSpeed / 2
        : pigSpeed;
    }

    return adjustedSpeed;
  }

  private void increaseStepsCounter() {
    stepsCounter++;
  }

  private void resetPigData() {
    stepsCounter = 0;
    pigPosition = 0;
    pigSpeed = 1;
  }

  private void printStepData() {
    System.out.printf(
      "-N: %d, position: %d, speed: %d, N: %d%n",
      negativeN,
      pigPosition,
      pigSpeed,
      positiveN
    );
  }
}

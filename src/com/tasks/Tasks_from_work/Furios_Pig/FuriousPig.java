package com.tasks.Tasks_from_work.Furios_Pig;

import java.util.ArrayList;
import java.util.Arrays;
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
    double[][][] array = fillArray();
    double multiplier = 1.0 / 2;

    // from 0 steps to maximum possible
    for (int s = array.length - 1; s >= 0; s--) {

      // from 0 to 2n ( speed = 2^v )
      for (int v = 0; v < array[s].length - 1; v++) {

        // from -N to N ( both exclusively )
        for (int k = 1; k < array[s][v].length - 1; k++) {
          int nextSteps = s + 1;
          int nextSpeed = getNextSpeed(array[s], k, v);
          double speedCalculated = Math.pow(2, v);
          int nextPositionLeft = getNextPositionForLeft(speedCalculated, k);
          int nextPositionRight = getNextPositionForRight(speedCalculated, k, array[s][v]);

          // if we have max speed
          if (v == array[s].length - 2) {
            for (int i = 0; i < array[s].length; i++) {
              for (int j = 0; j < array[s][v].length; j++){
                array[s][v][j] = 1;
              }
            }

          // if the next step is last
          } else if (s == array.length - 1) {
            double leftPathValue = nextPositionLeft == 0 ? 1 : 0;
            double rightPathValue = nextPositionRight == array[s][v].length - 1 ? 1 : 0;

            array[s][v][k] = (multiplier * leftPathValue) + (multiplier * rightPathValue);
          } else {
            double leftPathValue = array[nextSteps][nextSpeed][nextPositionLeft];
            double rightPathValue = array[nextSteps][nextSpeed][nextPositionRight];

            array[s][v][k] = (multiplier * leftPathValue) + (multiplier * rightPathValue);
          }
        }

        System.out.println(String.format(
          "Steps: %d, speed: %f, positions: %s",
          s,
          Math.pow(2, v),
          Arrays.toString(array[s][v])
        ));
      }
    }
  }

  public double[][][] fillArray() {
    double[][][] array = new double[MAX_STEPS][2 * positiveN][2 * positiveN + 1];

    for (int s = 0; s < array.length; s++) {
      for (int v = 0; v < array[s].length; v++) {
        array[s][v][0] = 1;
        array[s][v][array[s][v].length - 1] = 1;
      }
    }

    return array;
  }

  private int getNextSpeed(double[][] array, int k, int v) {
    int nextSpeed;

    if (k < array.length / 2) {
      nextSpeed = Math.max(v - 1, 0);
    } else {
      nextSpeed = Math.min(v + 1, array[0].length);
    }

    // if (nextSpeed >= array[0].length) {
    //   nextSpeed = array[0].length - 1;
    // }

    return nextSpeed;
  }

  private int getNextPositionForLeft(double speed, int position) {
    int nextPosition;

    if (position - speed < 0) {
      nextPosition = 0;
    } else {
      nextPosition = (int) (position - speed);
    }

    return nextPosition;
  }

  private int getNextPositionForRight(double speed, int position, double[] posArray) {
    int nextPosition;

    if (position + speed >= posArray.length) {
      nextPosition = posArray.length - 1;
    } else {
      nextPosition = (int) (position + speed);
    }

    return nextPosition;
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

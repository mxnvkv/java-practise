package com.tasks.Tasks_from_work.Furios_Pig;

import java.util.ArrayList;
import java.util.Random;

public class FuriousPig {
  private int positiveN;
  private int negativeN;
  private int stepsCounter = 0;
  private int pigPosition = 0;
  private int pigSpeed = 1;
  private final int MAX_STEPS = 10;
  private final int ITERATIONS = 1_000_000;

  FuriousPig() {}

  FuriousPig(int n) {
    this.positiveN = Math.abs(n);
    this.negativeN = -Math.abs(n);
  }

  public int getAllPaths() {
    int leftPaths = getLeftPath(pigPosition, pigSpeed, stepsCounter);
    int rightPaths = getRightPath(pigPosition, pigSpeed, stepsCounter);

    return leftPaths + rightPaths;
  }

  public void getExpectedSteps() {
    ArrayList<Integer> list = new ArrayList<>();

    for (int i = 0; i < ITERATIONS; i++) {
      escape();
      list.add(stepsCounter);
      resetPigData();
    }

    Double average = list.stream().mapToInt(i -> i).average().orElse(0.0);
    System.out.println(average);
  }

  private int getLeftPath(int pigPosition, int pigSpeed, int stepsCounter) {
    if (!isPigStillAlive(stepsCounter)) {
      System.out.println("pig is dead");
      return 0;
    }

    stepsCounter++;
    pigPosition -= pigSpeed;
    pigSpeed = adjustPigSpeed(pigSpeed, pigPosition);

    if (hasPigEscaped(pigPosition)) {
      System.out.println(String.format("position: %d, speed: %d, steps: %d", pigPosition, pigSpeed, stepsCounter));
      return 1;
    }

    int leftPaths = getLeftPath(pigPosition, pigSpeed, stepsCounter);
    int rightPaths = getRightPath(pigPosition, pigSpeed, stepsCounter);

    return leftPaths + rightPaths;
  }

  private int getRightPath(int pigPosition, int pigSpeed, int stepsCounter) {
    if (!isPigStillAlive(stepsCounter)) {
      System.out.println("pig is dead");
      return 0;
    }

    stepsCounter++;
    pigPosition += pigSpeed;
    pigSpeed = adjustPigSpeed(pigSpeed, pigPosition);

    if (hasPigEscaped(pigPosition)) {
      System.out.println(String.format("position: %d, speed: %d, steps: %d", pigPosition, pigSpeed, stepsCounter));
      return 1;
    }

    int leftPaths = getLeftPath(pigPosition, pigSpeed, stepsCounter);
    int rightPaths = getRightPath(pigPosition, pigSpeed, stepsCounter);

    return leftPaths + rightPaths;
  }

  private void escape() {
    while (!hasPigEscaped()) {
      movePig();

      if (!isPigStillAlive()) {
        System.out.println("Unfortunately, pig has died from starvation");
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

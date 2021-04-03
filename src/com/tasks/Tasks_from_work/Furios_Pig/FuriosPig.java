package com.tasks.Tasks_from_work.Furios_Pig;

import java.util.ArrayList;
import java.util.Random;

public class FuriosPig {
  private int positiveN;
  private int negativeN;
  private int stepsCounter = 0;
  private int pigPosition = 0;
  private int pigSpeed = 1;
  private final int MAX_STEPS = 10_000_000;
  private final int ITERATIONS = 1_000_000;

  FuriosPig() {}

  FuriosPig(int n) {
    this.positiveN = Math.abs(n);
    this.negativeN = -Math.abs(n);
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

  private boolean isPigStillAlive() {
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

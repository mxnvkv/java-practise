package com.tasks.Tasks_from_work.Furios_Pig;

import java.util.Random;

public class FuriosPig {
  private int positiveN;
  private int negativeN;
  private int stepsCounter = 0;
  private int pigPosition = 0;
  private final int MAX_STEPS = 10_000_000;

  FuriosPig() {}

  FuriosPig(int n) {
    this.positiveN = Math.abs(n);
    this.negativeN = -Math.abs(n);
  }

  public void escape() {
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
      printStepData();
    } else {
      goRight();
      printStepData();
    }
  }

  private void goLeft() {
    stepsCounter++;
    pigPosition--;
  }

  private void goRight() {
    stepsCounter++;
    pigPosition++;
  }

  private void printStepData() {
    System.out.println(String.format(
      "-N: %d, position: %d, N: %d",
      negativeN,
      pigPosition,
      positiveN
    ));
  }

  private boolean hasPigEscaped() {
    return pigPosition == positiveN || pigPosition == negativeN;
  }

  private boolean isPigStillAlive() {
    return stepsCounter < MAX_STEPS;
  }
}

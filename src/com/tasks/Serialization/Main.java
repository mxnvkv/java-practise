package com.tasks.Serialization;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
  public static void main(String[] args) {
    BankAccount bankAccount = new BankAccount("AB01", 500);
    bankAccount.deposit(400);

    saveAccount(bankAccount, "account.dat");

    BankAccount ba = loadAccount("account.dat");
    System.out.println(ba.getId() + " : " + ba.getBalance());
    System.out.println(ba);
  }

  public static void saveAccount(BankAccount ba, String filename) {
    try(ObjectOutputStream objectStream =
      new ObjectOutputStream(Files.newOutputStream(Paths.get(filename)))) {
      objectStream.writeObject(ba);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static BankAccount loadAccount(String filename) {
    BankAccount ba = null;

    try(ObjectInputStream objectStream =
      new ObjectInputStream(Files.newInputStream(Paths.get(filename)))) {
      ba = (BankAccount) objectStream.readObject();
    } catch (ClassNotFoundException | IOException e) {
      e.printStackTrace();
    }

    return ba;
  }
}

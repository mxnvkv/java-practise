package Serialization;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class BankAccount implements Serializable {
  private static final long serialVersionUID = 7987352599099921436L;

  private String id;
  private int balance = 0;
  transient private char lastTxType; // won't be serialized
  private int lastTxAmount;

  BankAccount(String id) {
    this.id = id;
  }

  BankAccount(String id, int balance) {
    this.id = id;
    this.balance = balance;
  }

  public String getId() {
    return id;
  }

  public int getBalance() {
    return balance;
  }

  public synchronized void deposit(int amount) {
    this.balance += amount;
    lastTxType = 'd';
    lastTxAmount = amount;
  }

  public synchronized void withdrawal(int amount) {
    this.balance -= amount;
    lastTxType = 'w';
    lastTxAmount = amount;
  }

  private void writeObject(ObjectOutputStream out) throws IOException {
    out.defaultWriteObject();
  }

  private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
    ObjectInputStream.GetField fields = in.readFields();

    id = (String) fields.get("id", null);
    balance = fields.get("balance", 0);
    // remove 'transient' keyword and uncomment this line
    // lastTxType = fields.get("lastTxType", 'u');
    lastTxAmount = fields.get("lastTxAmount", -1);
  }

  @Override
  public String toString() {
    return id + " " + balance + " " + lastTxType + " " + lastTxAmount;
  }
}

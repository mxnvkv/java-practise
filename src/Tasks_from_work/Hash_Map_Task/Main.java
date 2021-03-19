package Tasks_from_work.Hash_Map_Task;

public class Main {
  public static void main(String[] args) {
    HashMapWrapper hashMap = new HashMapWrapper();

    hashMap.add("5852", "luck");
    hashMap.add("5852", "juck");
    hashMap.add("27753", "apple");
    hashMap.add("6262", "nana");
    hashMap.add("672643", "orange");
    hashMap.add("5852", "kuck");
    hashMap.add("6262", "obob");

    System.out.println(hashMap.get("5852"));
  }
}

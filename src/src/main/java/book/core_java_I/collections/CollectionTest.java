/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2018，所有权利保留。
 * <p>
 * 项目名：  steping
 * 文件名：  CollectionTest.java
 * 模块说明：
 * 修改历史：
 * 2018-08-29 - Songyanyan - 创建。
 */
package book.core_java_I.collections;

import java.time.LocalDate;
import java.util.*;

/**
 * 集合用法测试
 *
 * @author Songyanyan
 */
public class CollectionTest {
  private final static String DIVISION_LINE = "--------------------------";

  public static void main(String[] args) {
    ArrayList<String> arrayList = new ArrayList<>(); // 非同步
    Vector<String> vector = new Vector<>(); // 方法都是同步的

    // 有序链表
    LinkedList<String> linkedList = new LinkedList<>();
    linkedList.add("this");
    linkedList.add("is");
    linkedList.add("a");
    linkedList.add("sentence");

    ListIterator<String> iterator = linkedList.listIterator();
    while (iterator.hasNext()) {
      if (iterator.next() == "sentence")
        iterator.add("complete");
    }

    // 无序集
    Set<String> set = new HashSet<String>();
    set.add("hello");
    set.add("this");
    set.add("is");
    set.add("a");
    set.add("sunny");
    set.add("day");
    Iterator<String> setIterator = set.iterator();
    while (setIterator.hasNext()) {
      System.out.println(setIterator.next());
    }
    System.out.println(DIVISION_LINE);

    // 树集 有序
    SortedSet<Item> parts = new TreeSet<>();
    parts.add(new Item("Hello", 1));
    parts.add(new Item("This", 2));
    parts.add(new Item("Is", 3));
    System.out.println(parts);

    NavigableSet<Item> sortByDescription = new TreeSet<>(
      Comparator.comparing(Item::getDescription));
    NavigableSet<Item> sortByNumber = new TreeSet<>(
      Comparator.comparing(Item::getPartNumber));
    sortByDescription.addAll(parts);
    sortByNumber.addAll(parts);
    System.out.println("According to Description" + sortByDescription);
    System.out.println("According to Number" + sortByNumber);
    System.out.println("sortByNumber 逆序：" + sortByNumber.descendingSet());
    System.out.println(DIVISION_LINE);

    // 优先级队列
    PriorityQueue<LocalDate> pq = new PriorityQueue<>();
    pq.add(LocalDate.of(1906, 12, 9)); // G. Hopper
    pq.add(LocalDate.of(1815, 12, 10)); // A. Lovelace
    pq.add(LocalDate.of(1903, 12, 3)); // J. von Neumann
    pq.add(LocalDate.of(1910, 6, 22)); // K. Zuse

    System.out.println("Iterating over elements...");
    for (LocalDate date : pq)
      System.out.println(date);
    System.out.println("Removing elements...");
    while (!pq.isEmpty())
      System.out.println(pq.remove());

    PriorityQueue<Item> itemPriorityQueue = new PriorityQueue<>(Comparator.comparing(
      Item::getPartNumber));
    itemPriorityQueue.addAll(parts);

    HashMap<String, String> hashMap = new HashMap<>();

    hashMap.putIfAbsent("word", "");
    hashMap.put("word", hashMap.get("word") + "string");

    hashMap.put("word", hashMap.getOrDefault("word", "defaultStr"));

    hashMap.merge("word", "defaultStr", String::concat);

    // 映射视图
    Set<String> hashMapKeySet = hashMap.keySet();
    Collection<String> hashMapValues = hashMap.values();
    Set<Map.Entry<String, String>> hashMapEntrySet = hashMap.entrySet();

    hashMap.forEach((k, v) -> {
      // do sth. with k v
    });

    // 枚举集与映射
    EnumSet<Weekday> weekdays = EnumSet.allOf(Weekday.class);
    EnumSet<Weekday> none = EnumSet.noneOf(Weekday.class);
    EnumSet<Weekday> workday = EnumSet.range(Weekday.MONDAY, Weekday.TUESDAY);

    Arrays.asList("hello", "world");

    Collections.unmodifiableCollection(parts);
    Collections.unmodifiableList(linkedList);
    Collections.unmodifiableSet(parts);
    Collections.unmodifiableSortedSet(parts);
    Collections.unmodifiableNavigableSet(sortByNumber);
    Collections.unmodifiableMap(hashMap);

    // 同步视图
    Map<String, Item> map = Collections.synchronizedMap(new HashMap<String, Item>());

    // 集合排序 顺排 混排 指定顺序排 逆序
    Collections.sort(linkedList);
    Collections.shuffle(linkedList);
    linkedList.sort(Comparator.comparing(String::toLowerCase));
    linkedList.sort(Comparator.reverseOrder());
  }
}

enum Weekday {
  MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}
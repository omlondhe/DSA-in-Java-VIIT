import java.util.*;

class LinkedListDemo {
    public static void main(String []ar) {
        LinkedList<Integer> linkedList = new LinkedList<Integer>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(0, 0);
        linkedList.add(4, 4);
        linkedList.addLast(5);
        System.out.println(linkedList.contains(4));
        System.out.println(linkedList.getFirst());;
        System.out.println(linkedList);
    }
}
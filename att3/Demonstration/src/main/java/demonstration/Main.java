package demonstration;

import circularLinkedList.CircularLinkedList;

public class Main {

    public static void main(String[] args) {
        CircularLinkedList<Integer> cll = new CircularLinkedList<>();
        cll.add(2);
        cll.add(3);
        cll.add(4);
        cll.delete(2);
        cll.addHead(5);
        cll.insert(6,1);
        cll.delete(3);
        cll.addTail(7);
        cll.list();
    }
}

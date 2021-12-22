package circularLinkedList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CircularLinkedListTest {
    @Test
    public void addTest (){
        CircularLinkedList<Integer> cll = new CircularLinkedList<>();
        cll.add(2);
        cll.add(7);
        cll.add(24);
        Assertions.assertTrue(cll.get(0) == 2);
        Assertions.assertTrue(cll.get(1) == 7);
        Assertions.assertTrue(cll.get(2) == 24);
    }

    @Test
    public void setTest (){
        CircularLinkedList<Integer> cll = new CircularLinkedList<>();
        cll.add(2);
        cll.add(7);
        cll.add(24);
        cll.set(5, 1);
        Assertions.assertTrue(cll.get(1) == 5);
    }

    @Test
    public void insertTest (){
        CircularLinkedList<Integer> cll = new CircularLinkedList<>();
        cll.add(2);
        cll.add(7);
        cll.add(24);
        cll.insert(6,2);
        Assertions.assertTrue(cll.get(2) == 6);
    }

    @Test
    public void insertLastTest (){
        CircularLinkedList<Integer> cll = new CircularLinkedList<>();
        cll.add(2);
        cll.add(7);
        cll.add(24);
        cll.insert(6,3);
        Assertions.assertTrue(cll.get(3) == 6);
    }

    @Test
    public void deleteTest (){
        CircularLinkedList<Integer> cll = new CircularLinkedList<>();
        cll.add(2);
        cll.add(7);
        cll.add(24);
        cll.delete(2);
        Assertions.assertTrue(cll.get(0) == 7);
        Assertions.assertTrue(cll.get(1) == 24);
    }

    @Test
    public void containsTest() {
        CircularLinkedList<Integer> cll = new CircularLinkedList<>();
        cll.add(2);
        cll.add(7);
        cll.add(24);
        Assertions.assertTrue(!cll.contains(0));
    }

    @Test
    public void indexOfTest() {
        CircularLinkedList<Integer> cll = new CircularLinkedList<>();
        cll.add(2);
        cll.add(7);
        cll.add(24);
        Assertions.assertTrue(cll.indexOf(7) == 1);
    }
}

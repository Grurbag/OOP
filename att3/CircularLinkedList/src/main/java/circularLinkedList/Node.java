package circularLinkedList;

public class Node<T> {
    public T value;
    public Node<T> nextNode;
    public Node<T> prevNode;

    public Node(T value) {
        this.value = value;
    }

    public String toString() {
        return "Node{" +
                "value='" + this.value + '\'' +
                '}';
    }
}

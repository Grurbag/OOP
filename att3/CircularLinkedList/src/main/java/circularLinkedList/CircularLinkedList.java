package circularLinkedList;

public class CircularLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public CircularLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    public T get(int n) {
        Node<T> temp = null;
        if (!isEmpty()) {
            temp = head;
            if (!isEmpty()) {
                for (int i = 0; i < n; i++) {
                    temp = temp.nextNode;
                }
            }
        }
        return temp.value;
    }

    public void set(T value, int n) {
        if (!isEmpty()) {
            Node<T> newNode = new Node(value);
            Node<T> temp = head;
            for (int i = 0; i < n; i++) {
                if (temp.nextNode == null) {
                    break;
                }
                temp = temp.nextNode;
                if (i == n - 1) {
                    temp.value = newNode.value;
                }
            }
        }
    }

    public void insert(T value, int n) {
        if (!isEmpty() && n <= size) {
            Node<T> newNode = new Node<>(value);
            Node<T> temp = head;
            boolean flag = false;
            if (n == 0) {
                addHead(value);
                return;
            }
            if (n == size) {
                addTail(value);
                return;
            }
            for (int i = 0; i < n; i++){
                if (temp.nextNode == null){
                    break;
                }
                if (i == n - 1 && temp.nextNode != head) {
                    flag = true;
                }
                temp = temp.nextNode;
            }
            if (flag) {
                newNode.prevNode = temp.prevNode;
                newNode.nextNode = temp;
                temp.prevNode.nextNode = newNode;
                temp.prevNode = newNode;
                size++;
            }
        }
    }

    public void add(T value) {
        Node<T> newNode = new Node<>(value);
        if (isEmpty()) {
            head = newNode;
        } else {
            newNode.prevNode = tail;
            tail.nextNode = newNode;
        }
        tail = newNode;
        tail.nextNode = head;
        head.prevNode = tail;
        size++;
    }

    public void addHead(T value) {
        Node<T> newNode = new Node<>(value);
        if (isEmpty()){
            head = newNode;
            tail = newNode;
            tail.nextNode = head;
            head.prevNode = tail;
        } else {
            newNode.nextNode = head;
            head.prevNode = newNode;
            head = newNode;
            newNode.prevNode = tail;
            tail.nextNode = newNode;
        }
        size++;
    }

    public void addTail(T value) {
        Node<T> newNode = new Node<>(value);
        if (isEmpty()){
            head = newNode;
            tail = newNode;
            tail.nextNode = head;
            head.prevNode = tail;
            size++;
        } else {
            newNode.nextNode = head;
            head.prevNode = newNode;
            newNode.prevNode = tail;
            tail.nextNode = newNode;
            tail = newNode;
            size++;
        }
    }

    public void deleteTail() {
        if (!isEmpty()) {
            this.tail = tail.prevNode;
            tail.nextNode = head;
            head.prevNode = tail;
            size--;
        }
    }

    public void deleteHead() {
        if (!isEmpty()) {
            this.head = head.nextNode;
            tail.nextNode = head;
            head.prevNode = tail;
            size--;
        }
    }

    public void delete(T value) {
        if (!isEmpty()) {
            Node<T> currentNode = head;
            if (head.value == value){
                deleteHead();
            }
            if (tail.value == value){
                deleteTail();
            }
            while (currentNode != tail) {
                currentNode = currentNode.nextNode;
                if (currentNode.value == value) {
                    currentNode.prevNode.nextNode = currentNode.nextNode;
                    currentNode.nextNode.prevNode = currentNode.prevNode;
                    size--;
                }
            }
        }
    }

    public boolean contains(T value) {
        if(!isEmpty()){
            Node<T> currentNode = head;
            for (int i = 0; i < size; i++){
                if (currentNode.value == value) {
                    return true;
                }
                currentNode = currentNode.nextNode;
            }
        }
        return false;
    }

    public int indexOf(T value) {
        if(!isEmpty()){
            Node<T> currentNode = head;
            for (int i = 0; i < size; i++){
                if (currentNode.value == value) {
                    return i;
                }
                currentNode = currentNode.nextNode;
            }
        }
        return -1;
    }

    public void list() {
        if(isEmpty()){
            System.out.println("Список пуст");
            return;
        }
        Node<T> temp = head;
        for (int i = 0; i < size; i++){
            System.out.println(temp.toString());
            temp = temp.nextNode;
        }
    }
}




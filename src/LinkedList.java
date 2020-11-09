// LinkedList implementation
public class LinkedList<T> implements List<T> {
    Node<T> head;
    Node<T> tail;
    int size;

    // constructing the class with head and tail and a size variable
    public LinkedList() {
        head = tail = null;
        size = 0;
    }


    @Override
    public boolean add(T item) {
        Node<T> node = new Node(item);
        // Special case for empty list
        if (head == null) {
            head = node;
            tail = node;
            size = 1;
        // Adds new element to the end of the list and increments size
        } else {
            tail.next = node;
            tail = node;
            size++;
        }
        return true;
    }

    @Override
    public T get(int idx) {
        // returns null when idx is out of range
        if (idx<0 || idx>size){ return null; }
        Node cur = head;
        int i=0;
        //walks down the list to the given idx
        while (cur!=null){
            if (i==idx)
                return (T) cur.value;
            i++;
            cur = cur.next;
        }
        return null;
    }

    @Override
    // overloaded add method for adding an item that to a specific idx
    public void add(int idx, T item) throws Exception {
        Node node = new Node(item);
        if (idx<0 || idx>=size){
            throw new Exception("Out of range.");
        }
        if (head == null) {
            head = node;
            tail = node;
            size = 1;
        } else {
            int i = 0;
            if(idx==0){
                node.next = head;
                head = node;
                size++;
            } else {
                Node prev = null;
                for (Node cur = head; cur != null; cur = cur.next) {
                    if (i==idx-1){
                        prev=cur;
                    } else if (i==idx) {
                        prev.next = node;
                        node.next = cur;
                        size++;
                    }
                }
            }
        }
    }

    @Override
    public T remove(int idx) throws Exception {
        // throws exception when idx is out of range
        if (idx<0 || idx>=size){
            throw new Exception("Out of range.");
        }

        // special case is head is null
        if (head == null) {
            return null;
        } else {
            int i = 0;
            // special case:head is one being removed
            if (idx == 0) {
                if (tail == head) {
                    tail = null;
                }
                head = head.next;
            }
        }
        //  main case
        Node cur = head;
        Node tem = null;
        int i = 0;
        while (cur != null) {
            if (i == idx-1) {
                // remove it
                tem = cur;
                cur.next = cur.next.next;
                size--;
                // if we removed the last node, reset the tail
                if (cur.next == null) {
                    tail = cur;
                }
                return (T) tem;
            } else {
                cur = cur.next;
                i++;
            }

        }

        return null;
    }

    // getter for size
    @Override
    public int size() {
        return size;
    }

    private class Node <E>{
        E value;
        Node<E> next;
        public Node(E value){
            this.value = value;
            this.next=null;
        }
    }
    public static void main(String [] args) throws Exception {
        LinkedList<Planet> uni = new LinkedList<Planet>();
        Planet a = new Planet("P0",200, 100, 100, -250, -250.0, 150.0, 10);
        Planet b = new Planet("P1",200, 150, 150, 100, 100.0, 100.0, 10);
        uni.add(a);
        uni.add(b);
    }

}

package deque;
import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T>{
    private class Node{
        public T item;
        public Node prev;
        public Node next;

        public Node(T item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        Node rest = sentinel.next;
        sentinel.next = new Node(item, sentinel, rest);
        rest.prev = sentinel.next;
        size+= 1;
    }

    @Override
    public void addLast(T item){
        Node back = sentinel.prev;
        sentinel.prev = new Node(item, back, sentinel);
        back.next = sentinel.prev;
        size += 1;
    }

    @Override
    public T removeFirst() {
        if(isEmpty())
            return null;

        Node oldFront = sentinel.next;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        oldFront.prev = null;
        oldFront.next = null;
        size -= 1;
        return oldFront.item;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    boolean isEmpty() {
        return super.isEmpty();
    }
}

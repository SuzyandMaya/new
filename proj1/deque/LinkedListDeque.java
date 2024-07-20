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
    public T get(int i){
        if(isEmpty() || i < 0)
            return null;

        Node p = sentinel.next;
        int count = 0;
        while(p != sentinel){
            if(count == i)
                return p.item;

            p = p.next;
            count++;
        }
        return null;
    }

    public T getRecursive(int index){
        if (isEmpty() || index < 0){
            return null;
        }
        return getRecursiveHelper(index, sentinel.next);
    }

    private T getRecursiveHelper(int i, Node p){
        if(p == sentinel)
            return null;
        if(i == 0)
            return p.item;
        return getRecursiveHelper(i - 1, p.next);
    }

    /* Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line. */
    @Override
    public void printDeque(){
        Node p = sentinel.next;

        if (p == sentinel){
            System.out.println("This is empty deque");
            return;
        }
        while(p != sentinel){
            if (p.next == sentinel){
                System.out.print(p.item);
                System.out.println();
                return;//直接返回，不进行下面的，先if可以不用分类
            }
            System.out.print(p.item + " -> ");
            p = p.next;
        }
    }

    public Iterator<T> iterator(){
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T>{
        private int index;

        public LinkedListDequeIterator(){cnt = 0;};

        public boolean hasNext() {
            return index < size();
        }

        public T next() {
            T returnItem = get(index);
            index += 1;
            return returnItem;
        }
    }

    @Override
    public boolean equals(Object o){
        if(this == o)
            return true;
        if(!(o instanceof Deque))
            return false;

        Deque<T> obj = (Deque<T>) o;
        if(obj.size() != size())
            return false;

        for(int i = 0; i < obj.size(); i++){
            T itemFromobj = obj.get(i);
            T itemFromDeque = this.get(i);
            if(!itemFromobj.equals(itemFromDeque))
                return false;
        }
        return true;
    }


}

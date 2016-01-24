package bag;

import BagUtil.BagInterface;

/**
 * Created by hayesj3 on 1/21/2016.
 */
public class LinkedBag<T extends Object> implements BagInterface<T> {

    Node first = null;
    int size = 0;

    public LinkedBag() { this(null); }
    public LinkedBag(Node first) {
        this.first = first;
        if(first != null) { size++; }
    }

    /**
     * Searches for <code>T item</code> in the bag. If found, traversing
     * n links in the chain(where n is the value returned from this method. and n != -1)
     * will result in the node containing <code>item</code>.
     *
     * @param item the item to search for
     * @return if found, the position(zero-indexed) of the node in the bag containing the item; -1 if not found
     */
    private int search(T item) {
        int pos = 0;
        boolean found = false;

        for (Node n =first; n!=null; n=n.getNext(), pos++) {
            if(n.getData().equals(item)) {
                found = true;
                break;
            }
        }
        return (found ? pos : -1);
    }

    @Override
    public int getCurrentSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size <= 0);
    }

    @Override
    public boolean add(T item) {
        Node temp = new Node(item);
        if(first != null) {
            temp.setNext(first);
        }
        first = temp;
        size++;
        return true;
    }

    @Override
    public boolean remove(T item) {
        boolean removed = false;
        int pos = search(item);
        Node n = first;
        if(pos == -1) {
            removed = false;
        } else {
            for(int i = 0; i < pos; i++, n=n.getNext());
            n = n.getNext().getNext();
            removed = true;
        }
        return removed;
    }

    @Override
    public T remove() {
        T removed = (T) first.getData();
        first = first.getNext();
        return removed;
    }

    @Override
    public void clear() {
        first = null;
        size = 0;
    }

    @Override
    public boolean contains(T item) {
        return (search(item) != -1);
    }

    @Override
    public int getFrequencyOf(T item) {
        int freq = 0;
        int firstPos = this.search(item);
        Node n = this.getFirstNode();
        if(firstPos != -1) {
            for (int i = 0; i < firstPos; i++, n=n.getNext());
            for (int i = firstPos; n != null && i < size; i++, n=n.getNext()) {
                if(n.getData().equals(item)) { freq++; }
            }
        }
        return freq;
    }

    @Override
    public T[] toArray() {
        T[] array = (T[]) new Object[size];
        Node n = first;
        for(int i = 0; i < size; i++, n=n.getNext()) {
            array[i] = (T) n.getData();
        }
        return array;
    }

    public LinkedBag union(LinkedBag otherBag) {
        LinkedBag<T> combinedBag = new LinkedBag<>();
        for(Node n = this.getFirstNode(); n != null; n=n.getNext()){
            combinedBag.add(n.getData());
        }
        for (Node n = otherBag.getFirstNode(); n != null; n=n.getNext()) {
            combinedBag.add(n.getData());
        }
        return combinedBag;
    }

    public LinkedBag intersection(LinkedBag<T> otherBag) {
        LinkedBag<T> combinedBag = new LinkedBag<>();

        T item = null;
        int count = 0;
        int skip = 0;
        for (Node n = this.getFirstNode(); n != null; n=n.getNext()) {
            item = n.getData();
            skip = 0;
            if(!otherBag.contains(item)) { continue; }
            count = Math.min(this.getFrequencyOf(item), otherBag.getFrequencyOf(item));

            for (int j = 0; j < count; j++) {
                combinedBag.add(item);
            }

            skip = this.getFrequencyOf(item) - count;
            for (int i = 0; i < skip; i++, n= n.getNext());
        }
        return combinedBag;
    }

    /**
     * Obtains all items in <code>this</code> but not in <code>otherBag</code>, and EXCLUDING the items
     * in <code>otherBag</code> but not in <code>this</code>.
     * @param otherBag the bag to intersect with <code>this</code>
     * @return a LinkedBag contains all items in <code>this</code> but not in <code>otherBag</code>
     */
    public LinkedBag inverseIntersection(LinkedBag otherBag) {
        LinkedBag<T> combinedBag = new LinkedBag<>();
        LinkedBag<T> intersectedBag = this.intersection(otherBag);

        T item = null;
        int count = 0;
        for (Node n = this.getFirstNode(); n != null; n=n.getNext()) {
            item = n.getData();
            count = this.getFrequencyOf(item) - intersectedBag.getFrequencyOf(item);

            for (int j = 0; j < count; j++) {
                combinedBag.add(item);
            }
        }
        return combinedBag;
    }
    public void fromArray(T[] items) {
        this.clear();
        for (int i = 0; i < items.length; i++) {
            this.add(items[i]);
        }
    }

    @Override
    public String toString() {
        StringBuilder strbldr = new StringBuilder("");
        for (Node n = getFirstNode(); n != null; n=n.getNext())
            //Calling toString on data will force a String value; toString() is guaranteed to exist,
            //  at least in its un-overriden form, because the type of the data is <T extends Object>
            strbldr.append(n.getData().toString() + " ");
        return strbldr.toString();
    }

    public Node getFirstNode() { return first; }
    public void setFirstNode(Node first) { this.first = first; }

    public static void main(String[] args) {
        BagTester<String> testStr = new BagTester<>();
        BagTester<Integer> testInt = new BagTester<>();

        System.out.println("Starting LinkedBag<T> Test");

        System.out.println("Starting LinkedBag<String> Test");
        testStr.startTest(new String[] {"Apples", "Oranges", "Bananas", "Kiwi", "Pomegranate", "Pear"}, "string");
        System.out.println("Completed LinkedBag<String> Test");

        // Auto-Boxing makes this possible
        System.out.println("Starting LinkedBag<Integer> Test");
        testInt.startTest(new Integer[] {1, 2, 3, 100, 200, 300}, "integer");
        System.out.println("Completed LinkedBag<Integer> Test");
    }
    public class Node {
        private T data = null;
        private Node next = null;

        public Node(T data) { this(data, null); }
        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }

        public T getData() { return data; }
        public void setData(T data) { this.data = data; }

        public Node getNext() { return next; }
        public void setNext(Node next) { this.next = next; }
    }
}

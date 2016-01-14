/**
 * Created by hayesj3 on 1/14/2016.
 * @author Jacob Hayes
 */
public class LinkedBag<T> implements IBag<T> {

    private int size;
    private Node firstNode;

    public LinkedBag() { this(null); }
    public LinkedBag(Node firstNode) {
        this.firstNode = firstNode;
    }
    @Override
    public int getCurrentSize() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean add(T item) {
        Node newNode = new Node(item);
        newNode.nextNode = firstNode;
        firstNode = newNode;
        size++;
        return true;
    }

    @Override
    public boolean remove(T item) {
        return false;
    }

    @Override
    public T remove() {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean contains(T item) {
        return false;
    }

    @Override
    public int getFrequencyOf(T item) {
        return 0;
    }

    @Override
    public T[] toaArray() {
        T[] result = (T[]) new Object[size];
        Node currentNode;
        int count = 0;
        for (currentNode = firstNode; currentNode != null; currentNode = currentNode.getNextNode()) {
            result[count] = currentNode.getData();
            count++;
        }
        return result;
    }

    protected class Node {
        private T data;
        private Node nextNode;

        Node(T data) { this(data, null); }
        Node(T data, Node nextNode) {
            this.data = data;
            this.nextNode = nextNode;
        }

        public T getData() { return data; }
        public Node getNextNode() { return nextNode; }

        public void setData(T data) { this.data = data; }
        public void setNextNode(Node nextNode) { this.nextNode = nextNode; }
    }
}

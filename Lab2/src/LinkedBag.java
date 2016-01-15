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
        Node newItem = new Node(item, firstNode);
        firstNode = newItem;
        size++;
        return true;
    }

    @Override
    public boolean remove(T item) {
        Node previousNode = null;
        Node node = firstNode;
        do {
            if (node.getData().equals(item)) {
                if (node == firstNode) {
                    firstNode = firstNode.getNextNode();
                } else {
                    previousNode.setNextNode(node.nextNode);
                    node = null;
                }
                return true;
            }

            previousNode = node;
            node = node.nextNode;
        } while(node != null);
        return false;
    }

    @Override
    public T remove() {
        T item = firstNode.getData();
        firstNode = firstNode.nextNode;
        return item;
    }

    @Override
    public void clear() {
        Node node = firstNode;
        Node tempNode = firstNode.nextNode;
        for (int i = 0; i < size -1; i++) {
            node = null;
            node = tempNode;
            tempNode = node.nextNode;
        }
        firstNode = null;
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
        return ((T[]) new Object[0]);
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

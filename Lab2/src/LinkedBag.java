/**
 * Created by hayesj3 on 1/14/2016.
 * @author Jacob Hayes
 */
public class LinkedBag<T extends Object> implements IBag<T> {

    private int size = 0;
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
        firstNode = new Node(item, firstNode);
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
                    previousNode.setNextNode(node.getNextNode());
                    node = null;
                }
                return true;
            }

            previousNode = node;
            node = node.getNextNode();
        } while(node != null);
        return false;
    }

    @Override
    public T remove() {
        T item = (T) firstNode.getData();
        firstNode = firstNode.getNextNode();
        return item;
    }

    @Override
    public void clear() {
        Node node = firstNode;
        Node tempNode = firstNode.getNextNode();
        for (int i = 0; i < size -1; i++) {
            node = null;
            node = tempNode;
            tempNode = node.getNextNode();
        }
        firstNode = null;
    }

    @Override
    public boolean contains(T item) {
        boolean found = false;
        Node node = firstNode;
        for (int i = 0; i < size ; i++, node = node.getNextNode()) {
            if (node.getData().equals(item)) { found = true; }
        }
        return found;
    }

    @Override
    public int getFrequencyOf(T item) {
        int count = 0;
        Node node = firstNode;
        for (int i = 0; i < size ; i++, node = node.getNextNode()) {
            if (node.getData().equals(item)) { count++; }
        }
        return count;
    }

    @Override
    public T[] toaArray() {
        T[] array = (T[]) new Object[size];
        Node node = firstNode;
        for (int i = 0; i < size ; i++, node = node.getNextNode()) {
            array[i] = (T) node.getData();
        }
        return array;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(this == obj) return true;

        LinkedBag<T> other = null;
        if(obj instanceof LinkedBag) {
            other = (LinkedBag<T>) obj;
            if( other == null) { return false; }
        } else {
            return false;
        }
        Node thisNode = this.getFirstNode();
        Node otherNode = other.getFirstNode();

        T thisItem;
        T otherItem;

        for (int i = 0; i < this.getCurrentSize(); i++, thisNode = thisNode.getNextNode()) {
            thisItem = (T) thisNode.getData();
            if (!other.contains(thisItem)) {
                return false;
            } else if (this.getFrequencyOf(thisItem) != other.getFrequencyOf(thisItem)) {
                return false;
            }
        }
        for (int i = 0; i < other.getCurrentSize(); i++, otherNode = otherNode.getNextNode()) {
            otherItem = (T) otherNode.getData();
            if(!this.contains(otherItem)) {
                return false;
            } else if (this.getFrequencyOf(otherItem) != other.getFrequencyOf(otherItem)) {
                return false;
            }
        }

        return true;
    }
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        Node n;
        str.append("The bag contains: ");
        for (n = firstNode; n.getNextNode() != null; n = n.getNextNode()) {
            str.append(n.getData().toString() + ", ");
        }
        str.append(n.getData().toString());
        return str.toString();
    }

    public LinkedBag union(LinkedBag otherBag) {
        LinkedBag<T> combinedBag = new LinkedBag<>();
        for(Node n = this.firstNode; n != null; n=n.getNextNode()){
            combinedBag.add(n.getData());
        }
        for (Node n = otherBag.firstNode; n != null; n=n.getNextNode()) {
            combinedBag.add(n.getData());
        }
        return combinedBag;
    }

    public LinkedBag intersection(LinkedBag otherBag) {
        LinkedBag<T> combinedBag = new LinkedBag<>();

        Node n = this.getFirstNode();
        int count = 0;
        int pos = 0;
        for (int i = 0;n != null; i+=pos) {
            for (int j = 0; j < i; j++) {
                n=n.getNextNode();
            }
            count = Math.min(this.getFrequencyOf(n.getData()), otherBag.getFrequencyOf(n.getData()));

            for (int j = 0; j < count; j++, pos++) {
                combinedBag.add(n.getData());
            }
        }
        return combinedBag;
    }
    public Node getFirstNode() { return this.firstNode; }

    class Node {
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

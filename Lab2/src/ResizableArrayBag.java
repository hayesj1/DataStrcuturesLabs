import java.util.Arrays;

/**
 * Created by hayesj3 on 1/14/2016.
 * @author Jacob Hayes
 */
public class ResizableArrayBag<T> extends ArrayBag {

    private int capacity;

    public ResizableArrayBag() { this(DEFAULT_CAPACITY); }
    public ResizableArrayBag(int initialCapacity) {
        super(initialCapacity);
        this.capacity = initialCapacity;
    }

    /**
     * Doubles the bag's capacity
     */
    private void doubleCapacity() {
        capacity *= 2;
        theBag = Arrays.copyOf(theBag, capacity);
    }

    @Override
    public boolean add(Object item) {
        if (this.isFull()) { doubleCapacity(); }
        return super.add(item);
    }

    @Override
    protected boolean isFull() {
        return (getCurrentSize() >= capacity);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ResizableArrayBag) {
            ResizableArrayBag<T> other = (ResizableArrayBag<T>) obj;

            if (this.getCurrentSize() != other.getCurrentSize()) {
                return false;
            } else {
                return super.equals(obj);
            }
        }

        return false;
    }
}

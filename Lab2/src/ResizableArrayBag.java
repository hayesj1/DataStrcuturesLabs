import java.util.Arrays;

/**
 * Created by hayesj3 on 1/14/2016.
 * @author Jacob Hayes
 * @author Christian Abate-Wong
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
    public ResizableArrayBag union(ResizableArrayBag otherBag) {
        int combinedSize = this.getCurrentSize() + otherBag.getCurrentSize();
        ResizableArrayBag<T> combinedBag = new ResizableArrayBag<>(combinedSize);

        for(int i = 0; i < this.getCurrentSize(); i++){
            combinedBag.add(this.theBag[i]);
        }
        for (int i = 0; i < otherBag.getCurrentSize(); i++) {
            combinedBag.add(otherBag.theBag[i]);
        }
        return combinedBag;

    }

    public ResizableArrayBag intersection(ResizableArrayBag otherBag) {

        int combinedSize = this.getCurrentSize() + otherBag.getCurrentSize();
        ResizableArrayBag combinedBag = new ResizableArrayBag<>(combinedSize);

        int count = 0;
        int pos = 0;
        T item;

        for(int i = 0; i < this.getCurrentSize() && i < otherBag.getCurrentSize(); i+=pos) {
            item = (T) this.getTheBag()[pos];
            count = Math.min(this.getFrequencyOf(item), otherBag.getFrequencyOf(item));

            for (int j = 0; j < count; j++, pos++) {
                combinedBag.add(this.getTheBag()[i]);
            }

        }
        return combinedBag;
    }


}

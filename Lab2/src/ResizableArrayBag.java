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
    public ResizableArrayBag<T> union(ResizableArrayBag<T> firstBag, ResizableArrayBag<T> secondBag) {
        ResizableArrayBag<T> combinedBag = new ResizableArrayBag<>(firstBag.getCurrentSize() + secondBag.getCurrentSize());
        for(int i = 0; i < firstBag.getCurrentSize(); i++){
            combinedBag.add(firstBag.theBag[i]);
        }
        for (int i = 0; i < secondBag.getCurrentSize(); i++) {
            combinedBag.add(secondBag.theBag[i]);
        }
        return combinedBag;
    }

    public ResizableArrayBag<T> intersection(ResizableArrayBag<T> firstBag, ResizableArrayBag<T> secondBag) {
        ResizableArrayBag<T> combinedBag = new ResizableArrayBag<>(firstBag.getCurrentSize() + secondBag.getCurrentSize());
        for(int i = 0; i < firstBag.getCurrentSize(); i++) {
            if(firstBag.contains(secondBag.theBag[i])){
                combinedBag.add(secondBag.theBag[i]);
            }

        }
        return combinedBag;
    }


}

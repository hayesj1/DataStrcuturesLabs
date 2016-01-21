import java.util.Arrays;

/**
 * Created by hayesj3 on 1/14/2016.
 * @author Jacob Hayes
 * @author Christian Abate-Wong
 */
public class ResizableArrayBag<T extends Object> extends ArrayBag {

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

    public ResizableArrayBag<T> union(ResizableArrayBag<T> otherBag) {
        int combinedSize = this.getCurrentSize() + otherBag.getCurrentSize();
        ResizableArrayBag<T> combinedBag = new ResizableArrayBag<>(combinedSize);

        T[] firstArray = ( T[] ) this.getTheBag();
        T[] secondArray = ( T[] ) otherBag.getTheBag();

        for(int i = 0; i < firstArray.length; i++){
            combinedBag.add(firstArray[i]);
        }
        for (int i = 0; i < secondArray.length; i++) {
            combinedBag.add(secondArray[i]);
        }
        return combinedBag;
    }
    public ResizableArrayBag<T> intersection(ResizableArrayBag<T> otherBag) {
        int combinedSize = this.getCurrentSize() + otherBag.getCurrentSize();
        ResizableArrayBag<T> combinedBag = new ResizableArrayBag<>(combinedSize);
        T[] combinedArray = (T[]) new Object[combinedSize];

        T[] firstBagS = ( T[] ) this.getTheBag();
        Arrays.sort(firstBagS);

        T[] secondBagS = ( T[] ) otherBag.getTheBag();
        Arrays.sort(secondBagS);

        T item;
        int count = 0;
        int pos = 0;
        for (int i = 0; i < firstBagS.length && i < secondBagS.length; i += this.getFrequencyOf(item)) {
            item = firstBagS[i];
            count = Math.min(this.getFrequencyOf(item), otherBag.getFrequencyOf(item));

            for (int j = 0; j < count; j++, pos++) {
                combinedArray[pos] = item;
            }
        }
        combinedBag.setTheBag(combinedArray);
        return combinedBag;
    }

    /**
     * @deprecated found to be an incorrect implementation
     * @param firstBag
     * @param secondBag
     * @return a bag of the unionized paramaters
     */
    public ResizableArrayBag<T> union(ResizableArrayBag<T> firstBag, ResizableArrayBag<T> secondBag) {
        int combinedSize = firstBag.getCurrentSize() + secondBag.getCurrentSize();
        ResizableArrayBag<T> combinedBag = new ResizableArrayBag<>(combinedSize);

        T[] firstArray = ( T[] ) firstBag.getTheBag();
        T[] secondArray = ( T[] ) secondBag.getTheBag();

        for(int i = 0; i < firstArray.length; i++){
            combinedBag.add(firstArray[i]);
        }
        for (int i = 0; i < secondArray.length; i++) {
            combinedBag.add(secondArray[i]);
        }
        return combinedBag;
    }

    /**
     * @deprecated found to be an incorrect implementation
     * @param firstBag
     * @param secondBag
     * @return a bag of the intersected paramaters
     */
    public ResizableArrayBag<T> intersection(ResizableArrayBag<T> firstBag, ResizableArrayBag<T> secondBag) {
        int combinedSize = firstBag.getCurrentSize() + secondBag.getCurrentSize();
        ResizableArrayBag<T> combinedBag = new ResizableArrayBag<>(combinedSize);
        T[] combinedArray = (T[]) new Object[combinedSize];

        T[] firstBagS = ( T[] ) firstBag.getTheBag();
        Arrays.sort(firstBagS);

        T[] secondBagS = ( T[] ) secondBag.getTheBag();
        Arrays.sort(secondBagS);

        T item;
        int count = 0;
        int pos = 0;
        for (int i = 0; i < firstBagS.length && i < secondBagS.length; i += firstBag.getFrequencyOf(item)) {
            item = firstBagS[i];
            count = Math.min(firstBag.getFrequencyOf(item), secondBag.getFrequencyOf(item));

            for (int j = 0; j < count; j++, pos++) {
                combinedArray[pos] = item;
            }
        }
        combinedBag.setTheBag(combinedArray);
        return combinedBag;
    }


}

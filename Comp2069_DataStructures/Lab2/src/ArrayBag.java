import java.util.Arrays;

/**
 * Created by hayesj3 on 1/14/2016.
 * @author Jacob Hayes
 */
public class ArrayBag<T> implements IBag<T> {

    protected static final int DEFAULT_CAPACITY = 25;

    protected T[] theBag;
    private int size;

    public ArrayBag() { this(DEFAULT_CAPACITY); }
    public ArrayBag(int initialCapacity) {
        theBag = ((T[]) new Object[initialCapacity]);
        size = 0;
    }

    @Override
    public int getCurrentSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0 ? true : false);
    }

    @Override
    public boolean add(T item) {
        if (isFull()) { return false; }
        theBag[size++] = item;
        return true;
    }

    @Override
    public boolean remove(T item) {
        T lastItem = theBag[size-1];
        for(T obj : theBag) {
            if(obj == null) {
                break;
            } else if(obj.equals(item)) {
                obj = null;
                obj = lastItem;
                lastItem = null;
                size--;
                return true;
            } else {
                continue;
            }
        }
        return false;
    }

    @Override
    public T remove() {
        T removed = theBag[size-1];
        theBag[size-1] = null;
        return removed;
    }

    @Override
    public void clear() {
        Arrays.setAll(theBag, value -> null);
    }

    @Override
    public boolean contains(T item) {
        for (T obj : theBag) {
            if(obj == null) { break; }
            else if(obj.equals(item)) { return true; }
        }
        return false;
    }

    @Override
    public int getFrequencyOf(T item) {
        int count = 0;
        for(T obj : theBag) {
            if(obj == null) { break; }
            else if (obj.equals(item)) { count++; }
        }
        return count;
    }

    @Override
    public T[] toaArray() {
        return Arrays.copyOf(theBag, theBag.length);
    }

    protected boolean isFull() {
        return (size >= theBag.length);
    }
    public int getSize() { return size; }
    public T[] getTheBag() { return theBag; }
}

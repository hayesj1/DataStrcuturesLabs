/**
 * Created by hayesj3 on 1/14/2016.
 * @author Jacob Hayes
 */
public interface IBag<T> {
    /**
     * Gets the number of items in the bag
     * @return the size of the bag
     */
    int getCurrentSize();

    /**
     * Test to check if the bag is empty
     * @return true is the bag is empty; false otherwise
     */
    boolean isEmpty();

    /**
     * Adds <code>item</code> to the bag
     * @param item the item to add
     * @return true if the item was added; false otherwise
     */
    boolean add(T item);

    /**
     * Removes the first equal item <code>item</code> from the bag
     * @param item the item to remove
     * @return true if the item is removed; false otherwise
     */
    boolean remove(T item);

    /**
     * Removes the most recently added item from the bag
     * @return the removed item
     */
    T remove();

    /**
     * clears the bag by deleting all its items
     */
    void clear();

    /**
     * Check to see if <code>item</code> is in the bag
     * @param item the item to search for
     * @return true if the item was found; false otherwise
     */
    boolean contains(T item);

    /**
     * Counts the number of times <code>item</code> appears in the bag
     * @param item the item to search and count
     * @return the numbers of times <code>item</code> was found
     */
    int getFrequencyOf(T item);

    /**
     * Converts this bag into an array
     * @return An array with all the items in theb bag
     */
    T[] toaArray();
}

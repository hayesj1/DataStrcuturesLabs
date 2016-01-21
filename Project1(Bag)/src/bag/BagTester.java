package bag;

/**
 * Created by hayesj3 on 1/21/2016.
 */
public class BagTester<T> {

    public void startTest(T[] items) {
        LinkedBag<T> testBag = new LinkedBag<>(null);
        testBag.fromArray(items);

        System.out.println("Size: " + testBag.getCurrentSize());
        System.out.println("Contents: " + testBag);
    }
}

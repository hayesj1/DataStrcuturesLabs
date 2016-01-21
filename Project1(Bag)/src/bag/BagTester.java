package bag;

/**
 * Created by hayesj3 on 1/21/2016.
 */
public class BagTester<T> {

    public void startTest(T[] items) {
        LinkedBag<T> tempBag = new LinkedBag<>(null);
        LinkedBag<T> testBag1 = tempBag.fromArray(items);

        //for();
    }
}

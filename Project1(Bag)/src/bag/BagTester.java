package bag;

/**
 *
 * Group Members: Christian Abate-Wong, Karen Camaso, Jacob Hayes
 *
 *
 * Created by hayesj3 on 1/21/2016.
 * @author Jacob Hayes
 */
public class BagTester<T extends Object> {

    public void startTest(T[] items, String className) {
        switch(className) {
            case "string":
                testString((String[]) items);
                break;
            case "integer":
                testInteger((Integer[]) items);
                break;
            default:
                System.out.println("Error! No test for given bag content type!");
        }

    }

    private void testInteger(Integer[] items) {
        LinkedBag<Integer> theBag = new LinkedBag<>();
        theBag.fromArray(items);

        System.out.println("Size: " + theBag.getCurrentSize());
        System.out.println("Contents: " + theBag);

        theBag.add(Integer.valueOf(5));
        System.out.println("Added \"5\": " + theBag);

        Integer rem = theBag.remove();
        System.out.println("Removed \"" + rem + "\": " + theBag);

        theBag.remove(100);
        System.out.println("Removed \"100\": " + theBag);

        System.out.println("Current Contents: " + theBag);
        System.out.println("Empty?" + theBag.isEmpty());
        System.out.println("Clearing if not empty!");
        if(!theBag.isEmpty()) {
            theBag.clear();
            System.out.println("Cleared!");
        }
        System.out.println("Current Contents: " + theBag);
        System.out.println("Empty?" + theBag.isEmpty());
        System.out.println("Creating new Bag containing: " + "1 " + "20 " + "3 " +
                "100 " + "5 " + "20 ");
        LinkedBag<Integer> bag2 = new LinkedBag<>();
        bag2.fromArray(new Integer[] { 1, 20, 3, 100, 5, 20 });
        System.out.println("Current Contents(first bag): " + theBag);
        System.out.println("Current Contents(second bag): " + bag2);
        System.out.println("Equal?" + theBag.equals(bag2));
        System.out.println("Do the first and second bags contain \"100\": " + theBag.contains(100) + " || " +
                bag2.contains(100));
        System.out.println("Frequency of \"Bob\" in second bag: " + bag2.getFrequencyOf(20));
        System.out.println("Intersection of first and second bags: " + theBag.intersection(bag2));
        System.out.println("Union of first and second bags: " + theBag.union(bag2));
    }

    private void testString(String[] items) {
        LinkedBag<String> theBag = new LinkedBag<>();
        theBag.fromArray(items);

        System.out.println("Size: " + theBag.getCurrentSize());
        System.out.println("Contents: " + theBag);

        theBag.add("Duke");
        System.out.println("Added \"Duke\": " + theBag);

        String rem = theBag.remove();
        System.out.println("Removed \"" + rem + "\": " + theBag);

        theBag.remove("Pomegranate");
        System.out.println("Removed \"Pomegranate\": " + theBag);

        System.out.println("Current Contents: " + theBag);
        System.out.println("Empty?" + theBag.isEmpty());
        System.out.println("Clearing if not empty!");
        if(!theBag.isEmpty()) {
            theBag.clear();
            System.out.println("Cleared!");
        }
        System.out.println("Current Contents: " + theBag);
        System.out.println("Empty?" + theBag.isEmpty());
        System.out.println("Creating new Bag containing: " + "Bob " + "Jenna " + "Duke " +
                "Bob " + "Larry " + "Sara ");
        LinkedBag<String> bag2 = new LinkedBag<>();
        bag2.fromArray(new String[] { "Bob", "Jenna", "Duke", "Bob", "Larry", "Sara" });
        System.out.println("Current Contents(first bag): " + theBag);
        System.out.println("Current Contents(second bag): " + bag2);
        System.out.println("Equal?" + theBag.equals(bag2));
        System.out.println("Do the first and second bags contain \"Duke\": " + theBag.contains("Duke") + " || " +
            bag2.contains("Duke"));
        System.out.println("Frequency of \"Bob\" in second bag: " + bag2.getFrequencyOf("Bob"));
        System.out.println("Intersection of first and second bags: " + theBag.intersection(bag2));
        System.out.println("Union of first and second bags: " + theBag.union(bag2));
    }
}

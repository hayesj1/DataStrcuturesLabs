/**
 *
 * Group Members: Christian Abate-Wong, Karen Camaso, Jacob Hayes
 *
 *
 * Created by hayesj3 on 1/8/2016.
 * @author Christian Abate-Wong
 * @author Karen Camaso
 * @author Jacob Hayes
 */
public class Lab2 {

    public static void main(String[] args)
    {
        String[] arrayBagVal = { "Apples", "Oranges", "Bananas", "Milk"};
        String[] linkedBagVal = {"Kiwi", "Watermelon", "Apple", "Zebra"};
        ArrayBag arrayBag = new ArrayBag<String>(4);
        LinkedBag linkedBag = new LinkedBag<String>();

        // Setting arrayBag
        arrayBag.setTheBag(arrayBagVal);

        // Setting linkedBag
        for(int i = 0; i < linkedBagVal.length; i++)
        {
            linkedBag.add(linkedBagVal[i]);
        }

        //Printing out both arrays, testing toString()
        System.out.println(arrayBag.toString());
        System.out.println(linkedBag.toString());


        //Testing equals(), contains(), getFrequencyOf(), clear(), isEmpty(), and isFull()
        String tester = "Kiwi";
        String[] tester2 = new String[] {"Kiwi", "Apple", "Watermelon", "Zebra" };
        String[] tester3 = new String[] {"Bob", "Jerry", "Bob", "Tom" };
        ArrayBag<String> tester4 = new ArrayBag<>(4);
        LinkedBag<String> tester5 = new LinkedBag<>(null);
        tester4.setTheBag(tester3);
        // Setting tester5
        for(int i = 0; i < linkedBagVal.length; i++)
        {
            tester5.add(tester2[i]);
        }

        //equals()
        System.out.println("Equal: " + linkedBag.equals(tester5));
        System.out.println("Equal: " + arrayBag.equals(tester4));

        //contains()
        System.out.println("Contains \"Kiwi\": " + arrayBag.contains(tester));
        System.out.println("Contains \"Kiwi\": " + linkedBag.contains(tester));

        //getFrequencyOf()
        System.out.println("Frequency of " + tester + ": " + linkedBag.getFrequencyOf(tester));
        System.out.println("Frequency of " + tester3[0] + ": " + tester4.getFrequencyOf("Bob"));

        //clear(), isEmpty(), and isFull()
        System.out.println(tester4);
        tester4.clear();
        System.out.println("isEmpty: " + tester4.isEmpty());
        System.out.println("isFull()" + arrayBag.isFull());


        //Testing remove() and remove(item):
        //ArrayBag
        System.out.println("Removed: " + arrayBag.remove());
        arrayBag.remove("Milk");
        System.out.println("arrayBag = " + arrayBag);

        //LinkedBag
        System.out.println("Removed: " + linkedBag.remove());
        linkedBag.remove("Zebra");
        System.out.println("linkedBag = " + linkedBag);


        //Testing intersecton() and union()
        //Setting up bags
        ResizableArrayBag<String> firstBag = new ResizableArrayBag<>(5);
        ResizableArrayBag<String> secondBag = new ResizableArrayBag<>(5);

        firstBag.add("Hi");
        firstBag.add("Ni hao");
        firstBag.add("Konichiwa");
        firstBag.add("Aloha");
        firstBag.add("Olah");
        secondBag.add("Goodbye");
        secondBag.add("Hi");
        secondBag.add("Ni hao");
        secondBag.add("Shalom");
        secondBag.add("Aloha");

        //intersection()
        ResizableArrayBag<String> thirdBag = firstBag.intersection(secondBag);
        ResizableArrayBag<String> fourthBag = secondBag.intersection(firstBag);
        System.out.println("Intersections equal: " + thirdBag.equals(fourthBag));

        //union()
        thirdBag = firstBag.union(secondBag);
        fourthBag = secondBag.union(firstBag);
        System.out.println("Unions equal: " + thirdBag.equals(fourthBag));

    }
}

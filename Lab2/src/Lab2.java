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
        ArrayBag arrayBag = new ArrayBag<String>();
        LinkedBag linkedBag = new LinkedBag<String>();

        // Setting arrayBag
        arrayBag.setTheBag(arrayBagVal);

        // Setting linkedBag
        for(int i = 0; i < linkedBagVal.length; i++)
        {
            linkedBag.add(linkedBagVal[i]);
        }

        //Printing out both arrays
        System.out.println(arrayBag.toString());
        System.out.println(linkedBag.toString());

        //setting up stuff
        ResizableArrayBag<String> firstBag = new ResizableArrayBag<>(5);
        ResizableArrayBag<String> secondBag = new ResizableArrayBag<>(5);

        firstBag.add("Hi");
        firstBag.add("Ni hao");
        firstBag.add("Konichiwa");
        firstBag.add("I don't know how to say hello in any more languages");
        firstBag.add("Olah");
        secondBag.add("Goodbye");
        secondBag.add("Hi");
        secondBag.add("Ni hao");
        secondBag.add("Shalom");
        secondBag.add("Aloha");

        //testing
        ResizableArrayBag<String> thirdBag = new ResizableArrayBag<String>().intersection(firstBag, secondBag);
        ResizableArrayBag<String> fourthBag = new ResizableArrayBag<String >().union(firstBag, secondBag);

        System.out.println(thirdBag.toString());
        System.out.println(fourthBag.toString());

    }
}

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
        String[] linkedBagVal = {"Kiwi", "Watermellon", "Apple", "Zebra"};
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
        System.out.println(arrayBag.toaArray().toString());


    }
}

import java.util.ArrayList;
/*
 * Sorting algorithms and run-time analysis for the sorts
 * This class is separate from the project, but is prerequisite knowledge
 */
public class SortingLists
{
    public static void main(String args [])
    {
        ArrayList<Comparable> list = new ArrayList<Comparable>();
        for(int p=0; p<100; p++)
            list.add(p);
        randomizeOrder(list);
        System.out.println(list);
        System.out.println("Bubble Sort");
        bubbleSort(list);
        System.out.println(list);
        System.out.println();
        randomizeOrder(list);
        System.out.println(list);
        System.out.println("Selection Sort");
        selectionSort(list);
        System.out.println(list);
        System.out.println();
        randomizeOrder(list);
        System.out.println(list);
        System.out.println("Insertion Sort");
        insertionSort(list);
        System.out.println(list);
        System.out.println();
        randomizeOrder(list);
        System.out.println(list);
        System.out.println("Merge Sort");
        mergeSort(list);
        System.out.println(list);
        System.out.println();
        randomizeOrder(list);
        System.out.println(list);
        System.out.println("Quick Sort");
        quickSort(list);
        System.out.println(list);
    }

    public static void bubbleSort(ArrayList<Comparable> list)
    // O(n^2)
    {
        int index; // O(1)
        for(int p=0; p<list.size(); p++) // O(n)
        {
            index=p; // O(1)
            for(int x=p; x<list.size(); x++) // O(n)
            {
                if(list.get(x).compareTo(list.get(index)) < 0) // O(1)
                    index = x; // O(1)
            }
            list.set(p, list.set(index, list.get(p))); // O(1)
        }
        // So worst-case run-time is O(n^2)
    }

    public static void selectionSort(ArrayList<Comparable> list)
    // O(n^2)
    {
        int index = 0; // O(1)
        int endNum = list.size() - 1; // O(1)
        for(int p=0; p<list.size(); p++) // O(n)
        {
            index = 0; // O(1)
            for(int x=1; x<endNum + 1; x++) // O(n)
            {
                if(list.get(index).compareTo(list.get(x)) < 0) // O(1)
                    index = x; // O(1)
            }
            list.set(endNum, list.set(index, list.get(endNum))); // O(1)
            endNum--; // O(1)
        }
        // So worst-case run-time is O(n^2)
    }

    public static void insertionSort(ArrayList<Comparable> list)
    // O(n^2)
    {
        for(int partition=1; partition<list.size(); partition++) // O(n)
        {
            for(int x=0; x<partition; x++) // O(n)
            {
                if(list.get(partition - x - 1).compareTo(list.get(partition - x)) > 0) // O(1)
                    list.set(partition - x - 1, list.set(partition - x, list.get(partition - x - 1))); // O(1)
            }
        }
        // So worst-case run-time is O(n^2)
    }

    public static void mergeSort(ArrayList<Comparable> list)
    // O(nlog(n))
    {   
        ArrayList<Comparable> temp = mergeRecursion(list); // O(nlog(n))
        for(int p=0; p<list.size(); p++) // O(n)
            list.set(p, temp.get(p)); // O(1)
        // So worst-case run-time is O(nlog(n))
    }

    public static void quickSort(ArrayList<Comparable> list)
    // O(n^2)
    {
        ArrayList<Comparable> temp = quickRecursion(list); // O(nlog(n))
        for(int p=0; p<list.size(); p++) // O(n)
            list.set(p, temp.get(p)); // O(1)
        // So worst-case run-time is O(n^2)
    }

    public static void randomizeOrder(ArrayList<Comparable> list)
    // O(n)
    {
        for(int p=0; p<list.size(); p++) // O(n)
        {
            int randomN = (int)(Math.random() * list.size()); // O(1)
            list.set(p, list.set(randomN, list.get(p))); // O(1)
        }
        // So worst-case run-time is O(n)
    }

    /*
     * Helper Methods
     */
    private static ArrayList<Comparable> mergeRecursion(ArrayList<Comparable> list)
    {
        if(list.size() < 2) // O(1)
            return list; // O(1)
        int mid = list.size()/2; // O(1)
        ArrayList<Comparable> left = new ArrayList<Comparable>(); // O(1)
        for(int l=0; l<mid; l++) // O(n)
            left.add(list.get(l)); // O(1)

        ArrayList<Comparable> right = new ArrayList<Comparable>(); // O(1)
        for(int r=mid; r<list.size(); r++) // O(n)
            right.add(list.get(r)); // O(1)

        return merge(mergeRecursion(left), mergeRecursion(right)); // O(nlog(n))
        // So worst-case run-time is O(nlog(n))
    }

    private static ArrayList<Comparable> merge(ArrayList<Comparable> left, ArrayList<Comparable> right)
    {
        ArrayList<Comparable> out = new ArrayList<Comparable>(); // O(1)
        int i=0, j=0; // O(1)
        while(i < left.size() || j < right.size()) // O(n)
        {
            if((j == right.size()) || (i < left.size() && left.get(i).compareTo(right.get(j)) <= 0)) // O(1)
            {
                out.add(left.get(i)); // O(1)
                i++; // O(1)
            }
            else // O(1)
            {
                out.add(right.get(j)); // O(1)
                j++; // O(1)
            }
        }
        return out; // O(1)
        // So worst-case run-time is O(n)
    }

    private static ArrayList<Comparable> quickRecursion(ArrayList<Comparable> list)
    {
        if(list.size() < 2) // O(1)
            return list;
        Comparable pivot = list.get(0); // O(1)
        int left=1, right=list.size()-1; // O(1)
        //Sorting magic
        while(left<=right) // O(n)
        {
            if(list.get(left).compareTo(pivot) <= 0) // O(1)
                left++; // O(1)
            else if(list.get(right).compareTo(pivot) >= 0) // O(1)
                right--; // O(1)
            else
                list.set(left, list.set(right, list.get(left))); // O(1)
        }
        list.set(0, list.set(right, list.get(0))); // O(1)

        //Recursive Calls
        ArrayList<Comparable>leftList = new ArrayList<Comparable>(); // O(1)
        for(int l=0; l<right; l++) // O(n)
            leftList.add(list.get(l)); // O(1)
        leftList = quickRecursion(leftList); // O(n^2)

        ArrayList<Comparable>rightList = new ArrayList<Comparable>(); // O(1)
        for(int r=right+1; r<list.size(); r++) // O(n)
            rightList.add(list.get(r)); // O(1)
        rightList = quickRecursion(rightList); // O(n^2)

        leftList.add(list.get(right)); // O(1)
        for(int f=0; f<rightList.size(); f++) // O(n)
            leftList.add(rightList.get(f)); // O(1)
        return leftList; // O(1)
        // So worst-case run-time is O(n^2)
    }
}
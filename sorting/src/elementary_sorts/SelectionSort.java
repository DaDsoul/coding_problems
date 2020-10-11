package elementary_sorts;
import helper.SortingHelper;

/**
 * in iteration i, find index min of smallest remaining entry.
 * Swap a[i] and a[min]
 *
 * at each step, we discard previous minimums during each iterations
 *
 * To maintain algorithm invariants:
 *
 *  running time is O(N^2) compares and N exchanges
 *  insensitive to the data -> N^2 for already sorted input array
 *
 *
 *
 */

public class SelectionSort{

    public static void sort(Comparable[] a){
        int len = a.length;
        for (int i = 0; i<len; i++){
            int minIndex = i;
            //  j is bigger by one, since no reason to compare the element with itself
            for (int j = minIndex + 1; j < len; j++) {
                if (SortingHelper.less(a[j], a[minIndex])) {
                    minIndex = j;
                }
            }
            SortingHelper.exchange(a, i, minIndex);
        }
    }

}

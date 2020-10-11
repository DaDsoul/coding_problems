package elementary_sorts;


import helper.SortingHelper;

/**
 * In iteration i, swap a[i] with each larger entry to its left
 *
 *  best and worst case
 *
 *  Best case. If the array is in ascending order, insertion sort makes
 *  N - 1 compares and 0 exchanges
 *
 *
 *  Worst case. If the array in descending order (and no duplicates)
 *  insertion sort makes 1/2 N^2 compares and 1/2 N^2 exchanges
 *
 */


public class InsertionSort {

    public static void sort(Comparable[] a){
        int len = a.length;

        for (int i = 0; i < len; i++) {
            for(int j = i; j > 0; j--){
                if (SortingHelper.less(a[j], a[j-1])){
                    SortingHelper.exchange(a, j, j-1);
                }else{
                    break;
                }
            }
        }
    }
}

package elementary_sorts;


import helper.SortingHelper;

/**
 *
 * Move entries more than one position at a time by
 * h-sorting the array
 *
 * an h-sorted array is h interleaved sorted subsequences
 *
 * How to h-sort an array ?
 * Insertion sort, with stride length h
 *
 *
 * Worst case of number of comparisons used by shellsort
 * with 3x + 1 increment is O(N^(3/2))
 *
 */


public class Shellsort {

    public static void sort(Comparable[] a){
        int len = a.length;
        int h = 1;

        while(h < (len/3)){
            h = h*3 + 1;
        }

        while (h >= 1) {
            // insertion sort with stride of h
            for (int i = h; i < len; i++) {
                for (int j = i - 1; j > 0; j --) {
                    SortingHelper.exchange(a, j, j - h);
                }
            }
            // without minus 1 since we take the floor part of integer
            // during the division
            h = h / 3;
        }
    }
}

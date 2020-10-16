package classic_sorts;


import helper.SortingHelper;

import java.util.Random;

/**
 *  Basic Plan
 *
 *  Shuffle the array
 *  Partition so that, for some j
 *  -   entry a[j] is in place
 *  -   no larger entry to the left of j
 *  -   no smaller entry to the right of j
 *
 *  Sort each piece recursively
 *
 *  Phase 1. repeat until i and j pointers cross
 *  * Scan i from left to right so long as (a[i] < a[lo])
 *  * Scan j from right to left so long as (a[j] > a[lo])
 *  * Exchange a[i] with a[j]
 *
 *  Phase II. When pointers cross.
 *  * Exchange a[lo] with a[j].
 *
 *  Partitioning in place: Using an extra array makes partitioning
 *  easier (and stable), but is not worth the cost.
 *
 *
 * Best case - O(NlogN)
 * Worst case - O(1/2N^2)
 *
 *
 * Improvement:
 *
 *  Best choice of pivot item = median
 *  Estimate true median by taking median of sample
 *  Median-of-3 (random) items
 *
 *
 * QuickSort is in-place but not stable
 */

public class QuickSort {

    public static void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi){
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    // 3-way quicksort
    // helps to escape an issue with duplicate values, by putting them in the middle
    private static void sortByThreeWayPartition(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return ;
        int lt = lo, gt = hi;
        Comparable v = a[lo];
        int i = lo;
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0) {
                SortingHelper.exchange( a, lt++, i++);
            }else if (cmp > 0) {
                SortingHelper.exchange(a, i, gt--);
            }else{
                i ++;
            }
        }
        sortByThreeWayPartition(a, lo, lt - 1);
        sortByThreeWayPartition(a, gt + 1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi){
        int i = 0, j = hi + 1;
        while (true) {
            while (SortingHelper.less(a[++i], a[lo])){
                if (i == hi) {
                    break;
                }
            }

            while(SortingHelper.less(a[lo], a[--j])){
                if (j == lo) {
                    break;
                }
            }

            if (i >= j) {
                break;
            }

            SortingHelper.exchange(a, i, j);
        }

        SortingHelper.exchange(a, lo, j);
        return j;
    }


}

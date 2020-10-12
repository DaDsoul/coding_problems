package classic_sorts;

import helper.SortingHelper;

/**
 *
 *  Basic plan
 *  -   Divide array into two halves
 *  -   Recursively sort each half
 *  -   Merge two halves
 *
 *  Remember:
 *  - No need to create an auxiliary array during recursion calls since it will be too costly in
 *  terms of computation
 *
 *
 */


public class MergeSort {

    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi){
        if (hi <= lo){
            return;
        }

        int mid = (hi + lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi){

        for (int i = lo; i <= hi; i++) {
            aux[i] = a[i];
        }

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[i++];
            } else if (j > hi) {
                a[k] = aux[j++];
            } else if (SortingHelper.less(aux[j], aux[i])) {
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }
        }
    }

    public static void sort(Comparable[] a){
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    public static void sortBottomUp(Comparable[] a){
        int len = a.length;
        Comparable[] aux = new Comparable[a.length];
        for (int sz = 1; sz < len; sz = sz + sz){
            for (int lo = 0; lo < len - sz; lo += sz + sz){
                merge(a, aux, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, len - 1));
            }
        }
    }


}

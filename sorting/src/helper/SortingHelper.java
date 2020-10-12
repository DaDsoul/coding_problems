
package helper;

import java.util.Comparator;

public class SortingHelper{

    public static boolean less(Comparable v, Comparable w){

        return v.compareTo(w) < 0;
    }


    public static boolean less(Comparator c, Object v, Object w){
        return c.compare(v, w) < 0;
    }

    public static void exchange(Object[] a, int i, int j){
        Object temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }


    public static void exchange(Comparable[] a, int i, int j){
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = a[i];
    }
}
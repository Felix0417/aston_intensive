package org.aston_intensive.week1;

import java.util.Comparator;

/**
 * Implementation class of the Quicksort algorithm
 *
 * @author felix
 * @version 0.1
 * @since 2023-10-31
 */
public class MyQuickSorter {

    /**
     * @param array the array to be sorted
     * @param c     the comparator that can comparing the objects
     * @param low   the lowest element of array
     * @param high  the highest element of array
     * @param <E>   the comparator to determine the order of the array. A null value indicates that the elements' natural ordering should be used.
     * @throws ClassCastException             - if the array contains elements that are not mutually comparable using the specified comparator.
     * @throws IllegalArgumentException       - if fromIndex > toIndex or (optional) if the comparator is found to violate the Comparator contract
     * @throws ArrayIndexOutOfBoundsException - if fromIndex < 0 or toIndex > a.length
     */
    public static <E> void sort(Object[] array, Comparator<? super E> c, int low, int high) {
        quickSort(array, c, low, high);
    }

    private static <E> void quickSort(Object[] array, Comparator<? super E> c, int low, int high) {
        if (low < high) {
            int partitionIndex = partition(array, c, low, high);
            quickSort(array, c, low, partitionIndex - 1);
            quickSort(array, c, partitionIndex + 1, high);
        }
    }

    @SuppressWarnings({"unchecked"})
    private static <E> int partition(Object[] array, Comparator<? super E> c, int low, int high) {
        E pivot = (E) array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (c.compare((E) array[j], pivot) <= 0) {
                i++;
                swap(array, i, j);
            }
        }

        swap(array, i + 1, high);
        return i + 1;
    }

    private static void swap(Object[] array, int i, int j) {
        Object temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}

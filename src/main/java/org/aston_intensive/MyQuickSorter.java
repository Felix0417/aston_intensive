package org.aston_intensive;

import java.util.Comparator;

public class MyQuickSorter {

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

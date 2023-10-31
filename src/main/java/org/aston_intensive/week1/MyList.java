package org.aston_intensive.week1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

/**
 * This is some realisation of Array with a dynamic size, you can add variable types and objects of only one type
 *
 * @author felix
 * @version 0.1
 * @since 2023-10-31
 */
public class MyList<E> {

    private static final Object[] DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA = new Object[0];

    transient Object[] elementData;

    private int size;

    /**
     * Constructor with start capacity
     *
     * @param capacity generates an object with a certain capacity
     */
    public MyList(int capacity) {
        if (capacity > 0) {
            this.elementData = new Object[capacity];
        } else {
            if (capacity != 0) {
                throw new IllegalArgumentException("Illegal Capacity: " + capacity);
            }
            this.elementData = DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA;
        }
    }

    /**
     * Constructor with default capacity(0)
     */
    public MyList() {
        this.elementData = DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA;
    }

    /**
     * Add a new object to the list
     *
     * @param e the data you want to add
     * @return true if object added correctly
     * @throws ClassCastException if you will add another data type
     */
    public boolean add(E e) {
        if (size == this.elementData.length) {
            this.elementData = this.grow();
        }
        this.elementData[size] = e;
        this.size++;
        return true;
    }

    /**
     * Add a new object to the certain cell
     *
     * @param index   index of the list
     * @param element the data that is need to add
     * @throws IndexOutOfBoundsException if you add the object to another cell, more than list capability or
     *                                   if you add an object to a cell that has not yet been filled in
     * @throws ClassCastException        if you will add another data type
     */
    public void add(int index, E element) {
        checkIndex(index);
        int s;
        Object[] elementData;
        if ((s = this.size) == (elementData = this.elementData).length) {
            elementData = this.grow();
        }
        System.arraycopy(elementData, index, elementData, index + 1, s - index);
        elementData[index] = element;
        this.size++;
    }

    /**
     * Get the element from index
     *
     * @param index index of the list
     * @return object of the list
     * @throws IndexOutOfBoundsException if you will request the index, more than list capability
     */
    public E get(int index) {
        Objects.checkIndex(index, this.size);
        return this.elementData(index);
    }

    @SuppressWarnings({"unchecked"})
    private E elementData(int index) {
        return (E) this.elementData[index];
    }

    /**
     * Delete the element from index
     *
     * @param index index of your list that you want to delete
     * @return the element previously at the specified position
     * @throws UnsupportedOperationException if the remove operation is not supported by this list
     * @throws IndexOutOfBoundsException     if the index is out of range
     */
    @SuppressWarnings({"unchecked"})
    public E remove(int index) {
        Objects.checkIndex(index, this.size);
        Object[] es = this.elementData;
        E oldValue = (E) es[index];
        this.fastRemove(es, index);
        return oldValue;
    }

    private void fastRemove(Object[] es, int i) {
        int newSize;
        if ((newSize = this.size - 1) > i) {
            System.arraycopy(es, i + 1, es, i, newSize - i);
        }

        es[this.size = newSize] = null;
    }

    /**
     * Clear all of list
     */
    public void clear() {
        Object[] es = this.elementData;
        int to = this.size;

        for (int i = this.size = 0; i < to; ++i) {
            es[i] = null;
        }
    }

    /**
     * Set the value in the list in certain index, replacing older value
     *
     * @param index   index of the list
     * @param element the data that is need to add
     * @return the older value
     * @throws UnsupportedOperationException if the set operation is not supported by this list
     * @throws ClassCastException            if the class of the specified element prevents it from being added to this list
     * @throws NullPointerException          if the specified element is null and this list does not permit null elements
     * @throws IllegalArgumentException      if some property of the specified element prevents it from being added to this list
     * @throws IndexOutOfBoundsException     if the index is out of range
     */
    public E set(int index, E element) {
        Objects.checkIndex(index, this.size);
        E oldValue = this.elementData(index);
        this.elementData[index] = element;
        return oldValue;
    }

    /**
     * Give size of the list
     *
     * @return size of the list
     */
    public int size() {
        return this.size;
    }

    private Object[] grow() {
        return this.elementData = Arrays.copyOf(this.elementData, (this.size * 3) / 2 + 1);
    }

    private void checkIndex(int index) {
        if (index > this.size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
        }
    }

    /**
     * Sort the list by quicksort algorithm
     *
     * @param c the comparator that can comparing the objects
     * @throws ClassCastException            if the list contains elements that are not mutually comparable using the specified comparator
     * @throws UnsupportedOperationException if the list's list-iterator does not support the set operation
     * @throws IllegalArgumentException      (optional) if the comparator is found to violate the
     */
    public void sort(Comparator<? super E> c) {
        MyQuickSorter.sort(this.elementData, c, 0, size - 1);
    }
}

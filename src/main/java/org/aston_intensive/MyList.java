package org.aston_intensive;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

public class MyList<E> {

    private static final Object[] DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA = new Object[0];

    transient Object[] elementData;

    private int size;

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

    public MyList() {
        this.elementData = DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA;
    }

    public boolean add(E e) {
        if (size == this.elementData.length) {
            this.elementData = this.grow();
        }
        this.elementData[size] = e;
        this.size++;
        return true;
    }

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

    public E get(int index) {
        Objects.checkIndex(index, this.size);
        return this.elementData(index);
    }

    @SuppressWarnings({"unchecked"})
    private E elementData(int index) {
        return (E) this.elementData[index];
    }

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

    public void clear() {
        Object[] es = this.elementData;
        int to = this.size;

        for (int i = this.size = 0; i < to; ++i) {
            es[i] = null;
        }
    }

    public E set(int index, E element) {
        Objects.checkIndex(index, this.size);
        E oldValue = this.elementData(index);
        this.elementData[index] = element;
        return oldValue;
    }

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

    public void sort(Comparator<? super E> c) {
        MyQuickSorter.sort(this.elementData, c, 0, size - 1);
    }
}

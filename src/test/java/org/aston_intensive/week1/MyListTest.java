package org.aston_intensive.week1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyListTest {

    MyList<Integer> list = new MyList<>();

    @BeforeEach
    void createData() {
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
    }

    @Test
    void addNaturalOrder() {
        for (int i = 0; i < list.size(); i++) {
            assertEquals(i, list.get(i));
        }
    }

    @Test
    void addInPosition() {
        list.add(2, 4);
        list.add(5, 0);

        assertEquals(4, list.get(2));
        assertEquals(0, list.get(5));
    }

    @Test
    void addWrongPosition() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(20, 4));
    }

    @Test
    void get() {
        for (int i = 0; i < 10; i++) {
            assertEquals(i, list.get(i));
        }
    }

    @Test
    void remove() {
        list.remove(0);
        assertEquals(1, list.get(0));
    }

    @Test
    void clear() {
        list.clear();
        for (int i = 0; i < list.size(); i++) {
            assertNull(list.get(i));
        }
    }

    @Test
    void set() {
        list.set(5, 555);
        assertEquals(555, list.get(5));
    }

    @Test
    void setWrongCell() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(100, 555));
    }

    @Test
    void size() {
        assertEquals(10, list.size());
    }

    @Test
    void sort() {
        Integer[] integers = new Integer[]{0, 9, 18, 27, 36, 45, 54, 63, 72, 81, 90};
        list.clear();
        for (int i = 90; i >= 0; i -= 9) {
            list.add(i);
        }
        list.sort(Integer::compare);
        for (int i = 0; i < list.size(); i++) {
            assertEquals(integers[i], list.get(i));
        }
    }
}
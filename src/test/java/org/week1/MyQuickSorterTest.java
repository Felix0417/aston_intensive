package org.week1;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyQuickSorterTest {

    @Test
    void sortInteger() {
        Integer[] integers = new Integer[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        MyQuickSorter.sort(integers, Integer::compare, 0, integers.length - 1);

        for (int i = 0; i < 10; i++) {
            assertEquals(i, integers[i]);
        }
    }

    @Test
    void sortString() {
        String[] strings = new String[]{"j", "d", "a", "c", "f", "i", "b", "e", "h", "g"};
        MyQuickSorter.sort(strings, CharSequence::compare, 0, strings.length - 1);

        for (int i = 0; i < 10; i++) {
            assertEquals(String.valueOf((char) (97 + i)), strings[i]);
        }
    }

    @Test
    void sortObjects() {
        Animal[] animals = init();

        MyQuickSorter.sort(animals, Comparator.comparing(Animal::getAge), 0, animals.length - 1);
        List<Integer> ages = List.of(2, 5, 25, 30);
        for (int i = 0; i < animals.length; i++) {
            assertEquals(ages.get(i), animals[i].getAge());
        }


        MyQuickSorter.sort(animals, Comparator.comparing(Animal::getName), 0, animals.length - 1);
        List<String> types = List.of("Bird", "Cat", "Dog", "Elephant");
        for (int i = 0; i < animals.length; i++) {
            assertEquals(types.get(i), animals[i].getName());
        }
    }

    Animal[] init() {
        return new Animal[]{
                new Animal("Dog", 5),
                new Animal("Cat", 2),
                new Animal("Bird", 30),
                new Animal("Elephant", 25)};
    }

    @Nested
    class Animal {
        String name;
        int age;

        public Animal(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }
}
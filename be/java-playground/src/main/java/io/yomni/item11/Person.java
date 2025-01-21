package io.yomni.item11;

import java.util.Comparator;
import java.util.Objects;

public class Person implements Comparable<Person> {
    private final String name;
    private final int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public int compareTo(Person other) {
        return Comparator.comparing(Person::getName)
                .thenComparingInt(Person::getAge)
                .compare(this, other);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

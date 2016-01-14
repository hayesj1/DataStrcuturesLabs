package com.jacob_hayes;

/**
 * Created by hayesj3 on 1/7/2016.
 */
public class Person {

    private String name;

    public Person() { this("No name yet"); }
    public Person(String name) {
        this.name = name;
    }

    public void writeOutput() {
        System.out.println("Name: " + this.name);
    }
    public boolean hasSameName(Person other) {
        return (this.name.equalsIgnoreCase(other.getName()));
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}

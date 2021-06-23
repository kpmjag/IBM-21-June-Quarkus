package org.ibm.entity;

public class Developer {
    static long counter = 1;
    private long id;
    private String name;

    @Override
    public String toString() {
        return "Developer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }


    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void persist() {
        this.id = counter++;
    }
}
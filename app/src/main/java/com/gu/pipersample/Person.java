package com.gu.pipersample;

/**
 * TODO
 */
public class Person {
    private long id;
    private final String name;
    private final String job;

    public Person(String name, String job) {
        this.name = name;
        this.job = job;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

    @Override
    public String toString() {
        return name + " (" + job + ")";
    }
}

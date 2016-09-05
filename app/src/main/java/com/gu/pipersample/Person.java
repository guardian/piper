package com.gu.pipersample;

/**
 * TODO
 */
public class Person {

    private static final long NO_ID = -1;

    private long id = NO_ID;
    private final String name;
    private final String job;

    public Person(String name, String job) {
        this(NO_ID, name, job);
    }

    public Person(long id, String name, String job) {
        this.id = id;
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

    public final boolean hasId() {
        return id > 0;
    }
}

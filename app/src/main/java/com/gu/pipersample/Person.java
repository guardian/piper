package com.gu.pipersample;

import java.util.Date;

/**
 * TODO
 */
public class Person {

    private static final long NO_ID = -1;

    private long id = NO_ID;
    private final String name;
    private final String job;
    private final Date dob;

    public Person(String name, String job, Date dob) {
        this(NO_ID, name, job, dob);
    }

    public Person(long id, String name, String job, Date dob) {
        this.id = id;
        this.name = name;
        this.job = job;
        this.dob = dob;
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

    public Date getDob() {
        return dob;
    }

    @Override
    public String toString() {
        return Long.toString(id) + ": " + name + ", " + job + ", " + dob.toString();
    }

    public final boolean hasId() {
        return id > 0;
    }
}

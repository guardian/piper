package com.gu.pipersample;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * TODO
 */
public class Person implements Parcelable {

    private static final long NO_ID = -1;

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(
                    in.readLong(),
                    in.readString(),
                    in.readString(),
                    new Date(in.readLong())
            );
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

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
        return id != NO_ID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(name);
        parcel.writeString(job);
        parcel.writeLong(dob.getTime());
    }
}

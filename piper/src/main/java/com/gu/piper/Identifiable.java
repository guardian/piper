package com.gu.piper;

/**
 * Identifiable objects have an ID which clients can get and set.
 */
public interface Identifiable {
    long getId();
    void setId(long id);
}

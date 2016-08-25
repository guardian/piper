package com.gu.piper;

import android.support.annotation.NonNull;

/**
 * TODO
 */
public class Queries {

    @NonNull
    public static SelectQuery select() {
        return new SelectQuery();
    }

    @NonNull
    public static UpdateQuery update() {
        return new UpdateQuery();
    }

    @NonNull
    public static DeleteQuery delete() {
        return new DeleteQuery();
    }
}

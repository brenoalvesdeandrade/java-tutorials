package com.nklkarthi.couchbase.mapreduce;

@SuppressWarnings("serial")
public class DuplicateKeyException extends Exception {

    public DuplicateKeyException(String s) {
        super(s);
    }

}

package com.uillirt.projects.frameworks.rx.java;

import java.util.Iterator;
import java.util.function.Supplier;

/**
 * Created by kshekhovtsova on 27.07.2015.
 */
public class ElementIterator implements Iterator<String> {
    private static final int CNT = 16;
    private static int ITERATOR = 0;

    @Override
    public boolean hasNext() {
        //null
        return !(ITERATOR == CNT);
    }

    @Override
    public String next() {
        if (hasNext()) {
            ITERATOR++;
            return "element"+ITERATOR;
        } else {
            return null;
        }
    }
}

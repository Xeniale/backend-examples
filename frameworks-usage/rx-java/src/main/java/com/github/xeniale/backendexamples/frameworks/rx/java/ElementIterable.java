package com.github.xeniale.backendexamples.frameworks.rx.java;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * Created by kshekhovtsova on 28.07.2015.
 */
public class ElementIterable implements Iterable<String> {
    @Override
    public Iterator<String> iterator() {
        return new ElementIterator();
    }
}

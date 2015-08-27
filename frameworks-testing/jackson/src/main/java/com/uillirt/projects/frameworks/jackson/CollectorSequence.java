package com.uillirt.projects.frameworks.jackson;

/**
 * Created by kshekhovtsova on 11.08.2015.
 */
public class CollectorSequence {
    private int i;

    public CollectorSequence(int startWith) {
        i = startWith;
    }

    public int nextVal() {
        return i++;
    }
}
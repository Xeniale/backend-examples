package com.uillirt.projects.frameworks.jackson;

/**
 * Created by kshekhovtsova on 11.08.2015.
 */
public class SeqMain {
    public static void main(String[] args) {
//        CollectorSequence sequence = new CollectorSequence(1);
//        System.out.println(sequence.nextVal());
//        System.out.println(sequence.nextVal());
//        System.out.println(sequence.nextVal());
//        System.out.println(sequence.nextVal());
//


        ConfigType typeA = ConfigType.valueOf("Aggregator");
        System.out.println(typeA);
        ConfigType typeNon = ConfigType.valueOf("Ag");
        System.out.println(typeNon);
    }
}

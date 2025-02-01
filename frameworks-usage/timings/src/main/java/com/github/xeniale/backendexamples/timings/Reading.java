package com.github.xeniale.backendexamples.timings;

public class Reading {
  public final String descr;
  public final Long timeNanos;

  public Reading(String descr, Long timeNanos) {
    this.descr = descr;
    this.timeNanos = timeNanos;
  }
}

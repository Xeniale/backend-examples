package com.github.xeniale.backendexamples.timings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringWriter;
import java.time.Duration;
import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Timings {

  private final String context;
  private final Long thresholdMillis;
  private final Logger logger;

  public Timings(String context, Long thresholdMillis) {
    this.context = context;
    this.thresholdMillis = thresholdMillis;
    this.logger = LoggerFactory.getLogger(Timings.class);
  }

  public Timings(String context, Long thresholdMillis, Logger logger) {
    this.context = context;
    this.thresholdMillis = thresholdMillis;
    this.logger = logger;
  }

  private final Deque<Reading> readings = new ConcurrentLinkedDeque<>();

  public void takeReadings(String descr) {
    readings.add(new Reading(descr, System.nanoTime()));
  }

  public void report() {
    if (readings.isEmpty()) {
      return;
    }
    Reading firstReading = readings.peek();
    Long totalMillis;
    if (readings.size() > 1) {
      totalMillis = ((readings.stream().reduce((t, u) -> u)).get().timeNanos - firstReading.timeNanos) / 1000000;
    }
    else {
      totalMillis = -1L;
    }

    boolean debug = logger.isDebugEnabled();

    StringWriter writer = new StringWriter();

    boolean goneOver = totalMillis > thresholdMillis;
    if (goneOver) {
      writer.write("Timings for " + context +  "; taken="+ totalMillis + "ms, threshold="+ thresholdMillis +"ms");
    }
    else {
      if (debug) {
        logger.debug("Timings for " + context +  "; taken="+ totalMillis + "ms");
      }
      return;
    }

    Long prevTime = firstReading.timeNanos;
    for (Reading reading : readings) {
      writer.write("\n"+ ((reading.timeNanos - prevTime) / 1000000) +"ms   " + reading.descr);
      prevTime = reading.timeNanos;
    }

    logger.info(writer.toString());
  }

  public Duration getProcessingDuration() {
    return Duration.ofNanos(readings.getLast().timeNanos - readings.getFirst().timeNanos);
  }
}

package com.rflpazini.badabin;

import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

public class BenchMark {
  private static String hello = "not another hello world";

  public static void main(String[] argv) throws RunnerException {
    Options options =
        new OptionsBuilder()
            .include(Benchmark.class.getName())
            .mode(Mode.Throughput)
            .warmupTime(TimeValue.seconds(1))
            .warmupIterations(6)
            .threads(1)
            .measurementIterations(6)
            .forks(1)
            .shouldFailOnError(true)
            .shouldDoGC(true)
            .build();

    new Runner(options).run();
  }

  @Benchmark
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  public void countInterations() {
    final int ITERATIONS = Math.max(Integer.getInteger("iterations", 1), 1);

    String sentence = String.join(" ");

    for (int iter = 0; iter < ITERATIONS; iter++) {
      if (ITERATIONS != 1) {
        System.out.println("-- iteration " + (iter + 1) + " --");
      }

      long total = 0, start = System.currentTimeMillis(), last = start;

      for (int i = 1; i < 10_000_000; i++) {
        total += sentence.chars().filter(Character::isUpperCase).count();
        if (i % 1_000_000 == 0) {
          long now = System.currentTimeMillis();
          System.out.printf("%d (%d ms)%n", i / 1_000_000, now - last);
          last = now;
        }
      }

      System.out.printf("total: %d (%d ms)%n", total, System.currentTimeMillis() - start);
    }
  }
}

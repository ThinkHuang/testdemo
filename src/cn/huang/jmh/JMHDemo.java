package jmh;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * 由于该项目是非maven项目，导致无法启动，后面解决这个问题
 * @author Administrator
 *
 */
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@BenchmarkMode(Mode.Throughput)
public class JMHDemo
{
    private static AtomicLong count = new AtomicLong();
    private static LongAdder longAdder = new LongAdder();
    public static void main(String[] args) throws Exception {
        Options options = new OptionsBuilder().include(JMHDemo.class.getName()).forks(1).build();
        new Runner(options).run();
    }

    @Benchmark
    @Threads(10)
    public void run0(){
        count.getAndIncrement();
    }

    @Benchmark
    @Threads(10)
    public void run1(){
        longAdder.increment();
    }
}

package ru.hh.school;

import org.openjdk.jmh.annotations.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.TimeUnit;

public class Application {

    @State(Scope.Benchmark)
    public static class Context {

        private AnnotationConfigApplicationContext context;

        @Setup(Level.Trial)
        public void doSetup() {
            context = new AnnotationConfigApplicationContext();
            context.scan("ru.hh.school"); // recursive
            context.refresh();
        }

        public AnnotationConfigApplicationContext get() {
            return context;
        }
    }


    @Benchmark
    @Fork(value = 1, warmups = 10)
    @BenchmarkMode({Mode.SingleShotTime, Mode.Throughput})
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void startContext() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("ru.hh.school"); // recursive
        context.refresh();
    }

    @Benchmark
    @Fork(value = 1, warmups = 10)
    @BenchmarkMode({Mode.SingleShotTime, Mode.Throughput})
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void getBean(Context context) {
        context.get().getBean(Bean.class);
    }
}

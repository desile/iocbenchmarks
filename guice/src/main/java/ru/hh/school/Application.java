package ru.hh.school;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

public class Application {
//
//    public static void main(String[] args) throws InterruptedException {
//        // same as ApplicationContext in spring
//        Injector injector = Guice.createInjector();
//
//        // same as context.getBean in spring
//        Bean bean = injector.getInstance(Bean.class);
//        Bean bean2 = injector.getInstance(Bean.class);
//        System.out.println(bean);
//        System.out.println(bean2);
//    }

    @State(Scope.Benchmark)
    public static class Context {

        private Injector injector;

        @Setup(Level.Trial)
        public void doSetup() {
            injector = Guice.createInjector();
        }

        public Injector get() {
            return injector;
        }
    }


    @Benchmark
    @Fork(value = 1, warmups = 10)
    @BenchmarkMode({Mode.SingleShotTime, Mode.Throughput})
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void startContext() {
        Guice.createInjector();
    }

    @Benchmark
    @Fork(value = 1, warmups = 10)
    @BenchmarkMode({Mode.SingleShotTime, Mode.Throughput})
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void getBean(Context context) {
        context.get().getInstance(Bean.class);
    }

}

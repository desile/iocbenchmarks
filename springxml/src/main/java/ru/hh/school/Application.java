package ru.hh.school;

import org.openjdk.jmh.annotations.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.TimeUnit;

public class Application {

//    public static void main(String[] args) {
//        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
//
//        System.out.println(context.getBean(Bean.class));
//        System.out.println(context.getBean(Bean.class));
//    }


    @State(Scope.Benchmark)
    public static class Context {

        private ApplicationContext context;

        @Setup(Level.Trial)
        public void doSetup() {
            context = new ClassPathXmlApplicationContext("config.xml");
        }

        public ApplicationContext get() {
            return context;
        }
    }


    @Benchmark
    @Fork(value = 1, warmups = 10)
    @BenchmarkMode({Mode.SingleShotTime, Mode.Throughput})
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void startContext() {
        new ClassPathXmlApplicationContext("config.xml");
    }

    @Benchmark
    @Fork(value = 1, warmups = 10)
    @BenchmarkMode({Mode.SingleShotTime, Mode.Throughput})
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void getBean(Context context) {
        context.get().getBean(Bean.class);
    }


}

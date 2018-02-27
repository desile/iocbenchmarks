package ru.hh.school;

import org.openjdk.jmh.annotations.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

public class Application {

    @Configuration
    public static class Config{

        @org.springframework.context.annotation.Bean
        public Bean bean() {
            return new Bean();
        }

    }

//    public static void main(String[] args) {
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
//
//        Bean bean = context.getBean(Bean.class);
//        Bean bean2 = context.getBean(Bean.class);
//        System.out.println(bean);
//        System.out.println(bean2);
//    }

    @State(Scope.Benchmark)
    public static class Context {

        private AnnotationConfigApplicationContext context;

        @Setup(Level.Trial)
        public void doSetup() {
            context = new AnnotationConfigApplicationContext(Config.class);
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
        new AnnotationConfigApplicationContext(Config.class);
    }

    @Benchmark
    @Fork(value = 1, warmups = 10)
    @BenchmarkMode({Mode.SingleShotTime, Mode.Throughput})
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void getBean(Context context) {
        context.get().getBean(Bean.class);
    }

}

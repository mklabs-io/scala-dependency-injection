package io.mklabs.di;

public class Inheritance {

    private interface Demo1 {
        default String greet() {
            return "hello";
        }
    }

    private interface Demo2 {
        default String greet() {
            return "hallo";
        }
    }

    // interface can extend multiple interfaces, however on conflict,
    // need to override
    private interface Demo3 extends Demo1, Demo2 {
        @Override
        default String greet() {
            return Demo1.super.greet();
        }
    }

    // .. and a class implement multiple interfaces;
    // however a class can only extend a single class

    private static class DemoImplememented implements Demo3 {}

    public static void main(String[] args) {

        final DemoImplememented demo = new DemoImplememented();
        System.out.println(demo.greet());

    }
}

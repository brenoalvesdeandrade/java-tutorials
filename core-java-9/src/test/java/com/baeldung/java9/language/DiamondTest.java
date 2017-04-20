package com.nklkarthi.java9.language;

import org.junit.Test;

public class DiamondTest {

    static class FooClass<X> {
        FooClass(X x) {
        }

        <Z> FooClass(X x, Z z) {
        }
    }

    @Test
    public void diamondTest() {
        FooClass<Integer> fc = new FooClass<>(1) {
        };
        FooClass<? extends Integer> fc0 = new FooClass<>(1) {
        };
        FooClass<?> fc1 = new FooClass<>(1) {
        };
        FooClass<? super Integer> fc2 = new FooClass<>(1) {
        };

        FooClass<Integer> fc3 = new FooClass<>(1, "") {
        };
        FooClass<? extends Integer> fc4 = new FooClass<>(1, "") {
        };
        FooClass<?> fc5 = new FooClass<>(1, "") {
        };
        FooClass<? super Integer> fc6 = new FooClass<>(1, "") {
        };

    }
}

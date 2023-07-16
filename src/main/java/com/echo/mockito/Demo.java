package com.echo.mockito;

public class Demo {
    public int add(int a, int b) {
        return a + b;
    }

    public int addMult(int a, int b) {
        return add(add(a, b), b);
    }
}
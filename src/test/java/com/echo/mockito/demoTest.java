package com.echo.mockito;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class demoTest {
    @Spy
    private Demo demo;
    @InjectMocks
    private UseDemo useDemo;

    @BeforeEach
    void serUp() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void testMock() {
        Assertions.assertEquals(3,demo.add(1,2));

        Mockito.when(demo.add(1,2)).thenReturn(0);
        Assertions.assertEquals(0,demo.add(1,2));
    }

    @Test
    void testMock2() {
        Mockito.when(demo.add(Mockito.anyInt(),Mockito.anyInt())).thenReturn(5);
        System.out.println(useDemo.useAdd(10, 10));

    }
}
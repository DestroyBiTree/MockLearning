# Mockito

## 怎么样去mock一个类？

1.使用mock方法

```Java
Random random = Mockito.mock(Random.class);
```

2.使用@Mock注解

```
@Mock
Random random;

@Test
void add() {
    MockitoAnnotations.openMocks(this);
}
```

@Mock注解要搭配MockitoAnnotations.openMocks()使用，不然不会生效。

this指的是生效范围为当前的测试类。



## 给Mock对象打桩

如果直接执行下面代码，返回值只会是0（默认值）；

```java
System.out.println(random.nextInt());
```

需要进行打桩：

```java
Mockito.when(random.nextInt()).thenReturn(3);
```



## 断言和验证

对于没有分支逻辑的功能，可以对结果断言，测试代码逻辑是否正确。

```Java
Assertions.assertEquals(4,random.nextInt());
```

验证方法被执行过的次数：

```java
Mockito.verify(random).nextInt(); //是否被调用过
Mockito.verify(random, Mockito.times(1)).nextInt();//是否被调用了一次
```



## @BeforeEach和@AfterEach

@BeforeEach在单元测试开始之前执行的方法，通常命名为setUp，通常会写MockitoAnnotations.openMocks(this);

@AfterEach在单元测试结束之后执行的方法，通常命名为after



## @Spy和spy方法

spy方法和mock不同的是：

**spy会走真实的方法，mock不会。**

spy方法参数是对象实例，mock的参数是class

下列代码用的是@Mock注解，在未进行打桩的时候，返回结果是0，断言成功；打桩结束返回结果是3. 

```Java
class demoTest {
    @Mock
    private Demo demo;

    @BeforeEach
    void serUp() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void testMock() {
        Assertions.assertEquals(0,demo.add(1,2));

        Mockito.when(demo.add(1,2)).thenReturn(3);
        Assertions.assertEquals(3,demo.add(1,2));
    }
}
```

而改成@Spy注解，在未进行打桩的时候，走的真实的方法

```Java
class demoTest {
    @Spy
    private Demo demo;

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
}
```

## @RunWith(MockitoJUnitRunner.class)注解有什么用

`@RunWith(MockitoJUnitRunner.class)`是JUnit Mockito库中的一个注解，它的作用是在运行测试时使用Mockito测试运行器，以便更方便地初始化模拟对象和注入依赖项。

通常，当您在测试类中使用`@Mock`和`@InjectMocks`注解时，为了让Mockito工作，需要在`@Before`方法中调用`MockitoAnnotations.initMocks(this)`来初始化模拟对象和依赖项的注入。

使用`@RunWith(MockitoJUnitRunner.class)`注解，可以简化这个过程。它告诉JUnit在运行测试时使用Mockito测试运行器，自动处理模拟对象的初始化和依赖项的注入。
###Lambda

在Java 8里面，**所有的Lambda的类型都是一个接口，而Lambda表达式本身，也就是”那段代码“，需要是这个接口的实现。**这是我认为理解Lambda的一个关键所在，简而言之就是，**Lambda表达式本身就是一个接口的实现**。

Lambda表达式可以看成是匿名类一点点**演变过来**

与匿名类概念相比较，Lambda 其实就是匿名方法，这是一种把方法作为参数进行传递的编程思想。

虽然代码是这么写 

filter(mans, m -> m.old > 50 && m.money > 500);

但是，Java会在背后把这些都还原成匿名类的方式.

引入Lambda表达式，会使得代码更加紧凑，而不是各种接口和匿名类遍布项目。

Lambda表达式虽然带来了代码的简洁，但是也有其局限性。

- 可读性差，与**啰嗦的**但是**清晰的**匿名类代码结构比较起来，Lambda表达式一旦变得比较长，就难以理解
-  不便于调试，很难在Lambda表达式中增加调试信息，比如日志
-  版本支持，Lambda表达式在JDK8版本中才开始支持，如果系统使用的是以前的版本，考虑系统的稳定性等原因，而不愿意升级，那么就无法使用。

Lambda比较适合用在简短的业务代码中，并不适合用在复杂的系统中，会加大维护成本。



在练习代码中假设了以下场景：

如果需要找出满足条件的Man，使用**普通方法**，**匿名类**，以及**Lambda**这几种方式分别进行测试 。

---

###Stream

什么是 Stream？

Stream 使用一种类似用 SQL 语句从数据库查询数据的直观方式来提供一种对 Java 集合运算和表达的高阶抽象。这种风格将要处理的元素集合看作一种流， 流在管道中传输， 并且可以在管道的节点上进行处理， 比如筛选， 排序，聚合等。元素流在管道中经过中间操作（intermediate operation）的处理，最后由最终操作(terminal operation)得到前面处理的结果。

Stream（流）是一个来自数据源的元素队列并支持聚合操作

- 元素是特定类型的对象，形成一个队列。 Java中的Stream并不会存储元素，而是按需计算。
- **数据源** 流的来源。 可以是集合，数组，I/O channel， 产生器generator 等。
- **聚合操作** 类似SQL语句一样的操作， 比如filter, map, reduce, find, match, sorted等。

和以前的Collection操作不同， Stream操作还有两个基础的特征：

- **Pipelining**: 中间操作都会返回流对象本身。 这样多个操作可以串联成一个管道， 如同流式风格（fluent style）。 这样做可以对操作进行优化， 比如延迟执行(laziness)和短路( short-circuiting)。
- **内部迭代**： 以前对集合遍历都是通过Iterator或者For-Each的方式, 显式的在集合外部进行迭代， 这叫做外部迭代。 Stream提供了内部迭代的方式， 通过访问者模式(Visitor)实现。

```
+--------------------+       +------+   +------+   +---+   +-------+
| stream of elements +-----> |filter+-> |sorted+-> |map+-> |collect|
+--------------------+       +------+   +------+   +---+   +-------+
```

以上的流程转换为 Java 代码为：

```java
List<Integer> transactionsIds = 
widgets.stream()
             .filter(b -> b.getColor() == RED)
             .sorted((x,y) -> x.getWeight() - y.getWeight())
             .mapToInt(Widget::getWeight)
             .sum();
```

####生成流

在 Java 8 中, 集合接口有两个方法来生成流：

- **stream()** − 为集合创建串行流。
- **parallelStream()** − 为集合创建并行流。

```java
List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl"); List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
```

####中间操作

每个中间操作，又会返回一个Stream，比如.filter()又返回一个Stream, 中间操作是“懒”操作，并不会真正进行遍历。中间操作比较多，主要分两类 对元素进行筛选 和 转换为其他形式的流。

- 对元素进行筛选：
  filter 匹配
  distinct 去除重复(根据equals判断)
  sorted 自然排序
  sorted(Comparator<T>) 指定排序
  limit 保留
  skip 忽略

- 转换为其他形式的流
  mapToDouble 转换为double的流
  map 转换为任意类型的流

  

以下是常用中间操作示例：

#####forEach

Stream 提供了新的方法 'forEach' 来迭代流中的每个数据。以下代码片段使用 forEach 输出了10个随机数：

```java
Random random = new Random(); random.ints().limit(10).forEach(System.out::println);
```

#####map

map 方法用于映射每个元素到对应的结果，以下代码片段使用 map 输出了元素对应的平方数：

```java
List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5); // 获取对应的平方数 List<Integer> squaresList = numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList());
```

#####filter

filter 方法用于通过设置的条件过滤出元素。以下代码片段使用 filter 方法过滤出空字符串：

```java
List<String>strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl"); // 获取空字符串的数量 int count = strings.stream().filter(string -> string.isEmpty()).count();
```

#####limit

limit 方法用于获取指定数量的流。 以下代码片段使用 limit 方法打印出 10 条数据：

```java
Random random = new Random(); random.ints().limit(10).forEach(System.out::println);
```

#####sorted

sorted 方法用于对流进行排序。以下代码片段使用 sorted 方法对输出的 10 个随机数进行排序：

```java
Random random = new Random(); random.ints().limit(10).sorted().forEach(System.out::println);
```

#####并行（parallel）程序

parallelStream 是流并行处理程序的代替方法。以下实例我们使用 parallelStream 来输出空字符串的数量：

```java
List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl"); // 获取空字符串的数量 int count = strings.parallelStream().filter(string -> string.isEmpty()).count();
```

我们可以很容易的在顺序运行和并行直接切换。

#####Collectors

Collectors 类实现了很多归约操作，例如将流转换成集合和聚合元素。Collectors 可用于返回列表或字符串：

```java
List<String>strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl"); List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());   System.out.println("筛选列表: " + filtered); String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", ")); System.out.println("合并字符串: " + mergedString);
```

#####统计

另外，一些产生统计结果的收集器也非常有用。它们主要用于int、double、long等基本类型上，它们可以用来产生类似如下的统计结果。

```java
List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);   IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();   System.out.println("列表中最大的数 : " + stats.getMax()); System.out.println("列表中最小的数 : " + stats.getMin()); System.out.println("所有数之和 : " + stats.getSum()); System.out.println("平均数 : " + stats.getAverage());
```



####结束操作

当进行结束操作后，流就被使用“光”了，无法再被操作。所以这必定是流的最后一个操作。 结束操作不会返回Stream，但是会返回int、float、String、 Collection或者像forEach，什么都不返回。结束操作才真正进行遍历行为，前面的中间操作也在这个时候，才真正的执行。
常见结束操作如下：
**forEach()** 遍历每个元素
**toArray()** 转换为数组
**min(Comparator<T>)** 取最小的元素
**max(Comparator<T>)** 取最大的元素
**count()** 总数
**findFirst()** 第一个元素

---

####Java8 日期

新版API中java.time包里的一些关键类：

- Instant：瞬时实例。
- LocalDate：本地日期，不包含具体时间 例如：2014-01-14 可以用来记录生日、纪念日、加盟日等。
- LocalTime：本地时间，不包含日期。
- LocalDateTime：组合了日期和时间，但不包含时差和时区信息。
- ZonedDateTime：最完整的日期时间，包含时区和相对UTC或格林威治的时差。

具体示例在代码中
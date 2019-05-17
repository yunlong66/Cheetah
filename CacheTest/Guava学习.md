## Guava 是什么?

Guava是一种基于开源的Java库，其中包含谷歌正在由他们很多项目使用的很多核心库。这个库是为了方便编码，并减少编码错误。这个库提供用于集合，缓存，支持原语，并发性，常见注解，字符串处理，I/O和验证的实用方法。

####Guava的好处：

- 标准化 - Guava库是由谷歌托管。
- 高效 - 可靠，快速和有效的扩展JAVA标准库
- 优化 -Guava库经过高度的优化

#### 源码包的简单说明：

com.google.common.annotations：普通注解类型。

com.google.common.base：基本工具类库和接口。
com.google.common.cache：缓存工具包，非常简单易用且功能强大的JVM内缓存。
com.google.common.collect：带泛型的集合接口扩展和实现，以及工具类，这里你会发现很多好玩的集合。
com.google.common.eventbus：发布订阅风格的事件总线。
com.google.common.hash： 哈希工具包。
com.google.common.io：I/O工具包。
com.google.common.math：原始算术类型和超大数的运算工具包。
com.google.common.net：网络工具包。
com.google.common.primitives：八种原始类型和无符号类型的静态工具包。
com.google.common.reflect：反射工具包。
com.google.common.util.concurrent：多线程工具包。

####Guava本地缓存：

缓存在很多系统和架构中都用广泛的应用：

- CPU缓存
- 操作系统缓存
- 本地缓存
- 分布式缓存
- HTTP缓存
- 数据库缓存

Guava Cache是一个全内存的本地缓存实现，它提供了线程安全的实现机制。整体上来说Guava cache 是本地缓存的不二之选，简单易用，性能好。

####适用场景：

- 愿意消耗一些内存空间来提升速度。
- 预料到某些键会被查询一次以上。
- 缓存中存放的数据总量不会超出内存容量。(Guava Cache 是单个应用运行时的本地缓存。它不把数据存放到文件或外部服务器。)



guava缓存过期时间分为两种，一种是从写入时开始计时，一种是从最后访问时间开始计时，而且guava缓存的过期时间是设置到整个一组缓存上的；这和EHCache，redis，memcached等不同，这些缓存系统设置都将缓存时间设置到了单个缓存上。

guava缓存设计成了一组对象一个缓存实例，这样做的好处是一组对象设置一组缓存策略，你可以根据不同的业务来设置不同的缓存策略，包括弱引用，软引用，过期时间，最大项数等。另外一点好处是你可以根据不同的组来统计缓存的命中率，这样更有意义一些。

这样做也是有缺点的，缺点是首先是每个缓存组都需要声明不同的缓存实例，具体到业务程序中可能就是每个业务对象一个缓存了。这样就把不同的业务缓存分散到不同的业务系统中了，不太好管理。
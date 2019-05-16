###Redis

####什么是Redis

Redis是一个开源的使用ANSIC语言编写、支持网络、可基于内存亦可持久化的日志型、Key-Value数据库，并提供多种语言的API。换句话说，Redis就像是一个HashMap，不过不是在JVM中运行，而是以一个独立进程的形式运行。一般说来，会被当作缓存使用。 因为它比数据库(mysql)快，所以常用的数据，可以考虑放在这里，这样就提高了性能。

####5种数据类型

Redis目前有5种数据类型，分别是：

- String（字符串）
- List（列表）
- Hash（字典）
- Set（集合）
- Sorted Set（有序集合）

不同的数据类型，有不同的命令方式：

以String字符串为例，其他的可查阅官方命令手册

<http://www.redis.cn/commands.html>

```
SET key value                   设置key=value

GET key                         获得键key对应的值

GETRANGE key start end          得到字符串的子字符串存放在一个键

GETSET key value                设置键的字符串值，并返回旧值

GETBIT key offset               返回存储在键位值的字符串值的偏移

MGET key1 [key2..]              得到所有的给定键的值

SETBIT key offset value         设置或清除该位在存储在键的字符串值偏移

SETEX key seconds value         键到期时设置值

SETNX key value                 设置键的值，只有当该键不存在

SETRANGE key offset value       覆盖字符串的一部分从指定键的偏移

STRLEN key                      得到存储在键的值的长度

MSET key value [key value...]   设置多个键和多个值

MSETNX key value [key value...] 设置多个键多个值，只有在当没有按键的存在时

PSETEX key milliseconds value   设置键的毫秒值和到期时间

INCR key                        增加键的整数值一次

INCRBY key increment            由给定的数量递增键的整数值

INCRBYFLOAT key increment       由给定的数量递增键的浮点值

DECR key                        递减键一次的整数值

DECRBY key decrement            由给定数目递减键的整数值

APPEND key value                追加值到一个键

DEL key                         如果存在删除键

DUMP key                        返回存储在指定键的值的序列化版本

EXISTS key                      此命令检查该键是否存在

EXPIRE key seconds              指定键的过期时间

EXPIREAT key timestamp          指定的键过期时间。在这里，时间是在Unix时间戳格式

PEXPIRE key milliseconds        设置键以毫秒为单位到期

PEXPIREAT key milliseconds-timestamp        设置键在Unix时间戳指定为毫秒到期

KEYS pattern                    查找与指定模式匹配的所有键

MOVE key db                     移动键到另一个数据库

PERSIST key                     移除过期的键

PTTL key                        以毫秒为单位获取剩余时间的到期键。

TTL key                         获取键到期的剩余时间。

RANDOMKEY                       从Redis返回随机键

RENAME key newkey               更改键的名称

RENAMENX key newkey             重命名键，如果新的键不存在

TYPE key                        返回存储在键的数据类型的值。
```

---

###Jedis

在常见命令中，使用各种Redis自带客户端的命令行方式访问Redis服务。 而在实际工作中需要用到Java代码才能访问，使用第三方jar包 ：Jedis就能方便地访问Redis的各种服务了。
### LRU缓存机制

题目描述：

>运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
>
>获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
>写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。
>
>进阶:
>
>你是否可以在 O(1) 时间复杂度内完成这两种操作？
>
>示例:
>
>LRUCache cache = new LRUCache( 2 /* 缓存容量 */ );
>
>cache.put(1, 1);
>cache.put(2, 2);
>cache.get(1);       // 返回  1
>cache.put(3, 3);    // 该操作会使得密钥 2 作废
>cache.get(2);       // 返回 -1 (未找到)
>cache.put(4, 4);    // 该操作会使得密钥 1 作废
>cache.get(1);       // 返回 -1 (未找到)
>cache.get(3);       // 返回  3
>cache.get(4);       // 返回  4

思路（别人分享的，理解中）：

首先我们应该了解LRU算法的原理。LRU算法总结来说就是：一个数据很久没有访问，那么这个数据在以后也很少访问。对于这样的很久没有访问的数据，如果缓存空间不足，就把那些很久没有使用的数据清除。

那怎么样实现这样一种算法呢？我们可以为每一个数据标记一个时间戳，标记了最近一次使用的时间，然后不断更新时间戳，缓存没用空间的时候删除距离现在最久的时间戳。这样的方法可以用数组，链表来实现，时间复杂度一般是O(n)。

或者可以不用表示时间戳，我们仅根据数据的使用时间对数据进行排序，最近使用的排在前面，很久没有使用的排在后面。删除数据从后面开始删除。在查找数据的时候可以使用HashMap。这样，时间复杂度就可以简化成O(1)。

说了这么多，我们把细节总结一下，说一下算法的具体实现。

> 1.首先我们应该维护一个双向链表，在这个链表中，我们应该使链表中的数据这样排列：越近使用的数据越排在前面，越久没有使用的数据排在后面。此外，链表中的所有数据缓存在一个HashMap中。
>
> ##### 2.我们插入一个数据的时候，首先查找HashMap中是否有这个数据？
>
> ###### 2.1 如果没有这个数据，我们要判断现在HashMap中是否还有剩余空间？如果没有剩余空间，需要将双向链表中最后一个数据删除（在HashMap中也要删除这个数据，注意删除的时候空指针等异常情况）。然后将要插入的数据放到双向链表的头部。
>
> ###### 2.2 如果有这个数据，我们首先应该将这个数据与它的前驱和后驱断开（注意可能造成空指针异常的情况），然后将这个数据插入到头结点。
>
> ##### 3. 当我们需要获取一个数据的时候，查找HashMap中是否有这个数据？
>
> ###### 3.1 如果有这个数据， 我们首先应该将这个数据与它的前驱和后驱断开（注意可能造成空指针异常的情况），然后将这个数据插入到头结点。
>
> ###### 3.2 如果没有这个数据，返回-1。

```java
class LRUCache {

    private int capacity;
    private LinkNode first;
    private LinkNode last;
    private HashMap<Integer, LinkNode> cache; 
    public LRUCache(int capacity) {
        this.capacity = capacity;
        //new hashmap的时候，可以指定容量吗？
        cache = new HashMap<Integer, LinkNode>(capacity);
    }
    
    public int get(int key) {
        LinkNode res=cache.get(key);
        if(res==null){
            return -1;
        }
        moveNodeToFirst(res);
        return res.val;
    }
    
    public void put(int key, int value) {
        LinkNode temp = cache.get(key);
        if(temp == null){
            //如果容量满了？不理解
            if(cache.size() >= capacity){
                removeLastNode();
            }
            temp = new LinkNode();
            temp.key = key;
        }
        //这里要注意，val可能会更新
        temp.val = value;
        moveNodeToFirst(temp);
        cache.put(key, temp);
    }
    
    private void removeLastNode(){
        LinkNode temp = last;
        last = last.pre;
        if(last != null){
            last.next = null;
        }else{
            first = last = null;
        }
        cache.remove(temp.key);
    }
    
    private void moveNodeToFirst(LinkNode node){
        if(first==node) return;
        if(node.pre!=null){
            node.pre.next=node.next;
        }
        if(node.next!=null){
            node.next.pre=node.pre;
        }
        if(node==last){
            last=last.pre;
        }
        if(last==null){
            last=first=node;
            return;
        }
        node.next=first;
        first.pre=node;
        node.pre=null;
        first=node;
    }
}

class LinkNode{
    LinkNode pre;
    LinkNode next;
    int val;
    int key; 
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
```




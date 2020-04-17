package clockIn;

import java.util.HashMap;
import java.util.Map;

/**
 * 460. LFU缓存
 * 请你为 最不经常使用（LFU）缓存算法设计并实现数据结构。它应该支持以下操作：get 和 put。
 * <p>
 * get(key) - 如果键存在于缓存中，则获取键的值（总是正数），否则返回 -1。
 * put(key, value) - 如果键不存在，请设置或插入值。当缓存达到其容量时，则应该在插入新项之前，使最不经常使用的项无效。
 * 在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，应该去除 最近 最少使用的键。
 * 「项的使用次数」就是自插入该项以来对其调用 get 和 put 函数的次数之和。使用次数会在对应项被移除后置为 0 。
 * <p>
 * <p>
 * <p>
 * 进阶：
 * 你是否可以在 O(1) 时间复杂度内执行两项操作？
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * LFUCache cache = new LFUCache( 2  );//capacity (缓存容量)
 * cache.put(1,1);
 * cache.put(2,2);
 * cache.get(1);       // 返回 1
 * cache.put(3,3);    // 去除 key 2
 * cache.get(2);       // 返回 -1 (未找到key 2)
 * cache.get(3);       // 返回 3
 * cache.put(4,4);    // 去除 key 1
 * cache.get(1);       // 返回 -1 (未找到 key 1)
 * cache.get(3);       // 返回 3
 * cache.get(4);       // 返回 4
 */


public class LFUCache {
    /**
     * key 就是题目中的 key
     * value 是结点类
     */
    private Map<Integer, ListNode> map;
    /**
     * 访问次数哈希表，使用 ListNode[] 也可以，不过要占用很多空间
     */
    private Map<Integer, DoubleLinkedList> frequentMap;

    /**
     * 外部传入的容量大小
     */
    private Integer capacity;

    /**
     * 全局最高访问次数，删除最少使用访问次数的结点时会用到（这个设计可能是冗余的）
     */
    private Integer maxFrequent = 1;

    public LFUCache(int capacity) {
        map = new HashMap<>(capacity);
        frequentMap = new HashMap<>();
        this.capacity = capacity;

    }

    /**
     * get 一次操作，访问次数就增加 1；
     * 从原来的链表调整到访问次数更高的链表的表头
     *
     * @param key
     * @return
     */
    public int get(int key) {
        if (capacity == 0) return -1;
        if (!map.containsKey(key)) return -1;
        // 获得结点类
        ListNode listNode = removeListNode(key);
        // 挂接到新的访问次数的双向链表的头部
        int frequent = listNode.frequent;
        addListNode2Head(frequent, listNode);
        return listNode.value;

    }

    public void put(int key, int value) {
        // 如果 key 存在，就更新访问次数 + 1，更新值
        if (map.containsKey(key)) {
            ListNode listNode = removeListNode(key);
            // 更新 value
            listNode.value = value;
            int frequent = listNode.frequent;
            addListNode2Head(frequent, listNode);
            return;
        }
        // 如果 key 不存在
        // 1、如果满了，先删除访问次数最小的的末尾结点，再删除 map 里对应的 key
        if (map.size() == capacity) {
            for (int i = 1; i <= maxFrequent; i++) {
                if (frequentMap.containsKey(i) && frequentMap.get(i).count > 0) {
                    // 1、从双链表里删除结点
                    DoubleLinkedList deleteLinkedList = frequentMap.get(i);
                    ListNode listNode = deleteLinkedList.removeTail();
                    map.remove(listNode.key);
                    break;
                }
            }
        }
        // 2、再创建新结点放在访问次数为 1 的双向链表的前面
        ListNode newListNode = new ListNode(key, value);
        addListNode2Head(1, newListNode);
        map.put(key, newListNode);
    }


    /**
     * 结点类，是双向链表的组成部分
     */
    private class ListNode {
        private int key;
        private int value;
        private int frequent = 1;
        private ListNode pre;
        private ListNode next;

        public ListNode() {

        }

        public ListNode(int key, int value) {
            this.key = key;
            this.value = value;
        }

    }


    /**
     * 双向链表
     */
    private class DoubleLinkedList {
        //虚拟头节点
        private ListNode dummyHead;
        //虚拟尾节点
        private ListNode dummyTail;
        /**
         * 当前双向链表的有效结点数
         */
        private int count;

        public DoubleLinkedList() {
            this.dummyHead = new ListNode(-1, -1);
            this.dummyTail = new ListNode(-1, -1);
            dummyHead.next = dummyTail;
            dummyTail.pre = dummyHead;
            this.count = 0;
        }

        /**
         * 把一个结点类添加到双向链表的开头（头部是最新使用数据）
         *
         * @param addNode
         */
        public void addNode2Head(ListNode addNode) {
            ListNode t = dummyHead.next;
            dummyHead.next = addNode;
            t.pre = addNode;
            addNode.next = t;
            addNode.pre = dummyHead;
            count++;
        }

        /**
         * 把双向链表的末尾结点删除（尾部是最旧的数据，在缓存满的时候淘汰）
         *
         * @return
         */

        public ListNode removeTail() {
            ListNode oldTail = dummyTail.pre;
            ListNode newTail = oldTail.pre;
            // 两侧结点建立连接
            newTail.next = dummyTail;
            dummyTail.pre = newTail;
            oldTail.next = null;
            oldTail.pre = null;
            count--;
            return oldTail;

        }

    }

    /**
     * 将原来访问次数的结点，从双向链表里脱离出来
     *
     * @param key
     * @return
     */
    private ListNode removeListNode(int key) {
        // 获得结点类
        ListNode deleteNode = map.get(key);
        ListNode preNode = deleteNode.pre;
        ListNode nextNode = deleteNode.next;
        //两侧节点建立连接
        preNode.next = nextNode;
        nextNode.pre = preNode;
        //去除关联
        deleteNode.pre = null;
        deleteNode.next = null;
        // 维护双链表结点数
        frequentMap.get(deleteNode.frequent).count--;
        //访问次数+1
        deleteNode.frequent++;
        maxFrequent = Math.max(maxFrequent, deleteNode.frequent);

        return deleteNode;
    }

    /**
     * 把结点放在对应访问次数的双向链表的头部
     *
     * @param frequent
     * @param addNode
     */
    private void addListNode2Head(int frequent, ListNode addNode) {
        DoubleLinkedList doubleLinkedList;
        // 如果不存在，就初始化
        if (frequentMap.containsKey(frequent)) {
            doubleLinkedList = frequentMap.get(frequent);
        } else {
            doubleLinkedList = new DoubleLinkedList();
        }
        doubleLinkedList.addNode2Head(addNode);
        frequentMap.put(frequent, doubleLinkedList);
    }
}

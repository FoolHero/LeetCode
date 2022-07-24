//package com.wzy;
//
//import com.wzy.base.ListNode;
//
//import java.util.HashMap;
//import java.util.Map;
//
//
//public class LFUCache {
//
//    private Map<Integer, ListNode> map;
//
//    private int capacity;
//    HashMap
//
//    private Map<Integer, TLinkedList> countMap;
//    // 全局最小频次
//    private int min_frequency;
//
//    public class ListNode {
//
//        int key;
//        int val;
//        int frequency;
//        ListNode pre;
//        ListNode next;
//
//        ListNode() {
//        }
//
//        ListNode(int key, int val) {
//            this.key = key;
//            this.val = val;
//            this.frequency = 1;
//        }
//    }
//
//    public LFUCache(int capacity) {
//        this.capacity = capacity;
//        this.map = new HashMap<>(capacity);
//        this.countMap = new HashMap<>();
//        this.min_frequency = Integer.MAX_VALUE;
//    }
//
//    public int get(int key) {
//        if (!map.containsKey(key)) {
//            return -1;
//        }
//        ListNode node = map.get(key);
//        if (node == null) {
//            return -1;
//        }
//        handle(node, false);
//        return node.val;
//    }
//
//
//    /**
//     * 关键函数，当put/set操作发生后调用此函数，分为新增结点以及对现有结点操作
//     * 1、新增结点：直接接到对于频次的双向链表头部
//     * 2、已存在结点：从老结点所在频次对应的双向链表中删除此结点后，再将其加入到频次+1对应的双向链表的头部
//     *
//     * @param node  结点
//     * @param isNew 是否是新增结点
//     */
//    private void handle(ListNode node, boolean isNew) {
//        int frequency = node.frequency;
//        //维护全局最小频次
//        min_frequency = Math.min(frequency, min_frequency);
//        if (countMap.containsKey(node.frequency)) {
//            TLinkedList oldlist = countMap.get(node.frequency);
//            if (!isNew) {
//                node.frequency++;
//                oldlist.removeNode(node);
//                // 如果
//                // 1、当前结点不为新增结点
//                // 2、当前结点频次==全局最小频次
//                // 3、且删除该结点后，对应频次双向链表为空，
//                // 则全局最小频次+1
//                if (frequency == min_frequency && oldlist.count == 0) {
//                    min_frequency++;
//                }
//            }
//        }
//        if (!countMap.containsKey(node.frequency)) {
//            countMap.put(node.frequency, new TLinkedList());
//        }
//        TLinkedList newList = countMap.get(node.frequency);
//        newList.addNode2Head(node);
//    }
//
//
//    public void put(int key, int val) {
//        if (capacity == 0) return;
//        ListNode node;
//        if (map.containsKey(key)) {
//            node = map.get(key);
//            node.val = val;
//            handle(node, false);
//        } else {
//            node = new ListNode(key, val);
//            //容量超出，则删除全局最小频次对应双向链表中的尾部结点
//            if (map.size() >= capacity) {
//                TLinkedList oldList = countMap.get(min_frequency);
//                ListNode tail = oldList.removeTail();
//                map.remove(tail.key);
//            }
//            map.put(key, node);
//            handle(node, true);
//        }
//
//    }
//
//    class TLinkedList {
//        //含有多少个结点，不包含虚拟头尾结点
//        int count;
//        //虚拟头结点
//        ListNode dummyHead;
//        //虚拟尾结点
//        ListNode dummyTail;
//
//        TLinkedList() {
//            this.count = 0;
//            this.dummyHead = new ListNode();
//            this.dummyTail = new ListNode();
//            this.dummyHead.next = this.dummyTail;
//            this.dummyTail.pre = this.dummyHead;
//        }
//
//        void addNode2Head(ListNode node) {
//            node.next = dummyHead.next;
//            dummyHead.next.pre = node;
//            node.pre = dummyHead;
//            dummyHead.next = node;
//            this.count++;
//        }
//
//        ListNode removeNode(ListNode node) {
//            ListNode pre = node.pre;
//            ListNode next = node.next;
//            pre.next = next;
//            next.pre = pre;
//            this.count--;
//            return node;
//        }
//
//
//        ListNode removeTail() {
//            ListNode node = this.dummyTail.pre;
//            node.pre.next = this.dummyTail;
//            this.dummyTail.pre = node.pre;
//            node.pre = null;
//            node.next = null;
//            this.count--;
//            return node;
//        }
//
//    }
//
//    /**
//     * ["LFUCache","put","put","get","put","get","get","put","get","get","get"]
//     * [[2],[1,1],[2,2],[1],[3,3],[2],[3],[4,4],[1],[3],[4]]
//     *
//     * @param args
//     */
//    // public static void main(String[] args) {
//    //     LFUCache cache = new LFUCache(2);
//    //     cache.set(1, 1);
//    //     cache.set(2, 2);
//    //     System.out.println(cache.get(1));
//    //     cache.set(3, 3);
//    //     System.out.println(cache.get(2));
//    //     System.out.println(cache.get(3));
//    //     cache.set(4, 4);
//    //     System.out.println(cache.get(1));
//    //     System.out.println(cache.get(3));
//    //     System.out.println(cache.get(4));
//    // }
//
//
//}
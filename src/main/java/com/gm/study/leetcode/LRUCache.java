package com.gm.study.leetcode;

import java.util.HashMap;

// 最近最少缓存
// 想象成是一叠书
// get，抽出来，放到最上面
// put，先拿到，然后更新值，如果是空，往最上面新增一个。最后需要检查书的总数是否到 capacity，要淘汰
// 查找需要是 O（1）的，所以用一个 map，快速查找
public class LRUCache {

    // 节点
    static class Node {
        int key;
        int value;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
        Node pre, next;
    }

    int capacity;
    HashMap<Integer, Node> key2Node = new HashMap<>();
    Node dummy = new Node(0,0);

    // LRU 的初始化,为实现 O(1) 的查找需要 map，还需要有一个虚拟节点
    public LRUCache(int capacity) {
        this.capacity = capacity;
        dummy.pre = dummy;
        dummy.next = dummy;
    }

    public int get(int key) {
        Node node = key2Node.get(key);
        if (node == null) return -1;
        // 抽出来
        remove(node);
        // 放到最上面
        putFront(node);
        return node.value;
    }

    public void put(int key, int value) {
        int val = get(key);
        if (val != -1) {
            // 有节点存在，更新值就好
            Node node = key2Node.get(key);
            node.value = value;
            return;
        }
        // 没值存在,新增
        Node node = new Node(key,value);
        node.next = dummy.next;
        node.pre = dummy;
        dummy.next = node;
        node.next.pre = node;
        // 在map里也要新加
        key2Node.put(key, node);
        // 判断是否达到淘汰阈值
        if (key2Node.size() > capacity) {
            Node re = dummy.pre;
            dummy.pre = re.pre;
            re.pre.next = dummy;
            re.pre = null;
            re.next = null;
            // map里也要删除
            key2Node.remove(re.key);
        }
    }

    public void remove(Node node) {
        if (node == null) return;
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    public void putFront(Node node) {
        node.next = dummy.next;
        node.pre = dummy;
        node.pre.next = node;
        node.next.pre = node;
    }
}

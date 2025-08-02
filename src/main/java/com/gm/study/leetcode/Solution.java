package com.gm.study.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Solution {

    //统计某时间点日志分布的条数
    //题目：程序日志打印文件名为logs格式为：
    // NOTICE 2009-09-09 08:00:00 ……………[proc time :12 ms]……
    //统计2009-09-09 08点到10点间所有日志分布在0-9ms 10-99ms 100ms+的日志条数（8分钟）
    // new InputStream("filePath")
    // string , string.substring(0,9), 08:00:00  09 [
    // idx -> 7 equals("2009-09-09")
    // kmp 算法，字符串匹配
    // shell

    //Grep  -e“NOTICE 2009-09-09 0[8,9].*[ proc time :/d+ ms]” logs >temp
    //    Grep –e “[ proc time :[0-9]ms]“  temp |wc
    //Grep –e “[ proc time :[1-9][0-9]ms]“  temp |wc
    //Cat temp |wc



//    public static void main(String[] args) {
//        int a = 11;
//        // Object*
//        System.out.println(divide(a));
//    }
//
//    // n 可以分成若干个 自然数，比如 45分成 3*15,和 5*9，
//    public static int divide(int n) {
//        int ans = 0;
//        // 已经不能被 2 分了，从3开始 到 sqrt(n) 进行分
//        for (int i = 2; i <= Math.sqrt(n); i++) {
//            while (n % i == 0) {
//                ans += i;
//                n /= i;
//            }
//        }
//        // 最后n可能不可再分，加入本身
//        ans += n;
//        return ans;
//    }













//    // 滑动窗口限流
//    // 队列，装时间戳
//    private Deque<Long> requestTimeStamps;
//    // 最大允许请求量
//    private int capacity;
//    // 窗口大小
//    private int windowSize;
//
//    public com.gm.Solution(int capacity, int windowSize) {
//        this.capacity = capacity;
//        this.windowSize = windowSize;
//        requestTimeStamps = new LinkedList<>();
//    }
//
//    public synchronized void tryConsume() {
//        // 获取当前时间
//        long now = Instant.now().toEpochMilli();
//        // 清理窗口内的过期时间
//        cleanWindow(now);
//        if (requestTimeStamps.size() < capacity) {
//            requestTimeStamps.addLast();
//        } else {
//            throw new RuntimeException("请求太多");
//        }
//    }
//
//    // 清理窗口内的过期时间，把当前时间传进来
//    private void cleanWindow(long currentTime) {
//        while (!requestTimeStamps.isEmpty() && currentTime - requestTimeStamps.peekFirst() > windowSize) {
//            requestTimeStamps.pollFirst();
//        }
//    }
//
//    public static void main(String[] args) {
//
//    }

//    public static void main(String[] args) {
//        ListNode listNode = new ListNode(1);
//        listNode.next = new ListNode(2);
//        listNode.next.next = new ListNode(3);
//
//        ListNode reverse = reverse(listNode);
//        System.out.println(reverse.val);
//    }
//    static class ListNode {
//
//        public ListNode(int val) {
//            this.val = val;
//        }
//        int val;
//        ListNode next;
//    }
//
//    public static ListNode reverse(ListNode head) {
//        ListNode pre = null;
//        ListNode curr = head;
//
//        while (curr != null) {
//            ListNode tmp = curr.next;
//            curr.next = pre;
//            pre = curr;
//            curr = tmp;
//        }
//        return pre;
//    }


    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> list = new LinkedList<>();
    int sum = 0;
    int target;
    public List<List<Integer>> func(int[] candidates, int target) {
        this.target = target;
        backTracking(candidates, 0);
        return ans;
    }

    public void backTracking(int[] candidates, int idx) {
        if (sum == target) {
            ans.add(new ArrayList(list));
            return;
        }

        if (sum > target) {
            return;
        }

        for (int i = idx; i < candidates.length; i++) {
            sum += candidates[i];
            list.add(candidates[i]);
            backTracking(candidates, i+1);
            sum -= candidates[i];
            list.remove(list.size()-1);
        }
    }






















}


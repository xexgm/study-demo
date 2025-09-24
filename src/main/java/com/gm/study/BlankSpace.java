package com.gm.study;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * @Author: xexgm
 */
public class BlankSpace {

    /**
     * 求 x 平方根，t为误差阈值
     */
    public static double func(int x, double t) {
        double ans = 1;
        double l = 1;
        double r = x;
        while (true) {
            double mid = l + (r-l)/2;

            if (mid * mid - x > t) {
                r = mid;
            } else if (x - mid * mid > t) {
                l = mid;
            } else {
                ans = mid;
                break;
            }

        }
        return ans;
    }

    public static String funcA() {
        //Scanner sc = new Scanner(System.in);
        //int a = sc.nextInt();
        //int b = sc.nextInt();
        //int k = sc.nextInt();
        // a/b 保留 k 位小数
        int a = 200;
        int b = 199;
        int k = 2;
        // a/b 保留两位小数
        String s = a/b + "";

        // 找到 . 的下标
        int idx = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '.') {
                idx = i;
                break;
            }
        }
        // 判断小数的位数 够不够 k 位
        int len = idx+1 + k;
        // 如果不够 k 位，直接补0
        if (s.length() < len) {
            StringBuilder sb = new StringBuilder(s);
            for (int i = 0; i < len-s.length(); i++) {
                sb.append(0);
            }
            return new String(sb);
        }

        // 如果够 k 位，进行四舍五入
        if (s.length() == len || (s.charAt(idx + k + 1) - '0' < 5)) {
            return s.substring(0, idx+k+1);
        }

        // 要做进位
        String str = s.substring(0, idx+k+1);
        StringBuilder ans = new StringBuilder();
        int carry = 1;
        for (int i = str.length()-1; i >= 0; i--) {
            if (str.charAt(i) == '.') {
                ans.append('.');
                continue;
            }
            int x = (str.charAt(i)-'0') + carry;
            ans.append(x%10);
            carry = x >= 10 ? 1 : 0;
        }
        return new String(ans.reverse());
    }

    static int ans = Integer.MAX_VALUE;
    static int biSum = 0;
    static int aiSum = 0;
    static int n;
    boolean[] use;
    public static int funcB() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        //use = new boolean[n];
        int[] a = new int[n];
        int[] b = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
            b[i] = sc.nextInt();
        }

        // 递归，对每个节点进行判断，选出最小的 biSum，
        backTracking(a, b, 0);

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static void backTracking(int[] a, int[] b, int idx) {
        if (aiSum == 1) {
            ans = Math.min(ans, biSum);
            return;
        }

        if (idx == n) {
            return;
        }

        for (int i = idx; i < n; i++) {
            //if (use[i]) {
            //    continue;
            //}
            //use[i] = true;
            aiSum += a[i];
            biSum += b[i];
            backTracking(a, b, i+1);
            aiSum -= a[i];
            biSum -= b[i];
            //use[i] = false;
        }
    }

    // 长度为 n，切成 整数长的 m 段，求 段数长度最大的乘积
    // 把
    //public int cutRope (int n) {
    //    // write code here
    //
    //}

    /**
     * Note: 类名、方法名、参数名已经指定，请勿修改
     *
     *
     * 找出数组arr里出现k次或以上的元素
     * @param arr int整型 一维数组 要查找的数组
     * @param k int整型  出现次数
     * @return int整型一维数组
     * 时间限制: 3000ms
     * 内存限制:256.0MB
     */
    public static int[] searchDupK(int[] arr, int k) {
        // write code here
        List<Integer> list = new ArrayList<>();
        //  arr 无序, 找出 arr 中，所有出现次数 >= k 的数，
        Arrays.sort(arr);
        for (int i = 0, j = 1; i < arr.length && j <= arr.length;) {
            // i 定位第一个元素
            // j 寻找与 i 相同的元素
            int x = arr[i];
            while (j < arr.length && arr[j] == arr[i]) {
                j++;
            }
            // j-1 ~ i 都是相同的元素
            if (j - i>= 2) {
                list.add(arr[i]);
            }
            i = j;
            j = i+1;
        }
        // 把 list 转为 int
        int[] ans = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }



    /**
     * Note: 类名、方法名、参数名已经指定，请勿修改
     *
     *
     * 实现两个大数相加，并且大数中可能包含小数位
     * @param numStr1 string字符串  一个大数
     * @param numStr2 string字符串  另外一个大数
     * @return string字符串
     */
    public static String bigAdd(String numStr1, String numStr2) {
        // write code here
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        StringBuilder small = new StringBuilder();

        // 处理整数部分的下标
        int str1Idx = numStr1.length()-1;
        int str2Idx = numStr2.length()-1;

        // 如果 两个 str 都有小数点
        // 1.先处理小数，然后带上 carry，去处理整数部分
        if (numStr1.contains(".") && numStr2.contains(".")) {
            // "." 的下标
            int idx1 = numStr1.indexOf(".");
            int idx2 = numStr2.indexOf(".");

            str1Idx = idx1-1;
            str2Idx = idx2-1;

            for (int i = numStr1.length()-1, j = numStr2.length()-1; i > idx1 && j > idx2;) {
                // 小数长度未对齐时
                while (i-idx1 != j-idx2) {
                    if (i-idx1 > j-idx2) {
                        small.append(numStr1.charAt(i));
                        i--;
                    } else {
                        small.append(numStr2.charAt(j));
                        j--;
                    }
                }
                // 对齐
                int ap = numStr1.charAt(i--)-'0' + numStr2.charAt(j--)-'0' + carry;
                carry = ap >= 10 ? 1 : 0;
                small.append(ap%10);
            }
            small.append(".");
            small.reverse();
        } else if (numStr1.contains(".")) {
            int idx = numStr1.indexOf(".");
            small.append(numStr1, idx, numStr1.length());
            str1Idx = idx-1;
        } else if (numStr2.contains(".")) {
            int idx = numStr2.indexOf(".");
            small.append(numStr2, idx, numStr2.length());
            str2Idx = idx-1;
        }

        // 2.处理整数部分,起点分别是 strIdx1 和 strIdx2
        for (; str1Idx >= 0 && str2Idx >= 0;) {
            int x = numStr1.charAt(str1Idx--)-'0' + numStr2.charAt(str2Idx--)-'0' + carry;
            carry = x >= 10 ? 1 : 0;
            sb.append(x%10);
        }

        // 处理整数不对齐部分
        while (str1Idx >= 0) {
            int x = numStr1.charAt(str1Idx--) - '0' + carry;
            carry = x >= 10 ? 1 : 0;
            sb.append(x%10);
        }

        while (str2Idx >= 0) {
            int x = numStr2.charAt(str2Idx--) - '0' + carry;
            carry = x >= 10 ? 1 : 0;
            sb.append(x%10);
        }

        // 处理剩余的carry
        if (carry != 0) {
            sb.append(carry);
        }

        sb.reverse();
        sb.append(small);
        return new String(sb);
    }



    //public static void main(String[] args) {
    //    // 第一行：接下来有 n 天的发货价格，有 m 个订单需要发货，每天最多能发 x 个包裹
    //    // 第二行：n个整数，分别代表第 i 天发货一个包裹的价格 ai
    //    // 第三行：m个整数，代表订单 j 需要在第 bj 天前发货
    //    Scanner sc = new Scanner(System.in);
    //    int n = sc.nextInt();
    //    int m = sc.nextInt();
    //    int x = sc.nextInt();
    //    long ans = 0l;
    //
    //    Node[] a = new Node[n];
    //    for (int i = 0; i < n; i++) {
    //        long value = sc.nextLong();
    //        Node node = new Node(value, x);
    //        a[i] = node;
    //    }
    //
    //    int[] b = new int[m];
    //    for (int i = 0; i < m; i++) {
    //        b[i] = sc.nextInt();
    //    }
    //
    //    // b数组，说明了 某一个订单，最迟需要在哪一天前发货
    //    // a数组，说明了 某一天发货 的价格
    //    // 1.先对 b[] 排序
    //    // 对每个订单，它的期限之前，找价格最低的那一天
    //
    //    Arrays.sort(b);
    //    PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> Math.toIntExact(n1.value - n2.value));
    //    for (int i = 0; i < m; i++) {
    //        int i1 = b[i];
    //
    //        // 找到 < i1 的下标的最小的 value 的 node
    //        // 放到 pq 里
    //        for (int j = 0; j < i1; j++) {
    //            Node node = a[j];
    //            if (!pq.contains(node)) {
    //                pq.add(node);
    //            }
    //        }
    //        // 从 pq 中拿到符合条件的，limit和idx，的node
    //        Iterator<Node> iterator = pq.iterator();
    //        long v = 0;
    //        while (iterator.hasNext()) {
    //            Node next = iterator.next();
    //            if (next.limit > 0) {
    //              v = next.value;
    //              next.limit--;
    //              break;
    //            }
    //        }
    //        ans += v;
    //    }
    //    System.out.println(ans);
    //
    //}

    static class Node{
        long value;
        int limit;

        public Node(long value, int limit) {
            this.value = value;
            this.limit = limit;
        }

        public Node() {
        }

        public long getValue() {
            return value;
        }

        public int getLimit() {
            return limit;
        }
    }



    public static void main2() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();
        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        while (q-- > 0) {
            // 读取 x 和 y
            int x = sc.nextInt();
            int y = sc.nextInt();
            int ans = 0;

            int i1 = n/x;
            int i2 = n/y;

            // l[i] 表示要+的 a数组中第 x 个数
            Set<Integer> l = new HashSet<>();
            for (int i = 0; i < i1; i++) {
                l.add((i+1)*x);
            }

            Set<Integer> r = new HashSet<>();
            for (int i = 0; i < i2; i++) {
                r.add((i+1)*y);
            }
            // 找出前i1个最大的数，和后i2个最小的数
            // 先按大到小排序
            // 先对比两边
            Iterator<Integer> iterator = l.iterator();
            while (iterator.hasNext()) {
                Integer next = iterator.next();
                if (r.contains(next)) {
                    r.remove(next);
                    l.remove(next);
                    iterator.remove();
                }
            }
            // 重复的已经去掉了
            // 找出l里的size，个数的最大数，r里的size个数的最小个数
            Arrays.sort(a);
            int add = l.size();
            int exa = r.size();
            for (int i = 0; i < add; i++) {
                ans += a[a.length-1-i];
            }
            for (int i = 0; i < exa; i++) {
                ans -= a[i];
            }
            System.out.println(ans);
        }
    }
    public static String multiply (String a1, String a2) {
        // 大数相乘
        // 123
        // 345
        // 遍历 a2的位数，依次和a1相乘，相乘完，做大数相加

        String ans = "0";
        long carry = 0;
        for (int i = a2.length()-1; i >= 0; i--) {
            char multi = a2.charAt(i);

            StringBuilder sb = new StringBuilder();

            for (int j = a1.length()-1; j >= 0; j--) {
                long x = (a1.charAt(j)-'0') * (multi-'0') + carry;
                carry = x/10;
                sb.append(x%10);
            }
            // carry != 0
            if (carry != 0) {
                sb.append(carry);
                // todo 致命，这里carry没置为0
            }
            sb.reverse();
            // 处理实际大小，要乘几个10
            for (int k = 0; k < (a2.length()-1-i); k++) {
                sb.append(0);
            }

            // 当前 sb 与 ans 去相加
            String tmp = new String(sb);
            ans = add(tmp, ans);
        }

        return ans;

    }

    public static String add(String s1, String s2) {
        long carry = 0;

        int i = s1.length()-1;
        int j = s2.length()-1;
        StringBuilder sb = new StringBuilder();
        while (i >= 0 && j >= 0) {
            long x = (s1.charAt(i--)-'0') + (s2.charAt(j--)-'0') + carry;
            carry = x/10;
            sb.append(x%10);
        }

        while (i >= 0) {
            long x = (s1.charAt(i--)-'0') + carry;
            carry = x/10;
            sb.append(x%10);
        }
        while (j >= 0) {
            long x = (s1.charAt(j--)-'0') + carry;
            carry = x/10;
            sb.append(x%10);
        }

        // 判断 carry
        if (carry != 0) {
            sb.append(carry);
        }

        return new String(sb.reverse());
    }


    // 最小因式分解,给定一个正整数a，找出最小的正整数b，使得b的所有数位
    // 相乘恰好等于a
    // eg. 15 -> 35
    public int test (int a) {
        // write code here
        return 0;
    }

    // 买卖股票变种
    public static void main2(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Double> list = new ArrayList<>();

        String s = sc.nextLine();
        String[] split = s.split(",");
        double[] nums = new double[split.length];
        for (int i = 0; i < split.length; i++) {
            nums[i] = Double.parseDouble(split[i]);
        }

        // 从右往左遍历，放入栈中
        // 如果 当前数 比栈顶大，两种情况，
        // 1.栈顶作为 in，栈底作为 out，进行一次买卖
        // 2.栈里只有一个元素，把栈中元素 pop，当前数 push入栈
        // 如果当前数比栈顶小，入栈

        double ans = 0;

        Deque<Double> st = new ArrayDeque<>();

        for (int i = nums.length-1; i >= 0; i--) {
            double num = nums[i];

            if (st.isEmpty()) {
                st.push(num);
            } else if (!st.isEmpty() && num >= st.peek()) {
                Double in = st.pop();
                if (st.isEmpty()) {
                    st.push(num);
                } else {
                    double out = 0;
                    while (!st.isEmpty()) {
                        out = st.pop();
                    }
                    ans += (out-in);
                    st.push(num);
                }
            } else if (!st.isEmpty() && num < st.peek()) {
                st.push(num);
            }
        }
        System.out.println(ans);
    }

    public static void main3(String[] args) {
        int[] palindromeSubarrays = find_palindrome_subarrays(new int[] {12, 3, 21, 12, 3, 21},3);

        System.out.println(1);
    }



    public static int[] find_palindrome_subarrays (int[] nums, int k) {
        // write code here
        // 遍历，依次判断区间内是否是回文
        ArrayList<Integer> ans = new ArrayList<>();

        for (int i = 0; i <= nums.length-k; i++) {
            if (valid(nums, i, i+k-1)) {
                ans.add(i);
            }
        }
        int[] arr = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            arr[i] = ans.get(i);
        }
        return arr;
    }


    public static boolean valid(int[] nums, int start, int end) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i <= end; i++) {
            sb.append(nums[i]);
        }
        int i = 0, j = sb.length()-1;
        while (i < j) {
            if (sb.charAt(i) != sb.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] a = new int[] {1,1,2,2,3,4,4,5,5};
        System.out.println(binarySearch(a));
    }

    // 有序成对数组，只有一个数出现过一次，把这个数找出来
    public static int binarySearch(int[] nums) {
        // 这个数出现的左边，成对的第一个数肯定在偶数位上，第二个数肯定在奇数位上
        // 这个数出现之后，规则被打破了
        int l = 0;
        int r = nums.length-1;

        while (l < r) {
            int mid = l + (r - l)/2;

            if (mid % 2 == 1) {
                mid--;
            }

            if (nums[mid] == nums[mid + 1]) {
                l = mid + 2;
            } else {
                r = mid;
            }
        }
        return nums[l];
    }


    public int func4() {
        Scanner sc = new Scanner(System.in);

        HashMap<Integer, List<LNode>> map = new HashMap<>();
        int n = sc.nextInt();
        int c = sc.nextInt();

        for (int i = 0; i < n; i++) {
            int k = sc.nextInt();
            int v = sc.nextInt();
            // 不存在k
            LNode lNode = new LNode(k, v);
            if (!map.containsKey(k)) {
                ArrayList<LNode> list = new ArrayList<>();
                list.add(lNode);
                map.put(k, list);
            } else {
                List<LNode> lNodes = map.get(k);
                lNodes.add(lNode);
            }
        }
        int ans = 0;
        // 获取推导式的数量
        ans = dfs(map, c);
        return ans + 1;
    }

    public int dfs(HashMap<Integer, List<LNode>> map, int c) {
        if (!map.containsKey(c)) {
            return 0;
        }
        List<LNode> lNodes = map.get(c);
        int num1 = lNodes.size();
        // 遍历 lnodes，拿到所有的 v
        // 用 v 去找剩余的数量
        for (int i = 0; i < lNodes.size(); i++) {
            LNode lNode = lNodes.get(i);
            num1 += dfs(map, lNode.v);
        }
        return num1;
    }

    class LNode {
        int k;
        int v;

        public LNode(int k, int v) {
            this.k = k;
            this.v = v;
        }
    }

}

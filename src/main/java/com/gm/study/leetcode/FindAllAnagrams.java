package com.gm.study.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xexgm
 * des: 找出所有异位词，包含大写字母版本
 */
public class FindAllAnagrams {

    private static List<Integer> ans = new ArrayList<>();

    public static void main(String[] args) {
        List<Integer> ans = findAllAnagrams("cbaDebabacDd", "abcD");
        System.out.println(ans);
    }


    // 模板字符串，对应的数组元素+1，遍历 s，当元素<0了，就把队头移出，判断队列的长度，是否符合 p 的长度，如何符合，就是一个答案
    public static List<Integer> findAllAnagrams(String s, String p) {
        int[] nums = new int[128];

        for (int i = 0; i < p.length(); i++) {
            nums[p.charAt(i)] ++;
        }

        int hh = 0;
        int tt = -1;

        tt ++;
        for (; tt < s.length(); tt++) {
            nums[s.charAt(tt)] --;

            while (tt >= hh && nums[s.charAt(tt)] < 0) {
                // 队头移出
                nums[s.charAt(hh++)] ++;
            }

            if (tt-hh+1 == p.length()) {
                ans.add(hh);
            }
        }
        return ans;
    }

}

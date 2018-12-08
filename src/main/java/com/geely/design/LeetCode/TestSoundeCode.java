package com.geely.design.LeetCode;

import java.util.ArrayList;

/**
 * Created by xiaolin on 2018/12/4.
 */
public class TestSoundeCode {

    ArrayList<String> arrayList;
    static int[] nums = {5, 5, 5, 6, 7, 7, 7, 7, 9, 9, 9, 9, 9};
    //  5   6  7   9
    //如果相邻的2个数，如果是相同的，就删除，不同的就挪动最前面
    //解决数组这种题目，可以定义一个index变量，去数组中获取第一个元素和所有的比较，对比
    public static void main(String[] args) {
//        System.out.println(removeDuplicates(nums));
        int number = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != nums[number]) {//从0开始，数组每个元素对比一次
                number++;//6不相等，将6移动到1位置
                nums[number] = nums[i];
            }
        }
        System.out.println(++number);
    }


    //    给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
//    不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
    //
//    给定一个有序数组，删除重复内容，使每个元素只出现一次，并返回新的长度。
//    不要为其他数组分配额外的空间，您必须通过在 o(1)额外的内存中修改输入数组来实现。
    public static int removeDuplicates(int[] nums) {
        int number = 0;
        for (int i = 0; i < nums.length; i++) {
            // 相邻两个值比较，不同才做统计操作
            if (nums[i] != nums[number]) { //0,1,2
                number++;
                nums[number] = nums[i]; //3   nums[1] = nums[3];
            }
        }
        //不同数字为总量= number+1
        return ++number;

    }
}

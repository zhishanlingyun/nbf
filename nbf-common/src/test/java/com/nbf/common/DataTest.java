package com.nbf.common;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 15-12-7
 * Time: 下午11:27
 * To change this template use File | Settings | File Templates.
 */
public class DataTest {

    /**
     * 在n个数里，存在一个数x，出现频率超过n/2的数，要以最小的时间复杂度计算出这个x
     * 引理：n个数中，数x出现频率超过n/2，那么从中去掉一对不相等的两个数，x在剩下的(n-2)个数中的出现频率依然超过n/2
     * 其实说到底非常简单，就是在一堆数里随便拿一个数，再找一个与它不相等的，然后一起扔掉，这样问题规模不断缩小，最终等到找不到一个不相等的数时，就成功 了。但要简化算法，就不能每拿一个数就统统找一遍。可以考虑准备一个队列，队列里放着暂时扔不掉的数。如从头开始，将a[0]放入队列，再看a[1]，如 果a[0] != a[1]，则扔掉a[1]和a[0]，a[0]从队列取出；如果a[0] == a[1]，则a[1]入队列，然后a[2]进行相同的操作，以此类推。
     * 解法依然可以优化。显而易见，队列里所有的数总是全部相等的，既然相等就没有必要存入队列，只要知道：1.假想的队列里的数什么 2.队列的长度
     */
    public int data_more_than_half( int[] arr) {
             int candidate=0;
             int count = 0;

             for(int i = 0; i < arr.length; i++) {
                     if (count == 0) {
                             candidate = arr[i];
                             count = 1;
                         }
                    else {
                             if (candidate == arr[i]) {
                                     count++;
                                 }
                            else {
                                    count--;
                                }
                        }
                 }
             return candidate;
    }

    @Test
    public void data_more(){
        int[] attr = {1,2,2,3,2,3,3,3,3,3};
        System.out.println(data_more_than_half(attr));
    }

}

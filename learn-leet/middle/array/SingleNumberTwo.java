package middle.array;

/**
 * Created by wm on 16/4/27.
 * 137. Single Number II
 * Given an array of integers, every element appears three times except for one. Find that single one.
 * 每个元素出现三次，但是一个出现两次，查找到，
 * <p/>
 * Note:
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 * 线性，不使用额外存储
 *
 * 1，map 可以额外存储
 * 2，亦或
 *
 * == 解法
 * 1,数组中其他数出现两次，仅有一个出现一次的，直接用所有元素异或就行了（只要是偶数次，都可以用这个方法）
 *
 * 2,而如果其他元素出现3次，而且时间复杂度要求线性，空间为常数。需要使用移位
 *
 * // 这个书写方式涉及到 32位二进制数的运算
 * http://blog.csdn.net/jiadebin890724/article/details/23306837
 *
 *
 *
 */
public class SingleNumberTwo {

    // todo


}

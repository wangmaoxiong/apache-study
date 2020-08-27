package com.wmx.sorting;

/**
 * Java 实现 8 大排序
 *
 * @author wangMaoXiong
 * @version 1.0
 * @date 2020/8/27 9:08
 */
public class SortingAlgorithm {

    public static void main(String[] args) {
        int[] dataArray = new int[]{465, 55, 34, 345, 355, 3, 67, 465, 665, 34};
        int[] dataArray1 = new int[]{265, 55, 134, 345, 355, 553, 617, 465, 665, 334};

        selectSort(dataArray);
        for (int i = 0; i < dataArray.length; i++) {
            System.out.print(dataArray[i] + " ");
        }

        System.out.println();
        quickSort(dataArray1, 0, dataArray1.length - 1);
        for (int i = 0; i < dataArray1.length; i++) {
            System.out.print(dataArray1[i] + " ");
        }
    }

    /**
     * 快速排序（效率最高，速度快）
     * 1、选择第一个数为p，小于p的数放在左边，大于p的数放在右边。
     * 2、递归的将p左边和右边的数都按照第一步进行，直到不能递归。
     *
     * @param numbers ：待排序的数组
     * @param start   ：排序的起始位置，比如 0
     * @param end     ：排序的结束位置，比如 numbers.length - 1
     */
    public static void quickSort(int[] numbers, int start, int end) {
        if (numbers == null || numbers.length <= 0 || start >= end) {
            return;
        }
        // 排序的第一个数值作为基准值
        int base = numbers[start];
        // 记录临时中间值
        int temp;
        int i = start, j = end;
        do {
            while ((numbers[i] < base) && (i < end)) {
                i++;
            }
            while ((numbers[j] > base) && (j > start)) {
                j--;
            }
            if (i <= j) {
                temp = numbers[i];
                numbers[i] = numbers[j];
                numbers[j] = temp;
                i++;
                j--;
            }
        } while (i <= j);

        if (start < j) {
            quickSort(numbers, start, j);
        }
        if (end > i) {
            quickSort(numbers, i, end);
        }
    }

    /**
     * 希尔排序
     * 1、将数的个数设为n，取k=n/2，将下标差值为k的数分为一组，构成有序序列。
     * 2、再取k=k/2 ，将下标差值为k的书分为一组，构成有序序列。
     * 3、重复第二步，直到k=1执行简单插入排序。
     *
     * @param numbers
     */
    public static void shellSort(int[] numbers) {
        if (numbers == null || numbers.length <= 0) {
            return;
        }
        int d = numbers.length;
        while (d != 0) {
            d = d / 2;
            //分的组数
            for (int x = 0; x < d; x++) {
                //组中的元素，从第二个数开始
                for (int i = x + d; i < numbers.length; i += d) {
                    //j为有序序列最后一位的位数
                    int j = i - d;
                    //要插入的元素
                    int temp = numbers[i];
                    //从后往前遍历。
                    for (; j >= 0 && temp < numbers[j]; j -= d) {
                        //向后移动d位
                        numbers[j + d] = numbers[j];
                    }
                    numbers[j + d] = temp;
                }
            }
        }
    }

    /**
     * 直接插入排序
     * 将第一个数和第二个数排序，然后构成一个有序序列
     * 将第三个数插入进去，构成一个新的有序序列。
     * 对第四个数、第五个数……直到最后一个数，重复第二步。
     *
     * @param numbers
     */
    public static void insertSort(int[] numbers) {
        //数组长度，将这个提取出来是为了提高速度。
        int length = numbers.length;
        int insertNum;//要插入的数
        //插入的次数
        for (int i = 1; i < length; i++) {
            //要插入的数
            insertNum = numbers[i];
            //已经排序好的序列元素个数
            int j = i - 1;
            //序列从后到前循环，将大于insertNum的数向后移动一格
            while (j >= 0 && numbers[j] > insertNum) {
                //元素移动一格
                numbers[j + 1] = numbers[j];
                j--;
            }
            //将需要插入的数放在要插入的位置。
            numbers[j + 1] = insertNum;
        }
    }

    /**
     * 冒泡排序
     * 1、比较相邻的元素。如果后一个比前一大，就交换位置。
     * 2、最多比较的次数是：1+2+3+4+...+numbers.length-1
     *
     * @param numbers 需要排序的整型数组
     */
    public static int[] bubbleSort(int[] numbers) {
        //存放临时值
        int temp;
        int size = numbers.length;
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1 - i; j++) {
                //< 表示倒序，> 表示顺序
                if (numbers[j] < numbers[j + 1]) {
                    temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                }
            }
        }
        return numbers;
    }

    /**
     * 选择排序
     * 假设待排序数组：{1, 23, 22, 12, 8}
     * 从左往右以第一个为基准，后面的元素轮流从末尾向前进行比对是否小于基准值，如果小于，则替换它们（同理也可以是大于）
     * 第一轮排序，以"1"为基准，排序后：{1, 23, 22, 12, 8}
     * 第二轮排序，以"23"为基准，排序后：{1, 8, 22, 12, 23}
     * 第三轮排序，以"22"为基准，排序后：{1, 8, 12, 22, 23}
     * 第四轮排序，以"22"为基准，排序后：{1, 8, 12, 22, 23}
     *
     * @param numbers
     */
    public static int[] selectSort(int[] numbers) {
        int size = numbers.length;
        //存放比对过程中的临时值
        int temp;
        for (int i = 0; i < size - 1; i++) {
            //k 位置表示临时的最小/大值
            int k = i;
            for (int j = size - 1; j > i; j--) {
                if (numbers[j] < numbers[k]) {
                    k = j;
                }
            }
            if (i != k) {
                temp = numbers[i];
                numbers[i] = numbers[k];
                numbers[k] = temp;
            }
        }
        return numbers;
    }

}

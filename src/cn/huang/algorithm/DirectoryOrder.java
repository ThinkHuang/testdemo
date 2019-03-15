package algorithm;

import java.util.Arrays;

/**
 * 实现字典序算法
 * 最近换位数的三个步骤：
 * 1、从后向前查看逆序区域，找到逆序区域的前一位，也就是数字置换的边界
 * 2、把逆序区域的前一位和逆序区域中刚刚大于它的数字交换位置。
 * 3、把原来的逆序区域转为顺序
 * 例如
 * 输入12345，返回12354
 * 输入12354，返回12435
 * 输入12435，返回12453
 * @author huangyejun
 *
 */
public class DirectoryOrder
{
    public static void main(String[] args)
    {
        int [] numbers = {1, 2, 3, 4, 5};
            
        for(int i=0; i<10;i++){
                numbers = findNearestNumber(numbers);
                outputNumbers(numbers);
        }
    }
    
    private static int[] findNearestNumber(int[] numbers)
    {
        int length = numbers.length;
        // 数组复制，避免污染原数组
        int[] copyNumbers = Arrays.copyOf(numbers, numbers.length);
        // 找到临界点
        int index = findTransferPoint(copyNumbers);
        if(index == 0)
        {
            // 现有的顺序已经是最大的数字，不能进行转换了
            return numbers;
        }
        
        if(index == length - 1)
        {
            // 直接将最后两个元素进行位置交换
            int temp = 0;
            temp = numbers[length - 2];
            numbers[length - 2] = numbers[length - 1];
            numbers[length - 1] = temp;
            return numbers;
        }
        
        // 在后面的逆序中找到刚好大于要交换的数据并进行交换
        copyNumbers = exchangeHead(copyNumbers, index);
        
        // 将交换后的数字正序
        copyNumbers = reverse(copyNumbers, index);
        
        return copyNumbers;
    }
    
    /**
     * 将指定序列后的数组正序排列
     * @param copyNumbers
     * @param index
     */
    private static int[] reverse(int[] copyNumbers, int index)
    {
        for (int i = index, j = copyNumbers.length - 1; i < j; i++, j--)
        {
            int temp = copyNumbers[i];
            copyNumbers[i] = copyNumbers[j];
            copyNumbers[j] = temp;
        }
        return copyNumbers;
    }

    private static int[] exchangeHead(int[] copyNumbers, int index)
    {
        int head = copyNumbers[index - 1];
        for (int i = copyNumbers.length - 1; i > 0; i--)
        {
            if(head < copyNumbers[i]){// 需要倒序查询
                copyNumbers[index - 1] = copyNumbers[i];
                copyNumbers[i] = head;
                break;
            }
        }
        return copyNumbers;
    }

    /**
     * 找到逆序边界，以12354为例，从3开始后面的数字就是逆序的了
     * @param numbers
     * @return
     */
    private static int findTransferPoint(int[] numbers)
    {
        for (int i = numbers.length - 1; i > 0 ; i--)
        {
            if(numbers[i] > numbers[i-1])
            {
                return i;
            }
        }
        // 如果不存在逆序，就直接返回数组的长度
        return 0;
    }
    
  //输出数组
    private static void outputNumbers(int[] numbers)
    {
        for (int i : numbers)
        {
            System.out.print(i);
        }
        System.out.println();
    }
}

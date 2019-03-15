package other;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;

import org.junit.Test;
/**
 * 这里面需要解决的问题：
 * 最大的问题就是随机数对钱的分配问题：
 * 	这下面涉及到的几个小问题：
 * 		1、小数点的精度问题，--解决思路：能不能产生的随机数只保留两位数字，因为精度问题就是因为产生的随机数是多位小数的。
 * 		2、总数不等于输入的问题--这个问题其实和上面的总结为一点。
 * 		3、金额分配最后为负数的情况。--首先要处理的问题。
 * 		4、随机数为0的情况。
 * 另外一个问题就是对产生随机数进行优化。因为感觉前面的总是太大。--解决思路:利用产生的金额随机数和另外一个运气随机数相乘得到新的随机数，就是抢到的红包。
 * @author huangyejun
 *
 */
public class HongBaoImatation {
	@Test
	public void fun1(){
		System.out.print("please input number of HongBao:");
		Scanner sc1 = new Scanner(System.in);
		int num = sc1.nextInt();//后续还要添加校验
		System.out.print("please input money:");
		Scanner sc2 = new Scanner(System.in);
		double money = sc2.nextFloat();
		double[] bodys = new double[num];
		double RemainMoney = money;
		//前n-1个people的总钱数
		double totalmoney = 0.0;
		//金额随机数
		Random mrandom = new Random();
		//运气随机数
		Random yrandom = new Random();
		bodys[0] = 0.0;
		for(int i = 0; i < num - 1; i++){
			if(i >= 1 && i < num){
				RemainMoney = RemainMoney - bodys[i-1];
			}
			//下面的验证
			if(RemainMoney <= 0){
				System.out.println("下手慢了,红包已被抢完了");
				break;
			}
			//每个人得到的钱
			/*
			 * 存入bodys[i]中的值可能偏大或者偏小一点，但是，不管你是偏大还是偏小，经过下面的转换后，所有钱的总数将不会在出错了。
			 */
			bodys[i] =  mrandom.nextFloat() * (RemainMoney) * yrandom.nextFloat();
			totalmoney += Double.valueOf(new DecimalFormat("0.00").format(bodys[i]));
			if(bodys[i] <= 0){
				System.out.println("下手慢了,红包已被抢完了");
				break;
			}
			System.out.println(new DecimalFormat("0.00").format(bodys[i]));
		}
		//这里输出最后一个人抢到的红包。
		System.out.println(new DecimalFormat("0.00").format(money - totalmoney));
	}
}



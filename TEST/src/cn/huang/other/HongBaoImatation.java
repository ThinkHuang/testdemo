package cn.huang.other;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;

import org.junit.Test;
/**
 * ��������Ҫ��������⣺
 * ������������������Ǯ�ķ������⣺
 * 	�������漰���ļ���С���⣺
 * 		1��С����ľ������⣬--���˼·���ܲ��ܲ����������ֻ������λ���֣���Ϊ�������������Ϊ������������Ƕ�λС���ġ�
 * 		2���������������������--���������ʵ��������ܽ�Ϊһ�㡣
 * 		3�����������Ϊ�����������--����Ҫ��������⡣
 * 		4�������Ϊ0�������
 * ����һ��������ǶԲ�������������Ż�����Ϊ�о�ǰ�������̫��--���˼·:���ò����Ľ�������������һ�������������˵õ��µ�����������������ĺ����
 * @author huangyejun
 *
 */
public class HongBaoImatation {
	@Test
	public void fun1(){
		System.out.print("please input number of HongBao:");
		Scanner sc1 = new Scanner(System.in);
		int num = sc1.nextInt();//������Ҫ���У��
		System.out.print("please input money:");
		Scanner sc2 = new Scanner(System.in);
		double money = sc2.nextFloat();
		double[] bodys = new double[num];
		double RemainMoney = money;
		//ǰn-1��people����Ǯ��
		double totalmoney = 0.0;
		//��������
		Random mrandom = new Random();
		//���������
		Random yrandom = new Random();
		bodys[0] = 0.0;
		for(int i = 0; i < num - 1; i++){
			if(i >= 1 && i < num){
				RemainMoney = RemainMoney - bodys[i-1];
			}
			//�������֤
			if(RemainMoney <= 0){
				System.out.println("��������,����ѱ�������");
				break;
			}
			//ÿ���˵õ���Ǯ
			/*
			 * ����bodys[i]�е�ֵ����ƫ�����ƫСһ�㣬���ǣ���������ƫ����ƫС�����������ת��������Ǯ�������������ڳ����ˡ�
			 */
			bodys[i] =  mrandom.nextFloat() * (RemainMoney) * yrandom.nextFloat();
			totalmoney += Double.valueOf(new DecimalFormat("0.00").format(bodys[i]));
			if(bodys[i] <= 0){
				System.out.println("��������,����ѱ�������");
				break;
			}
			System.out.println(new DecimalFormat("0.00").format(bodys[i]));
		}
		//����������һ���������ĺ����
		System.out.println(new DecimalFormat("0.00").format(money - totalmoney));
	}
}



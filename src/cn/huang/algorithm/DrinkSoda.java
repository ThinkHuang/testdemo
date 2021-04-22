package algorithm;

import java.util.Scanner;

public class DrinkSoda {
    
    public static void main(String[] args) {
        int divisor = 3;
        int total = 0;
        while(true) {
            Scanner in = new Scanner(System.in);
            int num = in.nextInt();
            if (num <= 0) {
                break;
            }
            // 余数
            int remainder = num % divisor;
            // 剩余的可以兑换的新的可乐
            int quotient = num / divisor;
            while(quotient > 0) {
                if (remainder < 2 && quotient == 0) {
                    System.out.println(total);
                    break;
                }
                total += quotient;
            }
        }
    }
}

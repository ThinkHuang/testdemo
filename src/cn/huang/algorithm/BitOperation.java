package algorithm;

public class BitOperation {
    
    public static void main(String[] args) {
        System.out.println(isOdd(3));
    }
    
    public static boolean isOdd(int i) {
        return (i & 1) == 1;
    }
}

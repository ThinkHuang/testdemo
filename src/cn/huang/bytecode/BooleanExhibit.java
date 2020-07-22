package bytecode;

public class BooleanExhibit {
    
//    public static void main(String[] args) {
//        int a = 3;
//        boolean result = (a & 1) == 1;
//        System.out.println(result);
//    }
    
    private int count = 0;

    public static void main(String[] args) throws Exception {
        BooleanExhibit t = new BooleanExhibit();
        int sum = 0;
        for (int i = 0; i < 1000000; i++) {
            sum += t.m();
        }
        System.out.println(sum);
        int a = 3;
        boolean result = (a & 1) == 1;
        System.out.println(result);
    }

    public int m() {
        int i = count;
        i = m2(i);
        i += count;
        i *= count;
        i++;
        return i;
    }

    public int m2(int i) {
        if (i % 10 == 0) {
            i += 1;
        } else if (i % 10 == 1) {
            i += 2;
        } else if (i % 10 == 2) {
            i += 3;
        }
        return i;
    }
}

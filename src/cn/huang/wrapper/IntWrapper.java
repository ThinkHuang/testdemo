package wrapper;

public class IntWrapper {
    
    /**
     * Integer包装类会缓存-128~127的数值型包装对象
     * @param args
     */
    public static void main(String[] args) {
        Integer i = 128;
        int j = 128;
        System.out.println(i == j);
    }
}

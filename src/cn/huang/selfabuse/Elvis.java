package selfabuse;

public class Elvis {
    
    private static final Boolean LIVING = true;
    
    public static final Elvis elvis = new Elvis();
    
    private Elvis() {}
    
    
    /**
     * 这里有两点需要注意：
     * 1、在类的静态变量中，初始化顺序和定义顺序有关。
     * 2、静态变量的显示初始化是在初始化阶段(也就是触发类构造器<cinit>)。
     * 3、包装类型的静态变量初始化会放到实例变量之后进行。
     * 4、类的初始化会在java虚拟机规定的6种情况下才会进行。
     * 5、final修饰的static变量和单独使用static修饰的字段处理不一样，final修饰是会被jvm当做常量在编译阶段就完成了初始化。但这仅限于String和原始类型(其包装类型不是这样的)，
     * 通过查看class文件，发现jvm使用了自动装箱，将基本类型包装成了对应的包装类型，其实质还是方法引用。
     */
    private final Boolean alive = LIVING;
    
    public final Boolean lives() {
        return alive;
    }
    
    public static void main(String[] args) {
        System.out.print(elvis.lives() ? "Hound Dog" : "Heartbreak Hotal");
    }
}
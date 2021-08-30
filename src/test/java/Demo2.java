/**
 * @ClassName Demo2
 * @Description TODO
 * @Author Neal
 * @Date 2021/8/19 14:14
 * @Version 1.0
 **/

public class Demo2 {
    {
        System.out.println("11");
    }
    static {
        System.out.println("14");
    }
    Demo2(){
        System.out.println("17");
    }
    {
        System.out.println("20");
    }

    public static void main(String[] args) {
        Demo2 demo=new Demo2();
        Demo2 demo2=new Demo2();

    }
}

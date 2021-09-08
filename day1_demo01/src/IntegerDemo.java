public class IntegerDemo {
    public static void main(String[] args) {
        System.out.println(Integer.toHexString(60));
        System.out.println(Integer.parseInt("3c",16));
        Integer x=new Integer("123");
        Integer y=new Integer(123);
        System.out.println(x==y);
        Integer xs=128;
        Integer ys=128;
        System.out.println(xs==ys);
        Integer a=127;
        Integer b=127;
        System.out.println(a==b );
    }
}

import java.lang.reflect.Method;

public class OtherDemo {static{
    System.out.println("主函数的静态代码块执行");
}
    public static void main(String[] args){
        for (int i = 0; i < 5; i++) {
            for (int j = i+1; j < 5; j++) {
                System.out.print(' ');
            }
            for (int j = 0; j <=i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println(getResult(5));
        show(4,5);
        int [] arr ={1,3,5,7,6,8,52};
        System.out.println(getMax(arr));
        bubbleSort(arr);
        showArr(arr);
        System.out.println("查找："+halfSearch_2(arr,60));
        toHex(60);
        trans(60,7,3);
        new Car().run();
        Person p=new Person(25);
        System.out.println(p.name);
        System.out.println(p.compare(new Person(15)));
        System.out.println(Person.name);
        StaticCode.show();
        Single s=Single.getInstance();
        Single s1=Single.getInstance();
        System.out.println(s);
        System.out.println(s1);
        new Single2();
        new AbsCode().show();
        new Test().getTime();
        new InterfaceDome().showAge();
        System.out.println(InterfaceDome.AGE);
        System.out.println(Inter.AGE);
        Wangwu wangwu = new Wangwu();
        wangwu.play();
        wangwu.java();
        AbsDame absDame = new AbsCode();
        absDame.show();
        absDame.hah();
        System.out.println(absDame.name);
        Animal animal = new Cat();//类型提升（向上转型） 只能调用父类成员
        animal.eat();
        Cat c =(Cat)animal;//强制转换（向下转型）//可以调用子类成员
        c.eat();
        c.zls();
        DoSome some=new DoSome();//多态的应用
        some.doSome(new Cat());
        Animal a = new Cat();
        System.out.println(a.age);
        Cat ca =(Cat)a;
        System.out.println(ca.age);
        Sc sc = new B();
        sc.mh1("卖衣服");
        sc.mh2("卖文具");
        Demo d1 =new Demo(5);
        Demo d2 =new Demo(4);
        System.out.println(d1.equals(d2));
        Class ass = d1.getClass();
        Method[] m = ass.getMethods();
        for (int i = 0; i < m.length; i++) {
            System.out.println(m[i]);
        }
        Wbl.Nbl nbl = new Wbl().new Nbl();
        nbl.show();
        new Wbl().show();
        new Outer.Inner().function();//外部类访问静态内部类成员
        //Outer.Inner.function(); 外部类访问静态内部类中的静态成员
    }
    public static void showArr(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }
    }
    //函数中可以调用函数，不能定义函数
    public static int getResult(int num){
        return  num*3+5;
    }
    public static int getMax(int a, int b){
        return (a>b)?a:b;
    }
    public static void show(int x,int y){
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
    public static int getMax(int[] arr){
        int max=arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (max<arr[i])
                max=arr[i];
        }
        return max;
    }
    public static void selectCort(int[] arr){
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = i+1; j <arr.length ; j++) {
                if (arr[i]>arr[j]){
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }
    public static void bubbleSort(int[] arr){
        //相邻比较
        for (int i = 0; i < arr.length-1; i++) {//最后一个数字不比较 控制循环次数
            for (int j = 0; j < arr.length-i-1; j++) {//每循环一次就会得出一个最值，就少遍历一次
                if (arr[j]>arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }

    public static int halfSearch_2(int[] arr, int key){
        int min,max,mid;
        min=0;
        max=arr.length-1;
        while (min<=max){
            mid=(min+max)/2;
            if (key>arr[mid])
                min=mid+1;
            else if (key<arr[mid])
                max=mid-1;
            else
                return mid;
        }
        return -1;
    }
    //十进制转二进制
    public static void toBin(int num){
        while (num>0){
            System.out.println(num%2);
            num=num/2;
            //int x=0; x=x+2;(局部变量的应用，形参也是个局部变量)
        }
    }
    //十进制转十六进制
    public static void toHex(int num){
        for (int i = 0; i < 8; i++) {
            int temp =num & 15;
            if (temp>9)
                System.out.println((char)(temp-10+'A'));
            else
                System.out.println(temp);
            num=num>>>4;
        }
    }
//    查表法，十进制>>>二进制/十六进制/八进制（&7/右移3）
    public static void trans(int num,int base,int offset){
        char[] chs={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        char[] arr=new char[32];
        int pos=arr.length;
        while (num!=0){
            int temp =num & base;
            arr[--pos]=chs[temp];
            num = num>>>offset;
        }
        for (int i = pos; i < arr.length; i++) {
            System.out.print(arr[i]);
        }
    }
}
class Car{
    String color="red";
    private int num=4;
    void run(){
        System.out.println("runrun"+color+"df"+num);
    }
}
//构造函数
class Person{
    static String name="youyong";
    private int age;
    void setAge(int age){
        this.age=age;
    }
    int getAge(){
        return this.age;
    }
    {
        System.out.println("Person code run");
    }
    Person(){
        System.out.println("person run");
    }
    Person(int age){
        this();
        setAge(age);
    }
    boolean compare(Person p){
        return this.age==p.age;
    }
}
class StaticCode{
    static {
        System.out.println("静态构造代码块执行");
    }
    {
        System.out.println("构造代码块执行");
    }
    StaticCode(){
        System.out.println("无参数的构造函数执行");
    }
    static void show(){
        System.out.println("静态show方法执行");
    }
}
//单例设计模式：保证了对象的唯一性
class Single{

    private Single(){}//私有化构造函数
    private static Single single = new Single();//私有化对象禁止外部访问
    public static Single getInstance(){//对外界提供接口，获取Single对象，并保证了对象的唯一性。
        return single;                 // 并且因为私有化了构造函数外面无法new对象所以提供静态方法获取对象
    }
}
class Single1{
    int age=28;
}
//final:变量--常量 方法--不可被重写  类--不可被继承
final class Single2 extends Single1{
    int age;
    Single2(){
        super();//隐式的调用父类的构造函数，默认调用父类无参的构造函数
        super.age=30;
        System.out.println(this.age+"这是子类的构造函数"+super.age);
    }
    final void show(){
        System.out.println(super.age);
    }
}
//抽象类必须要子类重写所有抽象方法才可以使用
abstract class AbsDame{
    String name="youyong";
    private int age;
    AbsDame(){
        age=20;
        System.out.println("抽象类的构造函数运行"+age);
    }
    abstract void show();
    void hah(){
        System.out.println("多态");
    }
}
class AbsCode extends AbsDame{
    int s =23;
    AbsCode(){
        System.out.println("抽象类的实现类"+s);
    }
    @Override
    void show() {
        System.out.println("继承抽象类的子类运行"+s);
    }
    void heihei(){
        System.out.println("实现");
    }
}
//模板设计模式
abstract class AbsTest{
    final void getTime(){
        double begin = System.currentTimeMillis();
        run();
        double end = System.currentTimeMillis();
        System.out.println("时间："+(end-begin));
    }
    abstract void run();
}
class Test extends AbsTest{
    @Override
    void run() {
        System.out.println("单例设计模式，暴露不确定部分，让子类进行重写");
    }
}
//接口 interface implements 实现
interface Inter{//接口中的变量为常量 方法为抽象方法 接口中的所有方法都是抽象方法
    public static final int AGE = 20;
    public abstract void showAge();
}
//类支持多实现 子类是需要重写一次同名的抽象方法（）但是不允许方法名相同，返回值类型不同的情况
interface Inter2 extends Inter{//接口与接口之间继承关系，并且允许多继承，但是不允许方法名相同，返回值类型不同的情况
    public abstract void showAge();
}
class Inter3{
    Inter3(){
        System.out.println("这是类的构造函数Inter3");
    }
    public void showAge(){
        System.out.println("这是类中的Inter");
    }
}
//子类实现接口后也会有接口中的成员
class InterfaceDome extends Inter3 implements Inter,Inter2{//创建的类需要实现接口 使用implements 重写接口中的所有抽象方法
    @Override
    public void showAge() {
        System.out.println("这是子类"+AGE);
    }
}
//继承的是基本功能，体系内的；接口是拓展功能，体系外的
abstract class Sporter{//运动员的类
    abstract void play()//是运动员就会有自己的专项 这就是体系的的，你只要是运动员就必须有专项
    ;
}
interface Study{//这是接口扩展类，你是运动员除了专项之外也可以有其他的东西，这就是进行扩展
    public abstract void java();
}
class Wangwu extends Sporter implements Study{
    @Override
    public void java() {
        System.out.println("学java");
    }

    @Override
    void play() {
        System.out.println("打篮球");
    }
}
abstract class Animal{
    int age = 20;
    abstract void eat();
    void sleep(){
        System.out.println("躺着睡");
    }
    static void show(){
        System.out.println("父类静态方法");
    }
}
class DoSome{//工具类
    public void doSome(Animal a){
        a.eat();
        a.sleep();
    }
}
class Cat extends Animal{
    int age=25;
    @Override
    void eat() {
        System.out.println("吃鱼");
    }

    void zls(){
        System.out.println("抓老鼠");
    }

    void sleep(){
        System.out.println("跪着睡");
    }
    static void show(){
        System.out.println("子类的静态方法");
    }
}
class Peg extends Animal{//多态的类型提升与强制转换

    @Override
    void eat() {
        System.out.println("吃骨头");
    }

    void kj(){
        System.out.println("看家");
    }
}
interface Sc{
    abstract void mh1(String w);
    abstract void mh2(String w);
}

class A implements Sc{
    @Override
    public void mh1(String w) {
        System.out.println(w);
    }

    @Override
    public void mh2(String w) {
        System.out.println(w);
    }
}
class B implements Sc{
    @Override
    public void mh1(String w) {
        System.out.println(w);
    }

    @Override
    public void mh2(String w) {
        System.out.println(w);
    }
}
//多态的应用
class Demo{
    private int age;
    Demo(int age){
        this.age=age;
    }
    public boolean equals(Object obj){//重写object类中的equals方法 比较两个数字 超类中equals接收的是Object类
        Demo d = (Demo) obj;//而超类中没有定义age变量，所以要使用子类中的特有成员时，需要向下转型得到子类引用
        return this.age==d.age;
    }
}
//内部类
class Wbl{
    private int age = 6;
    class Nbl{
        String name = "heihei";
        int age = 8;
        void show(){
            System.out.println(age);
        }
    }
    void show(){
        Nbl nbl=new Nbl();
        System.out.println(age);
    }
}
class Outer{
    private static int x = 3;
    static class Inner{//静态内部类
        void function(){
            System.out.println("inner:"+x);
        }
    }
    static class Inner2{
         void show(){
            System.out.println("静态内部类方法");
        }
        static void show2(){//内部类中有静态方法，该内部类也必须是静态
            System.out.println("静态方法");
        }
    }
    static void show(){
        new Inner2().show();//外部类静态方法访问内部类，该内部类不许是静态
    }
}
/*
事物内部还有事物 该事物用内部类来描述
* class Body{
*
    private class XinZang{
*
*   }

    public void show(){
        new XinZang();
    }
* }
* */
//静态是成员修饰符 不可以用在局部
//内部类定义在局部时 不可以被成员修饰符修饰，可以直接访问外部类成员
//但不能访问局部中的成员，除非被final修饰
class Outer2{
    int x = 3;
    void method(final int a){//局部内部类只能访问局部中被final修饰的变量
        final int y = 4;
        class Inner{
            void function() {
                System.out.println(a);
            }
         }
         new Inner().function();
    }
}
class InnerClassDemo3{
    public static void main(String[] args) {
        Outer2 ou = new Outer2();
        ou.method(7);
        ou.method(8);
        new Outer3().function();
    }
}


abstract class AbsDemo3{
    abstract void show();
}
//匿名内部类 前提时类必须继承一个类 或者 实现一个接口
class Outer3{
    int x = 3;
//    class Inner extends AbsDame{
//        @Override
//        void show() {
//            System.out.println("show:"+x);
//        }
//    }
    void function(){
        //new Inner().show();
        new AbsDemo3(){//匿名内部类创建  匿名所以没有子类对象new父类
            //重写父类方法体
            @Override
            void show() {
                System.out.println("x="+x);
            }
        }.show();//对象调用方法
        AbsDemo3 a3 = new AbsDemo3(){//匿名内部类就是一个子类对象，现在给匿名内部类创建应用，就是创建父类引用（多态）
            //父类应用就只能调用父类成员了
            @Override
            void show() {
                System.out.println("x="+x);
            }
        };
    }
}
interface InterFace{
    void method();
}
class TestFace{
    //部族代码，通过匿名内部类
    public static InterFace function(){
        return new InterFace(){
            @Override
            public void method() {
                System.out.println("method run");
            }
        };

    }
}
class InnerClassTest{
    public static void main(String[] args) {
        TestFace.function().method();
        //.method()：function()这个方法返回结果是一个对象，
        // 而且返回对象是InterFace的，因为只有该对象可以调用method()
        show(new InterFace() {
            @Override
            public void method() {
                System.out.println("method show run");
            }
        });
    }
    public static void show(InterFace in){
        in.method();
    }
}
class InnerTest{
    public static void main(String[] args) {

        new Object(){
            public void function(){

            }
        }.function();
        DemoE demoE=new DemoE();
//        try {
//            ExceptionDemo.div();
//
//            System.out.println("运算结果："+demoE.div(4,-1));
//        }catch (FuShuException e) {
//            //e.printStackTrace();
//            System.out.println(e.toString());
//            System.out.println("出现负数:"+e.getNum());
//        }catch (Exception e){//catch块尽量捕捉抛出的对应异常
//            System.out.println(e.toString());
//        }
        demoE.div1(4,-9);
    }
}
//异常机制 与 throws关键字
//多异常的处理  函数在遇到异常后会停止不在执行后面代码
//程序在抛异常时 声明几个异常就声明几个catch块
class ExceptionDemo{
    static int[] arr={1,2,3};
    static void div() throws ArithmeticException,ArrayIndexOutOfBoundsException
    {
        System.out.println(arr[0]);
        System.out.println(5/2);
    }
}
//自定义异常
class FuShuException extends Exception{
    private int num;
    FuShuException(){ }
    FuShuException(String msg,int num){
        super(msg);//父类已经完成的功能，子类只需要在构造时将数据通过super语句传递给父类，子类就可以在直接调用方法获取异常信息
        this.num=num;
    }
    public int getNum(){
        return num;
    }
}
//自定义运行时异常
class FuShuRuntime extends RuntimeException{
    FuShuRuntime(String msg){
        super(msg);
    }
}
class DemoE{
    int div(int a,int b)throws FuShuException
    {
        if (b<0)
            //函数内部throw抛出自定义异常后就必须要处理
            throw new FuShuException("出现负数了 / by fushu",b);//手动通过throw抛出自定义异常
        return a/b;
    }
    int div1(int a,int b){
        if (b<0)
            throw new FuShuRuntime("出现负数了");
        else
            return a/b;
    }
}
//讲课的异常练习
class LanPingException extends Exception{
    LanPingException(String msg){
        super(msg);
    }
}
class MaoYanException extends Exception{
    MaoYanException(String msg){
        super(msg);
    }
}
class NoPlanException extends Exception{
    NoPlanException(String msg){
        super(msg);
    }
}
class Computer{
    private int state = 3 ;
    void run() throws LanPingException,MaoYanException{
        if (state==2)
            throw new LanPingException("蓝屏了");
        if (state==3)
            throw new MaoYanException("冒烟了");
        System.out.println("电脑运行");
    }
    void reset(){
        state=1;
        System.out.println("电脑重启");
    }
}
class Teacher{
    private String name;
    private Computer computer;
    Teacher(String name){
        this.name=name;
        computer=new Computer();
    }
    void prelect() throws NoPlanException
    {
        try {
            computer.run();
        } catch (LanPingException e) {
            computer.reset();
        } catch (MaoYanException e) {
            test();
            //当捕捉到异常 抛出应该抛出调用者能解决的异常
            throw new NoPlanException("课时无法继续"+e.getMessage());
        }
        System.out.println("讲课");
    }
    void test(){
        System.out.println("做练习");
    }

}
class ExceptionTest{
    public static void main(String[] args) {
        Teacher t = new Teacher("毕老师");
        try {
            t.prelect();
        } catch (NoPlanException e) {
            System.out.println(e.getMessage());
            System.out.println("换电脑");
        }
    }
}
//异常在覆盖时，子类只能抛出父类的异常或者父类异常的子类，不能抛出新的异常
//如果父类没有抛出异常，子类出现异常值能在内部处理 绝对不能抛出
class FU{
    void show() throws LanPingException, MaoYanException {
        throw new LanPingException("蓝屏了");
    }
}
class Zi extends FU{
    void show() throws MaoYanException{
        throw new MaoYanException("冒烟了");
    }
}
class TestE{
    public static void main(String[] args) {
        function(new Zi());
    }
    static void function(FU f){
        try {
            f.show();
        } catch (LanPingException | MaoYanException e) {
            e.printStackTrace();
        }
    }
}
//异常的图像求面积 出现非法数值
class NoValueException extends RuntimeException{
    NoValueException(String msg){
        super(msg);
    }
}
interface Shape{
    void getArea();
}
class Res implements Shape{
    private int len,wid;
    Res(int len,int wid){
        if (len<=0 || wid<=0)
            throw new NoValueException("出现非法值");
        this.len=len;
        this.wid=wid;
    }

    @Override
    public void getArea() {
        System.out.println(len*wid);
    }
}
class Circle implements Shape{
    private int r;
    public static final double Pi=3.14;
    Circle(int r){
        if (r<=0)
            throw new NoValueException("非法值");
        this.r=r;
    }

    @Override
    public void getArea() {
        System.out.println(r*r*Pi);
    }
}
class ExceptionTest1{
    public static void main(String[] args) {

//        Res r = new Res(-3,4);
//        r.getArea();
        //因为传入的数值导致错误，所以没必要对其进行捕捉，直接=使用运行时异常在传入参数错误时让程序停掉
        Circle c = new Circle(-9);
        c.getArea();
        System.out.println("over");

    }
}
class Ff{
    int age;
    void show(){
        System.out.println("父类");
    }
}
class zz extends Ff{

    zz(){
        //子类中没有时就会在父类中找

    }
    void show(){
        System.out.println("子类");

    }
}
//在多态中成员变量的的应用都是看 应用型变量的所属类
class Tt{
    public static void main(String[] args) {
        Ff f = new zz();
        System.out.println(f.age);
        zz z = new zz();
        System.out.println(z.age);
        z.show();
    }
}
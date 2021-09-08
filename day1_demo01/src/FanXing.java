import java.util.*;

public class FanXing {
    public static void main(String[] args) {
        ArrayList<String> a1=new ArrayList<String>();
        a1.add("java");
        a1.add("java1");
        a1.add("java2");

        Iterator<String> it=a1.iterator();
        while (it.hasNext()){
            String s = it.next();
            System.out.println(s+":"+s.length());
        }
    }
}
//泛型例子
class GenericDemo2{
    public static void main(String[] args) {
        TreeSet<String> ts=new TreeSet<String>(new LenComp());//传入比较器对象
        ts.add("absk");
        ts.add("cvbsksdg");
        ts.add("hrsks");
        Iterator it=ts.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }
        TreeSet<Utils> ts2=new TreeSet<>();

    }
}
//给比较器对象指定泛型类型
class LenComp implements Comparator <String> {
//因为Comparator是个泛型接口可以在实现时指定泛型类型
//这样在复写的方法中的参数就可以指定为泛型的类型，不用强转了
    @Override
    public int compare(String o1, String o2) {
        int num=new Integer(o1.length()).compareTo(o2.length());
        if (num==0)
            return o1.compareTo(o2);
        return num;
    }
}
//自定义泛型
//当类中操作的引用数据类型不确定时 使用泛型类
class Utils<T>{//用<>标识

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    private T t;

}
class GenericDemo3{
    public static void main(String[] args) {
        Utils<Integer> utils=new Utils<>();//泛型类只能定义引用数据类型，基本数据类型不能定义
        utils.setT(222);//这里的int基本数据类型会被自动装修成Integer类
        System.out.println(utils.getT());
        FxDemo<String> fx=new FxDemo<String>();
        fx.show("999");
        fx.show(212);
        fx.print(true);
        fx.system("543");//这个不是泛型方法的就随着泛型类定义的类型走
        FxDemo.method(1.354);
        //接口为泛型，实现类也不明确类型时，就由调用者指定泛型类型
        //InterFIml<String>  interFIml=new InterFIml<String>();
       // interFIml.show("游勇");
    }
}
//泛型方法
//让不同方法可以接受不同类型，类型不确定时 定义泛型方法
class FxDemo<T>{
    //静态只能定义泛型方法，不能访问类上定义的泛型
    public static <T> void method(T t){
        System.out.println(t);
    }
    public <T> void show(T t){
        System.out.println("Show:"+t);
    }
    public <T> void print(T t){
        System.out.println("Print:"+t);
    }
    public void system(T t){
        System.out.println("System："+t);
    }
}
//泛型接口
interface InterF<T>{
    void show(T t);
}
//如果子类在实现泛型接口时，也不知道是什么类型，那么子类也定义成泛型类
class InterFIml implements InterF<String>{
//如果子类在实现接口时明确了接口的泛型类型，那么接口中接受泛型的方法，也会变成所定义的类型

    @Override
    public void show(String s) {

    }
}
class GenerioDemo{
    public static void main(String[] args) {
        ArrayList<String> ls=new ArrayList<>();
        ls.add("asdb");
        ls.add("assfdsg");
        ls.add("12sdg");
        ArrayList<Integer> li=new ArrayList<>();
        li.add(123);
        li.add(1235);
        li.add(12333);
        printColl(ls);
        printColl(li);
        ArrayList<GenPerson> gp=new ArrayList<>();
        gp.add(new GenPerson("abc1"));
        gp.add(new GenPerson("abc2"));
        gp.add(new GenPerson("abc3"));
        pritn(gp);

        ArrayList<GenStudent> gs=new ArrayList<>();
        gp.add(new GenStudent("abc1"));
        gp.add(new GenStudent("abc2"));
        gp.add(new GenStudent("abc3"));
        //pritn(gs);虽然是继承关系但是不允许，因为集合一开始就定义了接受类型，而该类型的子类会有很多不同的类，所以会导致不管什么类型的子类都能存进存入的安全问题
        //容器两边的泛型类型要相同
    }
    static void pritn(ArrayList<GenPerson> al){
        Iterator<GenPerson> iterator=al.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next().getName());
        }
    }
    //在不明确会传入什么泛型类型时 用?号作为占位符，就可以接受各种类型
    static void printColl(ArrayList<?> al){
        Iterator<?> it=al.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }
    }
}
class GenPerson{
    private String name;
    GenPerson(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }
}
class GenStudent extends GenPerson{
    GenStudent(String name){
        super(name);
    }
}
class AnimalB{}
class Bird extends AnimalB{
}
class Parrot extends Bird{
}
class ToolB{
    public static void main(String[] args) {
        ArrayList<Bird> ll=new ArrayList<>();
        addList(ll);
    }
    public static void addList(ArrayList<? super Bird> list){

    }
}
class GenWorker extends GenPerson{
    GenWorker(String name) {
        super(name);
    }
}
class CompMain{
    public static void main(String[] args) {
        //
        TreeSet<GenStudent> ts=new TreeSet<>(new Comp());//传入排序接口
        ts.add(new GenStudent("abc01"));
        ts.add(new GenStudent("abc06"));
        ts.add(new GenStudent("abc08"));
        ts.add(new GenStudent("abc02"));
        Iterator<GenStudent> it=ts.iterator();
        while (it.hasNext()){
            System.out.println(it.next().getName());
        }
        //因为TreeSet集合存储的自定义对象元素，所以让对象具有比较性
        TreeSet<GenWorker> tw=new TreeSet<>(new Comp());
        tw.add(new GenWorker("bbb--002"));
        tw.add(new GenWorker("bbb--012"));
        tw.add(new GenWorker("bbb--006"));
        tw.add(new GenWorker("bbb--000"));
        Iterator<GenWorker> it1=tw.iterator();
        while (it1.hasNext()){
            System.out.println(it1.next().getName());
        }
    }
}
//TreeSet集合的Comparator方法是有一个泛型的构造函数，<? super E> ，可以接收一个E或者E的父类
class Comp implements Comparator<GenPerson>{
    //这里泛型传入了Person类，而Student和Worker是Person的子类所以都可以用比较器

    @Override//泛型接口定义了类型后，方法中的泛型也擦除为了指定类型
    public int compare(GenPerson o1, GenPerson o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
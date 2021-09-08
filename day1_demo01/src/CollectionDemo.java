import java.util.*;

public class CollectionDemo {
    public static void main(String[] args) {
        //创建一个集合容器，使用Collaction接口的子类ArrayList
        ArrayList a1=new ArrayList();
        //1。添加元素
        a1.add("java01");
        a1.add("java02");
        a1.add("java03");
        //2.获取个数集合长度
        System.out.println(a1.size());
        //删除元素
        a1.remove("java02");
        System.out.println(a1);
        //清空集合
        //a1.clear();

        System.out.println(a1.contains("java"));//是否包含
        System.out.println(a1.isEmpty());//是否为空
        ArrayList a2=new ArrayList();
        a2.add("java01");
        a2.add("java06");
        a2.add("java05");
        a1.retainAll(a2);//取交集
        System.out.println(a1);
        a1.removeAll(a2);
        System.out.println(a1);//去掉相同对象
        method_get();
    }
    //获取方式 使用迭代
    public static void method_get(){
        ArrayList a1=new ArrayList();
        a1.add("java01");
        a1.add("java02");
        a1.add("java03");
        Iterator it=a1.iterator();//使用iterator方法获取迭代器对象
        while (it.hasNext())//Iterator是向上抽取出的共性动作，以内部类形式定义集合内部
            System.out.println(it.next());
        //for循环写法创建局部的Iterator对象，节约内存
        for (Iterator i=a1.iterator();i.hasNext();){
            i.next();
        }
    }
}
//Collection---List / Set两大派系
//List:元素是有序的，元素可以重复，该集合体系有索引
//Set:元素无序，元素不可重复
class ListDemo{
    public static void main(String[] args) {
        //演示列表迭代器
        ArrayList li=new ArrayList();
        //添加
        li.add("java01");
        li.add("java02");
        li.add("java03");
        ListIterator lt=li.listIterator();
        //ListIterator的出现可以在对集合的遍历过程中进行增删查改
        while (lt.hasNext()){
            Object obj=lt.next();
            if (obj.equals("java02")){
                lt.set("java002");
                lt.remove();
                lt.add("java008");
            }
        }
        System.out.println(li);
        //方向遍历
        while (lt.hasPrevious()){
            System.out.println(lt.previous());
        }
//        System.out.println(li);
//        //在迭代过程中，准备添加或删除元素
//        Iterator it=li.iterator();
//        while (it.hasNext()){
//            Object obj=it.next();
//            if (obj.equals("java02"))
//                it.remove();//从集合删除
//                //li.add("java08");在迭代过程中不能使用集合引用对集合进行操作
//        }
//        System.out.println(li);
    }
    static void listMethod(){
        ArrayList li=new ArrayList();
        //添加
        li.add("java01");
        li.add("java02");
        li.add("java03");

        //指定位置添加
        li.add( 1,"java");

        //删除
        li.remove(2);

        //修改
        li.set(0,"javaStart");

        //通过叫角标查询
        System.out.println(li.get(0));
        //获取所有
        for (int i = 0; i < li.size(); i++) {
            System.out.println(li.get(i));
        }

        //indexOf获取对象位置
        System.out.println(li.indexOf("java02"));
        List sub=li.subList(1,3);//根据角标获取对象
    }
}
//LinkedList 链接列表
//addFirst()/addLast() 在列表开头插入元素/尾部追加元素
//还有getF/L removeF/L
//remove会返回删除掉的指定元素  get只会返回元素不会删除
//removeF/L getF/L如果为空时会抛出异常，
//所以使用pollFirst/Last() 与 peekF/L如果为空不会抛异常，返回null
//offerF/L头部/尾部添加元素
class LinkedListDemo{
    public static void main(String[] args) {
        LinkedList ll=new LinkedList();
        ll.addFirst("java01");
        ll.addFirst("java02");
        ll.addFirst("java03");
        ll.addFirst("java04");
        System.out.println(ll);
        System.out.println(ll.getFirst());
        System.out.println(ll.getLast());
        System.out.println(ll.removeFirst());
        System.out.println(ll);
        System.out.println(ll.size());
        System.out.println(ll.remove());//默认删除掉第一个
        System.out.println(ll.pollFirst());//获取并移除第一个元素，如果为空就返回null不抛异常
        System.out.println(ll);
        System.out.println(ll.peekFirst());
        System.out.println(ll);
        ll.offerFirst("java008");
    }
}
//使用LinkedList模拟堆栈(先进后出)--杯子/队列(先进先出)--水管的数据结构
//为什么封装？ 将原有的功能封装进自己的描述中，并对外暴露接口
class LikedDemo{
    public static void main(String[] args) {
        DuiLie duiLie=new DuiLie();
        duiLie.myadd("java01");
        duiLie.myadd("java02");
        duiLie.myadd("java03");
    }
}
class DuiLie{
    private  LinkedList link;
    DuiLie(){
        link=new LinkedList();
    }
    void myadd(Object obj){
        link.offerFirst(obj);
    }
    Object myGet(){
        return link.removeLast();
    }
    boolean linkeNull(){
        return link.isEmpty();
    }
}
//去除ArrayList中的重复元素
class ArrayTest{
    public static void main(String[] args) {
        ArrayList list=new ArrayList();
        list.add("java01");
        list.add("java02");
        list.add("java01");
        list.add("java04");
        list.add("java05");
        System.out.println(list);
        System.out.println(singleElement(list));
    }
    static ArrayList singleElement(ArrayList li){
        ArrayList list=new ArrayList();
        Iterator it=li.iterator();
        while (it.hasNext()){
            Object obj=it.next();
            if (!list.contains(obj))
                list.add(obj);
        }
        return list;
    }
}
//将自定义对象作为元素 存入ArrayList中，并去除重复对象
class PersonTest{
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
    public boolean equals(Object obj){//
        if (!(obj instanceof PersonTest))
            return false;
        PersonTest p = (PersonTest) obj;
        System.out.println(this.getClass().getName()+"+++++++"+p.getName());
        return this.name.equals(p.name) && this.age==p.age;
    }
    PersonTest(String name, int age){
        this.name=name;
        this.age=age;
    }

}
class ArrayListTest2{
    public static void main(String[] args) {
        ArrayList li=new ArrayList();
        li.add(new PersonTest("lisi",20));
        li.add("java01");
        li.add(new PersonTest("lisi1",21));
        li.add(new PersonTest("lisi2",22));
        li.add(new PersonTest("lisi3",23));

        li=singleElement(li);
        System.out.println(li);
        Iterator it=li.iterator();

//        while (it.hasNext()){
//           PersonTest p= (PersonTest) it.next();//add(Object obj);//Object obj=new PersonTest("lisi",20);
//           //在添加时会有一个向上转型的过程，所以需要调用元素内部特有方法时需要向下转型
//            System.out.println(p.getName()+"..."+p.getAge());
//        }
    }
    static ArrayList singleElement(ArrayList li){
        ArrayList list=new ArrayList();
        Iterator it=li.iterator();
        while (it.hasNext()){
            Object obj=it.next();
            if (!list.contains(obj))//contains是否包含该元素，就是调用该元素的equals的方法对list集合中所有的元素进行比较
                list.add(obj);
        }
        return list;
    }
}

import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

public class SetDemoTest {
    public static void main(String[] args) {
        HashSet hs=new HashSet();
//        hs.add("java01");
//        hs.add("java02");
//        hs.add("java03");
//        hs.add("java04");
//        Iterator it=hs.iterator();//获取set的迭代器对象
//        while (it.hasNext()){
//            System.out.println(it.next());
//        }
        //存入的是哈希值，如果存入的对象名称与年龄相同就应该是重复了不应该存入，
        //所以要复写hasCode与equals方法进行自定义判断
        hs.add(new PersonSet("zhangs",11));
        hs.add(new PersonSet("zhangs1",12));
        System.out.println(hs.add(new PersonSet("zhangs1",12)));
        hs.add(new PersonSet("zhangs1",12));
        hs.add(new PersonSet("zhangs2",13));
        hs.add(new PersonSet("zhangs3",14));
        System.out.println(hs);
        System.out.println(hs.contains(new PersonSet("zhangs",11)));
        hs.remove(new PersonSet("zhangs",11));
        System.out.println(hs
        );

    }
}
class PersonSet{
    private String name;
    private int age;
    PersonSet(String name,int age){
        this.name=name;
        this.age=age;
    }
    //当所描述的对象需要存入容器中时，尽量复写hasCode与equals方法，保证对象的唯一性
    public int hashCode(){//在存入hashSet集合时，会先调用hashcode方法获取hash值
        //hash值会作为标识存入哈希表中，如果hash值相同就在对对象进行equals判断是否相同
        //由此确保存入对象的唯一性
        return this.name.hashCode()+age+2;//加2是确定唯一，避免相同
    }
    //hash值相同时会调用equals方法经行比较
    public boolean equals(Object obj){
        if (!(obj instanceof PersonSet))
            return false;//如果类型不同就返回false，集合中没有此对象
        PersonSet p=(PersonSet) obj;
        return this.name.equals(p.name) && this.age==p.age;
    }
    //hashSet集合中hashCode与equals方法都是在添加时自动调用的
}
//TreeSet添加自定义对象，按年龄排序
//Compareto接口
//排序是主要条件相同时，一定要排序次要条件
class StudentSet{
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    StudentSet(String name, int age){
        this.name=name;
        this.age=age;
    }

    //接口方法，返回正整数是大于，0是等于，负整数是小于
//    @Override
//    public int compareTo(Object o) {
//        if (!(o instanceof StudentSet))//如果比较类型不同就不用比较 抛出异常
//            throw  new RuntimeException("不是学生类");//运行时异常，无需在函数上声明
//        StudentSet sd=(StudentSet) o;
//        //System.out.println(this.name+"......"+sd.name);
//        if (this.age>sd.getAge())
//            return 1;
//        if (this.age==sd.getAge())//排序次要条件
//            // 类已经实现Comparable接口可以直接条用comparato方法进行比较
//            return this.name.compareTo(sd.name);
//        return -1;
//    }
}
class TreeSetTest{
    public static void main(String[] args) {
        //给TreeSet集合指定比较器 实现了Comparator接口的比较器
        TreeSet ts=new TreeSet(new MyCompare());//TreeSet集合能够对添加的元素进行排序，所以要让自定义对象具有比较性
        //实现Compareto接口
        ts.add(new StudentSet("lisi02",10));
        ts.add(new StudentSet("lisi007",11));
        ts.add(new StudentSet("lisi009",12));
        ts.add(new StudentSet("lisi04",13));
        ts.add(new StudentSet("lisi05",13));
        ts.add(new StudentSet("lisi009",11));
        Iterator it=ts.iterator();
        while (it.hasNext()){
            StudentSet st=(StudentSet) it.next();
            System.out.println(st.getName()+"++++++++"+st.getAge());
        }
    }
}
//TreeSet的第二种排序方式
//让集合自身具有比较性
//当元素自身不具备比较性，或者具备的比较性不是所需的
//就让集合自身具备比较性，
//定义比较容器，将比较器对象作为参数传递给TreeSet集合构造函数
class MyCompare implements Comparator{

    @Override
    public int compare(Object o1, Object o2) {
        StudentSet s1=(StudentSet) o1;
        StudentSet s2=(StudentSet) o2;
        int num = s1.getName().compareTo(s2.getName());
        if (num==0){
            //首要元素相同时比较次要元素
            //应为Integer类具有comparTo方法所以使用int的包装类 直接调用该方法进行比较
            return new Integer(s1.getAge()).compareTo(new Integer(s2.getAge()));
        }
        return num;
    }
}
//练习 按字符串长度排序
class StingCom{
    public static void main(String[] args) {
        TreeSet ts=new TreeSet(new StringLengthComparator());
        ts.add("asdfs");
        ts.add("ass");
        ts.add("askhldfs");
        ts.add("asd");
        Iterator it=ts.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }
    }
}
class StringLengthComparator implements Comparator{

    @Override
    public int compare(Object o1, Object o2) {
        String s1=(String) o1;
        String s2=(String) o2;
//        if (s1.length()>s2.length())
//            return 1;
//        else if (s1.length()==s2.length())
//            return 0;
        //字符的长度也是一个int型数字，所以个使用int的包装类调用比较方法
        int num=new Integer(s1.length()).compareTo(s2.length());
        if (num==0)//判断次要条件
            return s1.compareTo(s2);
        return num;
    }
}
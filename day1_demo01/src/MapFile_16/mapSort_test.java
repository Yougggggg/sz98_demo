package MapFile_16;

import java.util.*;

//对学生对象的年龄进行排序
//数据以键值形式存储，所以用可以排序的Map集合： TreeMap
public class mapSort_test {
    public static void main(String[] args) {
        TreeMap<Student,String> tap=new TreeMap<>(new CompStudent());//传入自定义比较器
        //自定以了Student的的hashCode()与equals()方法，会根据对象传入的name与age是否相同来判断是否为同一元素
        tap.put(new Student("lisi1",21),"beijing");
        tap.put(new Student("lisi1",21),"tianjing");//相同key，tianjing后进来，就会覆盖掉前面的数据
        tap.put(new Student("lisi2",2),"shanghai");
        tap.put(new Student("lisi3",23),"shengzheng");
        tap.put(new Student("lisi4",24),"henan");

        //学生类中已经实现了Comparable接口，所以具有了比较性
        Set<Map.Entry<Student,String>> me=tap.entrySet();
        Iterator<Map.Entry<Student,String>> it1=me.iterator();
        while (it1.hasNext()){
            Map.Entry<Student,String> mm=it1.next();
            System.out.println(mm.getKey()+"....."+mm.getValue());

        }

        //现在需要按学生姓名进行排序，不去修改学生类代码，所以使用比较器方法：Comparator接口

    }
}
class CompStudent implements Comparator<Student>{

    @Override
    public int compare(Student o1, Student o2) {
        int num=o1.getName().compareTo(o2.getName());
        if (num==0)//比较次要条件
            return new Integer(o1.getAge()).compareTo(new Integer(o2.getAge()));
        //返回 0 就带表重复已存在，在map中就会将原有数据覆盖掉
         return num;
    }
}

package MapFile_16;

import java.util.*;

//描述学生
//定义map容器 学生为键 地址为值
public class map_test {
    public static void main(String[] args) {
        HashMap<Student,String> map=new HashMap<>();
        //自定以了Student的的hashCode()与equals()方法，会根据对象传入的name与age是否相同来判断是否为同一元素
        map.put(new Student("lisi1",21),"beijing");
        map.put(new Student("lisi1",21),"tianjing");
        map.put(new Student("lisi2",22),"shanghai");
        map.put(new Student("lisi3",23),"shengzheng");
        map.put(new Student("lisi4",24),"henan");

        //keySet取出
        Set<Student> set=map.keySet();
        Iterator<Student> it=set.iterator();
        while (it.hasNext()){
            Student st=it.next();
            System.out.println(st+"...."+map.get(st));
        }

        //entry取出
        Set<Map.Entry<Student,String>> me=map.entrySet();
        Iterator<Map.Entry<Student,String>> it1=me.iterator();
        while (it1.hasNext()){
            Map.Entry<Student,String> mm=it1.next();
            System.out.println(mm.getKey()+"....."+mm.getValue());
        }
    }
}
//存入以二叉树的数据结构的集合中时，需要让对象具有自然顺序
//TreeSet集合需要让元素具有比较性 ，实现Comparable接口

class Student implements Comparable<Student>{
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    //当对象会被多次创建并存入以hash表为数据而结构中集合时，
    //复写hashCode() 与 equals（）


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return age == student.age && Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode()+age*34;
    }

    @Override
    public int compareTo(Student o) {
        int num=new Integer(this.age).compareTo(new Integer(o.age));
        if (num==0)
            return this.name.compareTo(o.name);
        return num;
    }

    @Override
    public String  toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
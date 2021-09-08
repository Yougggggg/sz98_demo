package day21;

import java.io.Serializable;

public class Person implements Serializable {
    public static final long serialVersionUID = 42L;//自定义序列号
    String name;
    transient int age;//transient关机字修饰的成员无法被序列化
    static String country="cn";//静态成员无法被序列化
    public Person(String name, int age,String country) {//只能将堆内存中的数据序列化
        this.name = name;
        this.age = age;
        this.country=country;
    }
    public String toString(){
        return name+":"+age+":"+country;
    }
}

package day21;

import java.io.*;
import java.util.*;

/*
有五个学生，每个学生有3门课的成绩，
从键盘输入以上数据（包括姓名，三门课成绩），
输入的格式：如：zhagnsan，30，40，60计算出总成绩，
并把学生的信息和计算出的总分数高低顺序存放在磁盘文件"stud.txt"中。

1，描述学生对象。
2，定义一个可操作学生对象的工具类。

思想：
1，通过获取键盘录入一行数据，并将该行中的信息取出封装成学生对象。
2，因为学生有很多，那么就需要存储，使用到集合。因为要对学生的总分排序。
	所以可以使用TreeSet。
3，将集合的信息写入到一个文件中。


*/

//描述学生对象
class Student implements Comparable<Student>{//根据学生成绩排序，所以实现comparable接口让学生对象具有比较性

    private String name;
    private int ma,cn,en;
    private int sum;

    public Student(String name, int ma, int cn, int en) {
        this.name = name;
        this.ma = ma;
        this.cn = cn;
        this.en = en;
        sum = ma + cn + en;
    }

    //排序
    @Override
    public int compareTo(Student o) {//复写方法 设置比较模式
        int num=new Integer(this.sum).compareTo(new Integer(o.sum));
        if (num==0)
            return this.name.compareTo(o.name);
        return num;
    }

    public String getName() {
        return name;
    }

    public int getSum() {
        return sum;
    }

    //不添加重复对象
    @Override
    public boolean equals(Object o) {//更具name与sum总复来比较对象是否相同
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return sum == student.sum && name.equals(student.name);
    }

    @Override
    public int hashCode() {//保证对象的唯一性
        return Objects.hash(name, sum);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", ma=" + ma +
                ", cn=" + cn +
                ", en=" + en +
                '}';
    }
}
class StudentTool{
    public static Set<Student> getStudents() throws IOException {//不传入比较器就使用对象的自然排序
        return getStudents(null);
    }
    public static Set<Student> getStudents(Comparator<Student> cmp) throws IOException {//传入比较器
        System.out.println("输入姓名成绩");
        BufferedReader bufr=new BufferedReader(new InputStreamReader(System.in));//读取键盘输入数据

        String line = null;
        Set<Student> stus=null;//用于存放集合对象，因为要排序所以使用TreeSet集合 不可重复无序集合

        if (cmp==null)
            stus=new TreeSet<>();
        else
            stus=new TreeSet<>(cmp);//集合自定义比较器

        while ((line=bufr.readLine())!=null){
            if ("over".equals(line))
                break;
            String[] info = line.split(",");//分割输入内容
            Student student=new Student(info[0],Integer.parseInt(info[1]),Integer.parseInt(info[2]),Integer.parseInt(info[3]));//创建学生对象
            stus.add(student);//加载入集合
        }
        bufr.close();
        return stus;
    }

    public static void write2File(Set<Student> stus) throws IOException {//将学生集合写本地磁盘
        BufferedWriter bufw = new BufferedWriter(new FileWriter("stuinfo.txt"));
        for(Student s : stus){
            bufw.write(s.toString()+"\t");
            bufw.write(s.getSum()+"");
            bufw.newLine();
            bufw.flush();//写完一个刷新一次
        }
        bufw.close();
    }
}
public class StudentInfoTool {
    public static void main(String[] args) throws IOException {
        Comparator<Student> cmp=Collections.reverseOrder();//创建一个反转的比较器
        Set<Student> stus=StudentTool.getStudents(cmp);//获取到键盘录入的学生对象集合
        StudentTool.write2File(stus);//将学生集合写入磁盘
    }
}

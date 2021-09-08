package day25;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegxDemo {
    public static void main(String[] args) {
        String str="_441195992@q1_q.com";
        String tel= "1[359]\\d*";
        String regx = "[1-9]\\d{4,14}";
        System.out.println(str.matches(tel));

        String name="ahsssngcccabcbssdfaagglsdsdccfbbaabdsggssd";
        String sub = "\\s+";
        String re = "(.)\\1+";
        String[] split = name.split(re);
        System.out.println(name.replaceAll(re,"$1"));//$+组号：获取组的内容
        for (String s: split
             ) {
            System.out.println(s);
        }

        String value = "ming tian jiu fang jia le yao";
        String rex = "\\b[a-z]{4}\\b";

        //正则对象
        Pattern compile = Pattern.compile(rex);
        Matcher matcher = compile.matcher(value);
        System.out.println(matcher.matches());//String的matches就是封装的pattern类的方法

        //获取 find --> group
        while (matcher.find()){
            System.out.println(matcher.group());
            System.out.println(matcher.start()+"....."+matcher.end());
        }

        String email = "_441195992@qq.com";
        String ex = "[\\w&&[^_]]\\w+@[\\w&&[^_]]+(\\.[a-zA-Z]+){1,3}";
        System.out.println(email.matches(ex));
    }
}

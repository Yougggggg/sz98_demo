public class StringDemo {
    public static void main(String[] args) {
        String s1="abc,defab";
        String s3="aA";
        char[] s2=new char[6];
        show(s1.length());
        show(s1.indexOf("b"));
        show(s1.charAt(0));
        show(s1.indexOf("b",3));
        show(s1.indexOf("ef"));
        show(s1.isEmpty());
        show(s1.contains("ab"));
        show(s1.startsWith("abc"));
        show(s1.endsWith("ab"));
        method_trans();
        show(String.valueOf(true)+1);//任意数据类型转成字符串
        show(s1.toCharArray());//字符串转字符数组
        byte[]b=s1.getBytes();//字符串转字节数组
        s1.getChars(1,3,s2,0);//将字符从此字符串复制到目标字符数组
        show(s1.replace("ab","kkkk"));//替换
        String ss=s1.substring(s1.indexOf(',')+1);//获取子串
        show(ss);
        show(ss.trim());//去除两段空格
        show(s1.compareTo(s3));//自然顺序比较大小
        //1==49 a==97 A==65
    }
    static <T> void show(T t){
        System.out.println(t);
    }
    static void method_trans(){
        char[] arr={'a','b','c','d','e','f'};
        String s=new String(arr,1,3);
        show("s="+s);
        show(String.copyValueOf(arr));
    }
}
//字符串练习
class StringTest{
    static void sop(String str){
        System.out.println(str);
    }
    public static void main(String[] args) {
        String s="   abc d   ";
//        sop(s);
//        s=myTrim(s);
//        sop(s);
        sop(reversString(s,3,5));
        sop(getSubCount_2("kkabsfskkasbskbskkksfa","kk")+"");
        sop(getMaxSubString("kljkljkljhelljavaasgs","lnzjuoinnghelljam/sf"));
    }
    //去除字符串两端空格
    static String myTrim(String str){
        int start=0;
        int end=str.length()-1;
        while (start<=end && str.charAt(start)==' ')
            start++;
        while (end>=start && str.charAt(end)==' ')
            end--;
        return str.substring(start,end+1);
    }
    //反转字符串
    static String reversString(String s){
        return reversString(s,0,s.length());
    }
    static String reversString(String s,int start,int end){
        char[] c=s.toCharArray();
        reverse(c,start,end);
        return new String(c);//字符数组转成字符串
        //String.copyValueOf()
        //String.valueOf()
    }
    //数组反转
    static void reverse(char[] chr,int s,int e){
        for (int start=s,end=e-1;start<end;start++,end--){
            swap(chr,start,end);
        }
    }
    static void swap(char[] chr,int x,int y){
        char temp=chr[x];
        chr[x]=chr[y];
        chr[y]=temp;
    }

    //获取一个字符串再另一个字符串中出现的次数
    static int getSubCount(String str,String key){
        int count=0;
        int index=0;
        while ((index=str.indexOf(key))!=-1){
            sop("str="+str);
            str=str.substring(index+key.length());
            count++;
        }
        return count;
    }
    static int getSubCount_2(String str,String key){
        int count=0;
        int index=0;
        while ((index=str.indexOf(key,index))!=-1){
            count++;
            index=index+key.length();
        }
        return count;
    }
    //获取两个字符串中最大相同字串
    static String getMaxSubString(String s1,String s2){
        String max,min;
        max=(s1.length()>s2.length())?s1:s2;
        min=(max.equals(s1))?s2:s1;
        for (int i = 0; i < min.length(); i++) {//短的字符串有多长，外循环就要跑多少次
            for (int j = 0,z=min.length()-i; z!=min.length()+1 ; j++,z++) {//短的字符串每减1个字符就有n+1种可能
                //z为尾坐标，开始减去外循换的次数，然后在依次递增，就包含了短字符串的所有变换形式
                String temp=min.substring(j,z);//根据每次的首位坐标获取子字符串
                if (max.contains(temp))//如果s1包含temp 就是最大字串
                    return temp;
            }
        }
        return "";
    }

}

package MapFile_16;

import java.util.*;

public class map_1 {
    public static void main(String[] args) {
        Map<String,String> map=new HashMap<>();

        //存入元素
        map.put("001","Zhangs1");
        map.put("002","Zhangs2");
        map.put("003","Zhangs3");

        //存在就返回false 不存在就返回true
        System.out.println(map.containsKey("002"));
        //移除并返回指定元素
        System.out.println(map.remove("001"));
        System.out.println(map);
        Collection<String> con=map.values();

        //去除元素
        //keySet()
        //先获取map集合的所有键的Set集合
        Set<String> keySet=map.keySet();
        //获取Set集合迭代器
        Iterator<String> it=keySet.iterator();
        while (it.hasNext()){
            String key=it.next();
            System.out.println(key);
            System.out.println(map.get(key));
        }

        //取出Map集合的映射关系，存入Set集合
        Set<Map.Entry<String ,String>> me=map.entrySet();
        Iterator<Map.Entry<String ,String>> iterator=me.iterator();
        while (iterator.hasNext()){
            Map.Entry<String ,String> entry=iterator.next();
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
    }
}

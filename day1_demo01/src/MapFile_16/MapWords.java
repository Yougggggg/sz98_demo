package MapFile_16;

import java.util.*;

//map统计词频
public class MapWords {
    public static void main(String[] args) {
        WordsCount count=new WordsCount();
        System.out.println(count.getCount("abl_!sigsss"));
        ArrayList<String> list=new ArrayList<>();
        list.add("assf");
        list.add("nc");
        list.add("bkdd");
        list.add("kk");
        Collections.sort(list,Collections.reverseOrder(new ComString()));
        System.out.println(list);
        System.out.println(SearchHalf.haleSearch(list,"bb"));
        System.out.println(Collections.binarySearch(list,"nc",new ComString()));
        Collections.replaceAll(list,"nc","nn");
        System.out.println(list);
        System.out.println(Collections.replaceAll(list,"nnn","zzzz"));
        TreeSet<String> treeSet=new TreeSet<>();
        treeSet.add("kkk");
        treeSet.add("bbb");
        treeSet.add("ccc");
        treeSet.add("aaa");
        Iterator<String> it=treeSet.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }
    }
}
class WordsCount{
    private TreeMap<String,Integer> map;
    public TreeMap<String,Integer> getCount(String str){
        char[] chars=str.toCharArray();
        map=new TreeMap<>();
        for (int i = 0; i < chars.length; i++) {
            if (!(chars[i]>='a'&&chars[i]<='z'||chars[i]>='A'&&chars[i]<='Z'))
                continue;

            Integer num=map.get(chars[i]+"");
            if (num!=null){
                num+=1;
                map.put(chars[i]+"",num);
            }else {
                map.put(chars[i]+"",1);
            }

//            if (map.containsKey(chars[i]+"")){
//                int temp=map.get(chars[i]+"")+1;
//                map.put(chars[i]+"",temp);
//            }else {
//                map.put(chars[i]+"",1);
//            }
        }

        return map;
    }
}
//二叉树查找
class SearchHalf{
    public static <T extends Comparable<? super T>> int haleSearch(List<T> list,T key){
        int max,min,mid;
        max=list.size()-1;
        min=0;
        while (min<=max){
            mid=(min+max)>>1;
            if (list.get(mid).compareTo(key)>0)
                max=mid-1;
            else if (list.get(mid).compareTo(key)<0)
                min=mid+1;
            else
                return mid;
        }
        return -min-1;
    }
}
//自定义排序方式
class ComString implements Comparator<String>{
    @Override
    public int compare(String o1, String o2) {
        if (o1.length()>o2.length())
            return 1;
        if (o1.length()<o2.length())
            return -1;
        return o1.compareTo(o2);
    }
}
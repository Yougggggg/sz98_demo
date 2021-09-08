public class StringBufferDemo {
    public static void main(String[] args) {
        StringBuffer sb=new StringBuffer();
        sb.append("acbc").append(true).append(12);
        sb.insert(1,"kk");
        sb.delete(1,3);//删除一部分内容
        while (sb.toString().indexOf('c')!=-1){
            sb.deleteCharAt(sb.toString().indexOf('c'));
            System.out.println(sb);
        }
        sb.replace(1,4,"java");
        System.out.println(sb);
        System.out.println(sb.reverse());
    }
}

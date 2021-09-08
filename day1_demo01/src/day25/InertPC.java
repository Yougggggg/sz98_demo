package day25;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InertPC {
    public static void main(String[] args) throws Exception {
        String email = "[\\w&&[^_]]\\w+@[\\w&&[^_]]+(\\.[a-zA-Z]+){1,3}";
        String email2 = "[正][则][\\u4E00-\\u9FA5]{4}";
        URL url = new URL("https://blog.csdn.net/make164492212/article/details/51656638");
        System.out.println(url.getPort());
        System.out.println(url.getPath());
        System.out.println(url.getHost());
        System.out.println(url.getQuery());
        System.out.println(url.getProtocol());

        URLConnection connection = url.openConnection();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        Pattern compile = Pattern.compile(email2);
        //BufferedReader reader = new BufferedReader(new FileReader("mail.txt"));
        String line;
        while ((line=bufferedReader.readLine())!=null){
              Matcher matcher = compile.matcher(line);
//            String[] split = line.split(email2);
//            if (split.length>1){
//                System.out.println(Arrays.toString(split));
//            }
            while (matcher.find()){
                System.out.println(matcher.group());
            }
        }
        bufferedReader.close();
    }
}
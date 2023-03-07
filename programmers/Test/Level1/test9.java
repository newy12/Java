package Test.Level1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 숫자 문자열과 영단어
 */
public class test9 {
    public static void main(String[] args) {
        String s = "one4seveneight";
        //String s = "2three45sixseven";
        Map<String,String> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        list.add("zero");
        list.add("one");
        list.add("two");
        list.add("three");
        list.add("four");
        list.add("five");
        list.add("six");
        list.add("seven");
        list.add("eight");
        list.add("nine");
        map.put("zero","0");
        map.put("one","1");
        map.put("two","2");
        map.put("three","3");
        map.put("four","4");
        map.put("five","5");
        map.put("six","6");
        map.put("seven","7");
        map.put("eight","8");
        map.put("nine","9");
        String s0 =  s.replaceAll(list.get(0),map.get(list.get(0)));
        String s1 = s0.replaceAll(list.get(1),map.get(list.get(1)));
        String s2 = s1.replaceAll(list.get(2),map.get(list.get(2)));
        String s3 = s2.replaceAll(list.get(3),map.get(list.get(3)));
        String s4 = s3.replaceAll(list.get(4),map.get(list.get(4)));
        String s5 = s4.replaceAll(list.get(5),map.get(list.get(5)));
        String s6 = s5.replaceAll(list.get(6),map.get(list.get(6)));
        String s7 = s6.replaceAll(list.get(7),map.get(list.get(7)));
        String s8 = s7.replaceAll(list.get(8),map.get(list.get(8)));
        String s9 = s8.replaceAll(list.get(9),map.get(list.get(9)));
        System.out.println("before " + s9);
        int answer = Integer.parseInt(s9);
        System.out.println("answer = " + answer);



/*
        for (int i = 0; i < map.size(); i++) {
            String s2 =  s.replaceAll(list.get(i),map.get(list.get(i)));

            System.out.println("s = " + s2);
        }*/
    }


}

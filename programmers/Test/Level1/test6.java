package Test.Level1;

import java.util.Arrays;

/**
 * 서울에서 김서방 찾기
 */
public class test6 {

    public static void main(String[] args) {
        String[] seoul = {"Jane","Kim"};
        String value = "김서방은 "+Arrays.asList(seoul).indexOf("Kim")+"에 있다";
        System.out.println("value = " + value);
    }
}

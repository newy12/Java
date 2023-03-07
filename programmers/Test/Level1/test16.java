package Test.Level1;

/**
 * 문자열 다루기 기본
 */
public class test16 {
    public static boolean main(String[] args) {
        String s = "1234";
        boolean answer = true;
        String regExp = "^[0-9]{4}+$";//dd
        String regExp2 = "^[0-9]{6}+$";//.ㅇㅇㅇㅇㅇddd
        return s.matches(regExp) || s.matches(regExp2);
    }
}

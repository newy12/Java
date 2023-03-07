package Test.Level1;

/**
 * 자릿수 더하기
 */
public class test13 {
    public static void main(String[] args) {
        int n = 987;
        int value = 0;
        String num = String.valueOf(n);

        for (int i = 0; i < num.length(); i++) {
            value += Integer.parseInt(String.valueOf(num.charAt(i)));
        }
        System.out.println("value = " + value);
    }



}

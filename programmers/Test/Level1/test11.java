package Test.Level1;

/**
 * 하샤드 수
 */
public class test11 {
    public static void main(String[] args) {
        int x = 11;//ㅇ/
        String number = String.valueOf(x);
        int value = 0;

        for (int i = 0; i < number.length(); i++) {
            String.valueOf(number.charAt(i));
        }
        System.out.println("sb = " + value);
        if(x%(Integer.parseInt(String.valueOf(value))) == 0){
            System.out.println("true");
        }else{
            System.out.println("false");
        }
    }



}

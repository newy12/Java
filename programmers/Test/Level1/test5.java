package Test.Level1;



/**
 * 핸드폰 번호 가리기
 */
public class test5{

    public static void main(String[] args) {
        String phone_number = "01033334444";
        String phone_number2 = "027778888";
        String answer = "";
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < phone_number2.length()-4; i++) {
            stringBuilder.append(phone_number.substring(i,i+1).replaceAll("[0-9]","*"));
        };
        stringBuilder.append(phone_number.substring(phone_number.length()-4));
        answer = String.valueOf(stringBuilder);
        System.out.println("answer = " + answer);
    }
}

package Test.Level1;

/**
 * 부족한 금액 계산하기
 */
public class test15 {
    public static void main(String[] args) {
        int price = 1;
        int money = 0;
        int count = 1;
        long answer = 0;
        long sum = 0;

        for (int i = 1; i <= count; i++) {
            sum += price * i;
            System.out.println("price : " + price * i);
            System.out.println("sum : " + sum);
        }
            answer = sum-money;
            System.out.println("answer = " + answer);
        if(sum < money){
            System.out.println("answer = " + answer);
        }
    }




}

package Test.Level1;



import java.util.stream.IntStream;

/**
 * 없는 숫자 더하기
 */
public class test2 {
    public static void main(String[] args) {
        int[] numbers = {5,8,4,0,6,7,9};
        int sum = 0;
        for (int i = 1; i <=9 ; i++) {
            int value = i;
            boolean a = IntStream.of(numbers).anyMatch(x -> x == value);
            if(a == false){
                sum += value;
            }
        }
        System.out.println("sum = " + sum);
    }
}

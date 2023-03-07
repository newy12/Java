package Test.Level1;

/**
 * 내적
 */
public class test4 {
    public static void main(String[] args) {
        int[] a = {1,2,3,4};
        int[] b = {-3,-1,0,2};
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i] * b[i];
        }
        System.out.println("sum = " + sum);
    }
}

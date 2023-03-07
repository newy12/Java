package Test.Level1;
import java.util.Arrays;

/**
 * 제일 작은 수 제거하기
 */
public class test7 {
    public static int[] main(String[] args) {
        int[] arr = {4,3,2,1};
        int[] arr2 = {10};
        int[] answer = {};

        if(arr.length <= 1) return new int[]{-1};
        return Arrays.stream(arr).filter(m -> m != Arrays.stream(arr).min().getAsInt()).toArray();

        
    }
}

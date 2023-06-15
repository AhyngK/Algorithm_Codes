import java.util.Arrays;
import java.util.Scanner;

public class Bj10844_01 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        long[] nums = new long[101];
        Arrays.fill(nums,-1);
        nums[1] =9;

        long[]tempNum = new long[10];
        Arrays.fill(tempNum,1);
        tempNum[0] =0;

        for (int i = 2; i <N+1 ; i++) {
            long[] thisNum = new long[10];
            long sum =0;
            for (int j = 0; j < thisNum.length; j++) {
                long before =0;
                long after =0;
                if(0<j){
                    before +=tempNum[j-1];
                }
                if(j<thisNum.length-1){
                    after+=tempNum[j+1];
                }
                thisNum[j] = (after+before)%1000000000;
                sum= (sum+thisNum[j])%1000000000;
            }
            tempNum = thisNum;
            // System.out.println(Arrays.toString(tempNum));
            nums[i] = sum;
        }

        System.out.println(nums[N]);
    }

}

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int[] firstPrime = {2,3,5,7};
    static int[] primes = {1,3,5,7,9};
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] nums = new int[N];
        selection(0,nums);
        System.out.print(sb);
    }
    static void selection (int index, int[] nums){
        if(index == nums.length){
            for (int i = 0; i < nums.length; i++) {
                sb.append(nums[i]);
            }
            sb.append("\n");
            return;
        }

        if(index == 0){
            for (int i = 0; i < firstPrime.length; i++) {
                nums[index] = firstPrime[i];
                selection(index+1,nums);
            }
        }
        else {
            for (int i = 0; i < primes.length; i++) {
                nums[index] = primes[i];
                int num = makeNum(index,nums);
                if(checkPrime(num)){
                    selection(index+1,nums);
                }
            }
        }
    }
    static boolean checkPrime(int num){
        boolean check = true;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if(num%i == 0){
                check = false;
                break;
            }
        }
        return check;
    }
    static int makeNum(int index, int[] nums){
        int num = 0;
        for (int i = 0; i < nums.length; i++) {
            if(index<0){
                break;
            }
            num += Math.pow(10,index)*nums[i];
            index-=1;
        }
        return num;
    }
}
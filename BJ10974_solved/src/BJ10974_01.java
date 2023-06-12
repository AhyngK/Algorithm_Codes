import java.util.Arrays;
import java.util.Scanner;

public class BJ10974_01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int num =0;
        boolean[] check = new boolean[N+1];
        Arrays.fill(check,false);
        int[] nums = new int[N];
        function(N,check,num,nums);
    }
    static void function(int N, boolean[] check, int num, int[] nums){
        if(num==N){
            for(int a:nums){
                System.out.print(a+" ");
            }
            System.out.println();
            return;
        }
        for (int i = 1; i <=N ; i++) {
            if(check[i]){
                continue;
            }
            nums[num] = i;
            check[i] = true;
            function(N,check,num+1,nums);
            check[i] = false;
        }
    }
}

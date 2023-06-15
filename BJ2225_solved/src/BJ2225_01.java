import java.util.Arrays;
import java.util.Scanner;

public class BJ2225_01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] input = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = input[0];
        int K = input[1];
        long[][] count = new long[N+1][K+1];
        Arrays.fill(count[0],1);

        for (int k = 1; k < K+1; k++) {
            if(k==1){
                for (int j = 1; j <N+1 ; j++) {
                    count[j][k] = 1;
                }
                continue;
            }
            for (int n = 1; n < count.length; n++) {
                long sum =0;
                for (int nBefore = 0; nBefore <=n ; nBefore++) {
                    sum = (sum+count[nBefore][k-1])%1000000000;
                }
                count[n][k] = sum%1000000000;
            }
        }
        // System.out.println(N+" "+K);
        System.out.println(count[N][K]);
    }
}

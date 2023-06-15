import java.util.Arrays;
import java.util.Scanner;

public class BJ2193_01 {
    public static void main(String[] args) {
        long[] counts = new long[91];
        Arrays.fill(counts,0);
        counts[1] = 1;

        long[] temp = {0,1};

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        for (int i = 2; i <=N ; i++) {
            long[] function = new long[2];
            function[0] = temp[0]+temp[1];
            function[1] = temp[0];

            counts[i] = function[0]+function[1];
            temp = function;
        }

        System.out.println(counts[N]);
    }
}

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static long[] counts = new long[1001];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        Arrays.fill(counts,-1);
        counts[1] =1;
        counts[2] =2;

        recursion(N);
        System.out.println(counts[N]);

    }
    static long recursion(int N){
        if(counts[N]!=-1){
            return counts[N];
        }

        long minus1=0;
        long minus2=0;

        if(N-1>0){
            minus1 = recursion(N-1)%10007;
        }
        if(N-2>0){
            minus2 = recursion(N-2)%10007;
        }

        counts[N] = (minus1+minus2)%10007;
        return counts[N];
    }
}

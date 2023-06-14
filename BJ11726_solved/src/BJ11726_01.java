import java.util.Arrays;
import java.util.Scanner;

public class BJ11726_01 {
    // 무조건 1*2 블록 1개가 추가되거나 2*1 블록 2개가 추가되는 것으로 생각해본다
    // 그러면 각 숫자가 1,2의 조합으로 구성되는 수의 개수임을 알 수 있다
    // N이 8인 경우는 (N이 7인 경우에 1을 더하는 경우) + (N이 6인 경우에 2를 더하는 경우) 로 볼 수 있다
    // 그럼 마지막에 추가된 블럭이 다 다르므로, 겹치는 경우가 없어진다
    // 피보나치와 유사한 방식이 된다
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


import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int[] counts = new int[10000001];
    public static void main(String[] args) {
        // 1,2,3,4,5,6,7,8 이 있을 때,
        // 1, 1+1, 1*3, 2*2, 2*3, 6+1 등으로 나타낼 수 있음
        // D[8] = D[4]+1 / D[4] = D[2]+1 로 표현 가능?

        // DP 배열로 사용할 counts에 0을 채움
        Arrays.fill(counts,-1);
        counts[0] =0;
        counts[1] =0;

        // 숫자 N입력 받기
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        recursion(N);
        System.out.println(counts[N]);
    }
    static int recursion (int n){
        if(counts[n]!=-1){
            return counts[n];
        }

        int divide3 = 1000000;
        int divide2 = 1000000;

        // 3으로 나눌 때, 2로 나눌 때, 1을 뺄때 세 경우에서 이전 값을 모두 호출
        if(n%3==0){
            divide3 = recursion(n/3)+1;
        }
        if (n%2==0) {
            divide2 = recursion(n/2)+1;
        }
        int minus1 = recursion(n-1)+1;

        // 세 경우에서 가장 작은 값을 현재 값으로 저장
        int answer = Math.min(divide3, divide2);
        answer = Math.min(answer, minus1);

        counts[n] = answer;
        return answer;
    }
}
import java.util.Arrays;
import java.util.Scanner;

public class BJ15988_02 {
    static long[] count;
    public static void main(String[] args) {
        count = new long[1000001];
        Arrays.fill(count,-1);
        count[0] = 1;
        count[1] = 1;

        Scanner sc = new Scanner(System.in);
        int testCases = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < testCases; i++) {
            int temp = sc.nextInt();
            sc.nextLine();

            if(count[temp]!=-1){
                System.out.println(count[temp]);
                continue;
            }

            // temp의 값을 구할 함수 호출
            for (int n = 2; n <=temp ; n++) {
                long one = 0;
                long two =0;
                long three =0;
                if(n>=3){
                    three= count[n-3]%1000000009;
                }
                if(n>=2){
                    two= count[n-2]%1000000009;
                }
                if(n>=1){
                    one= count[n-1]%1000000009;
                }
                count[n] = (one+two+three)%1000000009;
            }

            System.out.println(count[temp]);
        }
    }
}

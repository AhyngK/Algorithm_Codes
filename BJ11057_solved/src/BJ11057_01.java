import java.util.Arrays;
import java.util.Scanner;

public class BJ11057_01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        long[][] counts = new long[N+1][10];
        for (int i = 0; i < counts.length; i++) {
            if(i==0){
                Arrays.fill(counts[i],0);
            }
            else if(i==1){
                Arrays.fill(counts[i],1);
            }
            else {
                counts[i][0] =1;
                for (int j = 1; j <counts[i].length ; j++) {
                    long temp =0;
                    for (int k = 0; k <=j ; k++) {
                        temp = (temp+counts[i-1][k])%10007;
                    }
                    counts[i][j] = temp;
                }
            }
        }

        long total =0;
        for (int i = 0; i < counts[N].length; i++) {
            total = (total+counts[N][i])%10007;
        }
        System.out.println(total);
    }
}

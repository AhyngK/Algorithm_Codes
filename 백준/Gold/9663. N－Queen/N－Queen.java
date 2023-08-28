import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int N;
    static int count = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        int[] yCoors = new int[N];
        Arrays.fill(yCoors,-1);
        recursion(0,yCoors);
        System.out.println(count);
    }
    static void recursion(int x, int[] yCoors){
        if(x==N){
            count++;
            return;
        }

        for (int i = 0; i < N; i++) {
            boolean check = true;
            for (int j = 0; j < yCoors.length; j++) {
                if(i==yCoors[j]){
                    check = false;
                    break;
                }
            }
            for (int j = 0; j < x; j++) {
                if(Math.abs(x-j) == Math.abs(yCoors[j]-i)){
                    check = false;
                    break;
                }
            }
            if(check){
             yCoors[x] = i;
             recursion(x+1,yCoors);
             yCoors[x] = -1;
            }
        }
    }
}

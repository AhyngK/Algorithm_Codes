import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int N;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        int M = sc.nextInt();

        int[] selected = new int[M];
        selection(0,selected);
        System.out.print(sb);
    }
    static void selection(int index, int[] selected){
        if(index == selected.length){
            for (int i = 0; i < selected.length; i++) {
                sb.append(selected[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 1; i <= N; i++) {
                selected[index] = i;
                selection(index+1,selected);
        }
    }
}

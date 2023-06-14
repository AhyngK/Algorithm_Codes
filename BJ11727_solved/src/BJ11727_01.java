import java.util.Arrays;
import java.util.Scanner;

public class BJ11727_01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[] counts = new int[1001];
        Arrays.fill(counts,-1);
        counts[1] =1;
        counts[2] =3;

        // 11726번과 동일하게 1칸, 2칸을 더하는 방식으로 접근
        // 하지만 2칸 추가의 경우 경우의 수가 2가지가 되므로, *2를 해줌

        for (int i = 3; i <=N ; i++) {
            counts[i] = (counts[i-1]+counts[i-2]*2)%10007;
        }

        System.out.println(counts[N]);
    }
}

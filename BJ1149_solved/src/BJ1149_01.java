import java.util.Arrays;
import java.util.Scanner;

public class BJ1149_01 {
    public static void main(String[] args) {
        // 3개의 연속된 집을 같은 색이 연속되지 않도록 칠하는 것은 두개의 집을 같은 색이 아니도록 칠하는 것과 같다
        Scanner sc  = new Scanner(System.in);
        int houses = sc.nextInt();
        sc.nextLine();

        int[][] housePaint = new int[houses][3];
        for (int i = 0; i < houses; i++) {
            int[] temp = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            housePaint[i] = temp;
        }

        int[][] prices = new int[houses][3];
        prices[0] = housePaint[0];
        for (int i = 1; i < prices.length; i++) {
            int[] temp = new int[3];
            temp[0] = Math.min(prices[i-1][1],prices[i-1][2])+housePaint[i][0];
            temp[1] = Math.min(prices[i-1][0],prices[i-1][2])+housePaint[i][1];
            temp[2] = Math.min(prices[i-1][0],prices[i-1][1])+housePaint[i][2];
            prices[i] = temp;
        }

        int[] biggest = prices[prices.length-1].clone();
        Arrays.sort(biggest);
        System.out.println(biggest[0]);
    }
}

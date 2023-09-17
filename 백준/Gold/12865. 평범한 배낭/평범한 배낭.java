import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int objectNum = Integer.parseInt(st.nextToken());
        int totalWeight = Integer.parseInt(st.nextToken());

        int[][] objcts = new int[objectNum][2];
        for (int i = 0; i < objcts.length; i++) {
            objcts[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int[][] dp = new int[totalWeight+1][objectNum+1];
        for (int j = 1; j < dp[0].length; j++) {
            for (int i = 0; i < dp.length; i++) {
                int current = 0;
                if(i>0){
                    current = Math.max(current,dp[i-1][j]);
                }
                if(j>0){
                    current = Math.max(current,dp[i][j-1]);
                }
                if(i-objcts[j-1][0]>=0 && j>0){
                    current = Math.max(current,dp[i-objcts[j-1][0]][j-1]+objcts[j-1][1]);
                }
                dp[i][j] = current;
            }
        }
        System.out.println(dp[dp.length-1][dp[0].length-1]);
    }
}
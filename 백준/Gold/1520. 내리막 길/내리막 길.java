import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 내리막 길에서는 항상 방향성이 존재한다
public class Main {
    static int[][] map;
    static int[][] dp;
    static int[][] directions = {{-1,0},{0,1},{0,-1},{1,0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        map = new int[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())];
        dp = new int[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Arrays.fill(dp[i],-1);
        }
        dp[0][0] = 1;
        System.out.println(recursion(map.length-1,map[0].length-1));
    }
    static int recursion(int x, int y){
        if(dp[x][y]!=-1){
            return dp[x][y];
        }

        int temp = 0;
        for (int i = 0; i < directions.length; i++) {
            int nextX = x+directions[i][0];
            int nextY = y+directions[i][1];
            if(borderCheck(nextX,nextY) && map[x][y]<map[nextX][nextY]){
                temp+=recursion(nextX,nextY);
            }
        }
        dp[x][y] = temp;
        return dp[x][y];
    }
    static boolean borderCheck(int x, int y){
        if(x>=0 && x< map.length && y>=0 && y<map[0].length){
            return true;
        }
        return false;
    }
}

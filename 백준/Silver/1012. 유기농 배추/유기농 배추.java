import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] farm;
    static int[][] directions = {{0,1},{1,0},{0,-1},{-1,0}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int testCase = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= testCase; tc++) {
            st = new StringTokenizer(br.readLine());
            int rows = Integer.parseInt(st.nextToken());
            int columns = Integer.parseInt(st.nextToken());
            int cabbage = Integer.parseInt(st.nextToken());
            farm = new int[rows][columns];
            for (int i = 0; i < cabbage; i++) {
                st = new StringTokenizer(br.readLine());
                farm[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
            }
            int count = 0;
            for (int i = 0; i < farm.length; i++) {
                for (int j = 0; j < farm[0].length; j++) {
                    if(farm[i][j] == 1){
                        count+=1;
                        runBFS(i,j);
                    }
                }
            }
            System.out.println(count);
        }
    }
    static void runBFS(int startX, int startY){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {startX,startY});
        farm[startX][startY] = 0;
        while (!queue.isEmpty()){
            int[] temp = queue.poll();
            for (int i = 0; i < directions.length; i++) {
                int[] next = {temp[0]+ directions[i][0],temp[1]+directions[i][1]};
                if(borderCheck(next) && farm[next[0]][next[1]]==1){
                    farm[next[0]][next[1]] = 0;
                    queue.add(next);
                }
            }
        }

    }
    static boolean borderCheck(int[] next){
        if(next[0]>=0 && next[0]< farm.length && next[1]>=0 && next[1]<farm[0].length){
            return true;
        }
        return false;
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int tomatoTotal = 0;
    static int days = Integer.MAX_VALUE;
    static int[][] directions = {{-1,0},{0,1},{1,0},{0,-1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int columns = Integer.parseInt(st.nextToken());
        int rows = Integer.parseInt(st.nextToken());

        int[][] tomatoBox = new int[rows][columns];

        for (int i = 0; i < tomatoBox.length; i++) {
            tomatoBox[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        Queue<int[]> que = new LinkedList<>();
        searchTomatoes(que,tomatoBox);
        BFSSearch(que,tomatoBox);
        System.out.println(days==Integer.MAX_VALUE ? -1 : days);
    }
    static void searchTomatoes(Queue<int[]> que, int[][] tomatoBox){
        for (int i = 0; i < tomatoBox.length; i++) {
            for (int j = 0; j < tomatoBox[0].length; j++) {
                if(tomatoBox[i][j]==1){
                    int[] temp = new int[2];
                    temp[0] = i;
                    temp[1] = j;
                    que.add(temp);
                }
                if(tomatoBox[i][j]==0){
                    tomatoTotal+=1;
                }
            }
        }
    }
    static void BFSSearch(Queue<int[]> que, int[][] tomatoBox){
        boolean[][] visitCheck = new boolean[tomatoBox.length][tomatoBox[0].length];
        for (int i = 0; i < visitCheck.length; i++) {
            Arrays.fill(visitCheck[i],false);
        }

        boolean firstCheck = false;
        int dayCount = -1;
        int tomatoCount = 0;

        while (!que.isEmpty()){
            Queue<int[]> nextQue = new LinkedList<>();
            dayCount+=1;

            while (!que.isEmpty()){
                int[] currentTemp = que.poll();

                for (int i = 0; i < directions.length; i++) {
                    int[] next = new int[2];
                    next[0] = currentTemp[0]+directions[i][0];
                    next[1] = currentTemp[1]+directions[i][1];
                    if(bounderyCheck(next[0],next[1], tomatoBox) && tomatoBox[next[0]][next[1]]==0){
                        nextQue.add(next);
                        tomatoBox[next[0]][next[1]] = 1;
                        tomatoCount+=1;
                    }
                }
            }
            que = nextQue;
        }

        if(tomatoCount==tomatoTotal){
            days = Math.min(dayCount,days);
        }
    }
    static boolean bounderyCheck(int x, int y, int[][] tomatoBox){
        if(x>=0 && x<tomatoBox.length && y>=0 && y<tomatoBox[0].length){
            return true;
        }
        return false;
    }
}
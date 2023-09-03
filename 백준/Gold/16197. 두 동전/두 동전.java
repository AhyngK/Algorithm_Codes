import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] directions = {{0,-1},{-1,0},{1,0},{0,1}};
    static final int ENDLIMIT = 10;
    static String[][] board;
    static int smallest = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int rows = Integer.parseInt(st.nextToken());
        int columns = Integer.parseInt(st.nextToken());

        board = new String[rows][columns];
        for (int i = 0; i < board.length; i++) {
            board[i] = br.readLine().split("");
        }

        int[][] coor = findCoin();
        run(coor);
        smallest = smallest == Integer.MAX_VALUE ? -1 : smallest;
        System.out.println(smallest);
    }
    static void run(int[][]coins){
        Queue<int[][]> que = new LinkedList<>();
        int[][] start = {{coins[0][0],coins[0][1],-1,-1},{coins[1][0],coins[1][1],-1,-1}};
        que.add(start);

        int count = 0;
        label : while (!que.isEmpty()){
            count+=1;
            if(count > ENDLIMIT){
                break label;
            }

            Queue<int[][]> tempQue = new LinkedList<>();
            while (!que.isEmpty()){
                int[][] temp = que.poll();

                for (int i = 0; i < directions.length; i++) {
                    int[][] next = {{temp[0][0]+directions[i][0], temp[0][1]+directions[i][1], temp[0][0],temp[0][1]},{temp[1][0]+directions[i][0], temp[1][1]+directions[i][1], temp[0][0],temp[0][1]}};
                    if(borderCheck(next[0]) && borderCheck(next[1])){
                        if(board[next[0][0]][next[0][1]].equals("#") && board[next[1][0]][next[1][1]].equals("#")){
                            continue;
                        }
                        if(next[0][0]==temp[0][0] && next[0][1]==temp[0][1] && next[1][0]==temp[1][0] && next[1][1]==temp[1][1]){
                            continue;
                        }
                        if (board[next[0][0]][next[0][1]].equals("#")) {
                            next[0][0] = temp[0][0];
                            next[0][1] = temp[0][1];
                        }
                        if (board[next[1][0]][next[1][1]].equals("#")) {
                            next[1][0] = temp[1][0];
                            next[1][1] = temp[1][1];
                        }
                        tempQue.add(next);
                    }
                    else if (!borderCheck(next[0]) && !borderCheck(next[1])) {
                        continue;
                    }
                    else {
                        smallest = count;
                        break label;
                    }
                }
            }
            que = tempQue;
        }
    }
    static boolean borderCheck(int[] next){
        if(next[0]>=0 && next[0]< board.length && next[1]>=0 && next[1]< board[0].length){
            return true;
        }
        return false;
    }
    static int[][] findCoin(){
        int[][] coor = {{-1,-1},{-1,-1}};
        label : for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if(board[i][j].equals("o")){
                    if(coor[0][0] == -1){
                        coor[0][0] = i;
                        coor[0][1] = j;
                    }
                    else {
                        coor[1][0] = i;
                        coor[1][1] = j;
                        break label;
                    }
                }
            }
        }
        return coor;
    }
}

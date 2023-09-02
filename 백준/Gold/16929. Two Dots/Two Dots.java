import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] directions = {{-1,0},{0,1},{0,-1},{1,0}};
    static String[][] board;
    static boolean check = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int rows = Integer.parseInt(st.nextToken());
        int columns = Integer.parseInt(st.nextToken());

        board = new String[rows][columns];
        for (int i = 0; i < board.length; i++) {
            board[i] = br.readLine().split("");
        }

        end : for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if(check){
                    break end;
                }
                if((j==0 || !board[i][j-1].equals(board[i][j])) && (i==0 || !board[i-1][j].equals(board[i][j]))){
                    search(i,j,board[i][j]);
                }
            }
        }
        String answer = check ? "Yes" : "No";
        System.out.println(answer);
    }
    static void search(int x, int y, String toFind){
        boolean [][] visit = new boolean[board.length][board[0].length];
        visit[x][y] = true;

        Queue<int[]> que = new LinkedList<>();
        que.add(new int[] {x,y,0});

        label : while (!que.isEmpty()){
            int[] temp = que.poll();
            for (int i = 1; i < directions.length; i++) {
                int[] next = {temp[0]+directions[i][0], temp[1]+directions[i][1], i};
                if(borderCheck(next)){
                    if(visit[next[0]][next[1]] && temp[2]+i!=3){
                        check = true;
                        break label;
                    }
                    if(!visit[next[0]][next[1]] && board[next[0]][next[1]].equals(toFind)){
                        visit[next[0]][next[1]] = true;
                        que.add(next);
                    }
                }
            }
        }
    }
    static boolean borderCheck(int[] next){
        if(next[0]>=0 && next[0]< board.length && next[1]>=0 && next[1]<board[0].length){
            return true;
        }
        return false;
    }
}

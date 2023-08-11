import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static int bingo = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] board = new int[5][5];
        for (int i = 0; i < board.length; i++) {
            board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        int[][] called = new int[5][5];
        for (int i = 0; i < called.length; i++) {
            called[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        boolean[][][] callCheck = new boolean[5][5][2];
        for (int i = 0; i < callCheck.length; i++) {
            for (int j = 0; j < callCheck[i].length; j++) {
                Arrays.fill(callCheck[i][j],false);
            }
        }

        int count = 0;
        for (int i = 0; i < called.length; i++) {
            for (int j = 0; j < called[0].length; j++) {
                if(bingo >= 3){
                    break;
                }
                count+=1;
                called(board,called[i][j],callCheck);
                bingoCheck(callCheck);
            }
        }

        System.out.println(count);
    }
    static void called(int[][] board, int callNum, boolean[][][] callCheck){
        a : for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(board[i][j] == callNum){
                    callCheck[i][j][0] = !callCheck[i][j][0];
                    break a;
                }
            }
        }
    }

    static void bingoCheck(boolean[][][] callCheck){
        for (int i = 0; i < callCheck.length; i++) {
            for (int j = 0; j < callCheck[0].length; j++) {
                if(j==0 && callCheck[i][j][0]){
                    rowCheck(i,j,callCheck);
                }
                if(i==0 && callCheck[i][j][0]){
                    columnCheck(i,j,callCheck);
                }
                if(i==0 && j==0 && callCheck[i][j][0]){
                    rightDown(i,j,callCheck);
                }
                if(i == 0 && j == callCheck[0].length-1 && callCheck[i][j][0]) {
                    leftDown(i,j,callCheck);
                }
            }
        }
    }
    static void rowCheck(int x, int y, boolean[][][] callCheck){
        List<int[]> bingos = new ArrayList<>();
        int count = 0;
        while (y<callCheck[0].length){
            if(callCheck[x][y][0]){
              bingos.add(new int[] {x,y});
              if(callCheck[x][y][1]){
                  count++;
              }
            }
            y++;
        }
        if(bingos.size()==5 && count<5){
            for (int i = 0; i < bingos.size(); i++) {
                callCheck[bingos.get(i)[0]][bingos.get(i)[1]][1]= true;
            }
            bingo++;
        }
    }
    static void columnCheck(int x, int y, boolean[][][] callCheck){
        List<int[]> bingos = new ArrayList<>();
        int count = 0;
        while (x<callCheck.length){
            if(callCheck[x][y][0]){
                bingos.add(new int[] {x,y});
                if(callCheck[x][y][1]){
                    count++;
                }
            }
            x++;
        }
        if(bingos.size()==5 && count<5){
            for (int i = 0; i < bingos.size(); i++) {
                callCheck[bingos.get(i)[0]][bingos.get(i)[1]][1]= true;
            }
            bingo++;
        }
    }
    static void rightDown(int x, int y, boolean[][][] callCheck){
        List<int[]> bingos = new ArrayList<>();
        int count = 0;
        while (x<callCheck.length && y<callCheck[0].length){
            if(callCheck[x][y][0]){
                bingos.add(new int[] {x,y});
                if(callCheck[x][y][1]){
                    count++;
                }
            }
            x++;
            y++;
        }
        if(bingos.size()==5 && count<5){
            for (int i = 0; i < bingos.size(); i++) {
                callCheck[bingos.get(i)[0]][bingos.get(i)[1]][1]= true;
            }
            bingo++;
        }
    }
    static void leftDown(int x, int y, boolean[][][] callCheck){
        List<int[]> bingos = new ArrayList<>();
        int count = 0;
        while (x<callCheck.length && y>-1){
            if(callCheck[x][y][0]){
                bingos.add(new int[] {x,y});
                if(callCheck[x][y][1]){
                    count++;
                }
            }
            x++;
            y--;
        }
        if(bingos.size()==5 && count<5){
            for (int i = 0; i < bingos.size(); i++) {
                callCheck[bingos.get(i)[0]][bingos.get(i)[1]][1]= true;
            }
            bingo++;
        }
    }
}

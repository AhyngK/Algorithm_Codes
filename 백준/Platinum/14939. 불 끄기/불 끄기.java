import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int smallestCount = Integer.MAX_VALUE;
    static int[][] directions = {{-1,0},{0,1},{1,0},{0,-1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] lightBulbs = new int[10][10];
        for (int i = 0; i < lightBulbs.length; i++) {
            String[] sArray = br.readLine().split("");
            for (int j = 0; j < sArray.length; j++) {
                if(sArray[j].equals("#")){
                    lightBulbs[i][j] = 0;
                    continue;
                }
                lightBulbs[i][j] = 1;
            }
        }

        switchSelection(0,0,0,lightBulbs);

        if(smallestCount==Integer.MAX_VALUE){
            smallestCount = -1;
        }
        System.out.println(smallestCount);
    }
    static void switchSelection(int x, int y, int count, int[][] lightBulbs){

        // Stop Point : there is unReachable bulb
        if(!switchCheck(x,y,lightBulbs)){
            return;
        }

        // End Point : Reached the end of board
        if(x==lightBulbs.length){
            if(switchCheck(lightBulbs.length,lightBulbs.length,lightBulbs)){
                smallestCount = Math.min(smallestCount,count);
            }
            return;
        }

        // Next Coordinates
        int[] next = nextBulb(x,y,lightBulbs);

        // 1. Press Switch
        pressSwitch(x,y,1,lightBulbs);
        lightBulbs[x][y] += 1;
        switchSelection(next[0],next[1],count+1,lightBulbs);
        lightBulbs[x][y] -= 1;
        pressSwitch(x,y,-1,lightBulbs);

        // 2. Don't Press Switch
        switchSelection(next[0],next[1],count,lightBulbs);
    }
    static boolean switchCheck(int x, int y, int[][] lightBulbs){
        boolean check = true;
        int[] last = {x-1, y-1};
        if(last[0]>=0 && last[0]<lightBulbs.length && last[1]>=0 && last[1]<lightBulbs[0].length){
            int[] start = {0,0};
            while (true){
                if(start[0]==lightBulbs.length){
                    break;
                }
                if(start[0]==last[0] && start[1] == last[1]+1){
                    break;
                }
                if(lightBulbs[start[0]][start[1]]%2 != 0){
                    check = false;
                    break;
                }
                int[] next = nextBulb(start[0],start[1],lightBulbs);
                start[0] = next[0];
                start[1] = next[1];
            }
        }
        return check;
    }
    static int[] nextBulb(int x, int y, int[][] lightBulbs){
        int[] next = {x,y+1};
        if(next[1]==lightBulbs[0].length){
            next[0] = x+1;
            next[1] = 0;
        }
        return next;
    }
    static void pressSwitch(int x, int y, int press, int[][] lightBulbs){
        for (int i = 0; i < directions.length; i++) {
            if(x+directions[i][0]>=0 && x+directions[i][0]<lightBulbs.length && y+directions[i][1]>=0 && y+directions[i][1]<lightBulbs[0].length){
                lightBulbs[x+directions[i][0]][y+directions[i][1]]+=press;
            }
        }
    }
}

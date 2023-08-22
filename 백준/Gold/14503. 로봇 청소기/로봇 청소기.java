import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[][] directions = {{-1,0},{0,1},{1,0},{0,-1}};
    static int currentDirection = 0;
    static int cleaned = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int rows = Integer.parseInt(st.nextToken());
        int columns = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        currentDirection = Integer.parseInt(st.nextToken());

        int[][] room = new int[rows][columns];
        for (int i = 0; i < room.length; i++) {
            room[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        runVaccum(x,y,room);
        System.out.println(cleaned);
    }
    static void runVaccum(int x, int y, int[][] room){
        while (true){
            // 1. Check if current floor can be cleaned
            if(room[x][y]==0){
                cleaned+=1;
                room[x][y] = -1;
            }
            // 2. Search each four floors
            boolean check = false;
            for (int i = 0; i < directions.length; i++) {
                int nextX = x+directions[i][0];
                int nextY = y+directions[i][1];
                if(checkAbility(nextX,nextY,room)){
                    check = true;
                    break;
                }
            }

            // 2.1. Rotate counterClockwise, check if can be cleaned
            if(check){
                counterClockwiseTurn();
                int nextX = x+directions[currentDirection][0];
                int nextY = y+directions[currentDirection][1];
                if(checkAbility(nextX,nextY,room)){
                    x = nextX;
                    y = nextY;
                }
            }
            // 2.2. if there is nowhere to go, go backwards
            else {
                int beforeX = x-directions[currentDirection][0];
                int beforeY = y-directions[currentDirection][1];

                if(checkWall(beforeX,beforeY,room)){
                    x = beforeX;
                    y = beforeY;
                }
                else {
                    break;
                }
            }
        }
    }
    static void counterClockwiseTurn(){
        int newdir = currentDirection-1;
        if(newdir<0){
            newdir = 3;
        }
        currentDirection = newdir;
    }
    static boolean checkAbility(int x, int y, int[][] room){
        if(x>=0 && x<room.length && y>=0 && y<room[0].length && room[x][y]==0){
            return true;
        }
        return false;
    }
    static boolean checkWall(int x, int y, int[][] room){
        if(x>=0 && x<room.length && y>=0 && y<room[0].length && room[x][y]!=1){
            return true;
        }
        return false;
    }
}

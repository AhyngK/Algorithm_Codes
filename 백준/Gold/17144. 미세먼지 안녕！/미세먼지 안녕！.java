import java.io.*;
import java.util.*;
public class Main {
    static int[][] directions = {{1,0},{0,1},{-1,0},{0,-1}};
    static int totalTime = 0;
    static int[][] purifier;
    static int[][] visitCheck;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int rows = Integer.parseInt(st.nextToken());
        int columns = Integer.parseInt(st.nextToken());
        totalTime = Integer.parseInt(st.nextToken());

        map = new int[rows][columns];
        for (int i = 0; i < map.length; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        runTime();
        System.out.println(collectDust());
    }
    static void runTime(){
        int time = 0;
        while (true){
            time+=1;
            Queue<int[]> dusts = findDusts();
            dustSpread(dusts);
            purifyStart();
            if(time==totalTime){
                break;
            }
        }
    }
    static void purifyStart(){
        int x1 = Math.min(purifier[0][0],purifier[1][0]);
        int x2 = Math.max(purifier[0][0],purifier[1][0]);
        eachPurifyCycle(x1,0);
        eachPurifyCycle(x2,1);
    }
    static void eachPurifyCycle(int x, int dir){
        int startX = x;
        int startY = 1;

        int temp = 0;

        while (startY<map[0].length){
            int temp1 = map[startX][startY];
            map[startX][startY] = temp;
            temp= temp1;
            startY+=1;
        }

        int currentDir = dir==0 ? -1 : 1;
        startX+=currentDir;
        startY-=1;
        while (startX>=0 && startX<map.length){
            int temp1 = map[startX][startY];
            map[startX][startY] = temp;
            temp= temp1;
            startX+=currentDir;
        }

        startX-=currentDir;
        startY-=1;
        while (startY>=0){
            int temp1 = map[startX][startY];
            map[startX][startY] = temp;
            temp= temp1;
            startY-=1;
        }

        currentDir*=-1;
        startX+=currentDir;
        startY+=1;
        if(dir==0){
            while (startX<x){
                int temp1 = map[startX][startY];
                map[startX][startY] = temp;
                temp= temp1;
                startX+=currentDir;
            }
        }
        else {
            while (startX>x){
                int temp1 = map[startX][startY];
                map[startX][startY] = temp;
                temp= temp1;
                startX+=currentDir;
            }
        }
    }
    static void dustSpread(Queue<int[]> dusts){
        int[][] newMap = new int[map.length][map[0].length];
        newMap[purifier[0][0]][purifier[0][1]] = -1;
        newMap[purifier[1][0]][purifier[1][1]] = -1;

        while (!dusts.isEmpty()){
            int[] temp = dusts.poll();

            int count = 0;
            for (int i = 0; i < directions.length; i++) {
                int nextX = temp[0]+directions[i][0];
                int nextY = temp[1]+directions[i][1];
                if(dustSpreadCheck(nextX,nextY)){
                    count+=1;
                    newMap[nextX][nextY] += map[temp[0]][temp[1]]/5;
                }
            }
            newMap[temp[0]][temp[1]] = newMap[temp[0]][temp[1]] + map[temp[0]][temp[1]] - map[temp[0]][temp[1]]/5*count;
        }
        map = newMap;
    }
    static boolean dustSpreadCheck(int x, int y){
        if(x>=0 && x<map.length && y>=0 && y<map[0].length && map[x][y]>=0){
            return true;
        }
        return false;
    }
    static Queue<int[]> findDusts(){
        int count = 0;
        purifier = new int[2][2];
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if(map[i][j]>0){
                    queue.add(new int[] {i,j});
                }
                if(map[i][j]==-1){
                    purifier[count] = new int[] {i,j};
                    count++;
                }
            }
        }
        return queue;
    }
    static int collectDust(){
        int temp = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if(map[i][j]>0){
                    temp+=map[i][j];
                }
            }
        }
        return temp;
    }
}

import java.io.*;
import java.util.*;

public class Main {
    static int[][] map;
    static int[][] distance;
    static int[][] directions = {{0,1},{1,0},{0,-1},{-1,0}};
    static List<Integer> lands;
    static int smallestBridge = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        map = new int[size][size];
        distance = new int[size][size];

        for (int i = 0; i < map.length; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Arrays.fill(distance[i],Integer.MAX_VALUE);
        }
        int count = 100;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if(map[i][j] == 1){
                    mapLands(i,j,count);
                    count++;
                }
            }
        }

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if(map[i][j]>0 && endLandCheck(i,j)){
                    buildBridge(i,j,map[i][j]);
                }
            }
        }
        System.out.println(smallestBridge-2);
    }
    static void buildBridge(int x, int y, int count){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {x,y});
        int currentCount = 0;

        label : while (!queue.isEmpty()){
            currentCount+=1;
            if(currentCount>=smallestBridge){
                break;
            }
            Queue<int[]> tempQue = new LinkedList<>();
            while (!queue.isEmpty()){
                int[] temp = queue.poll();
                if(map[temp[0]][temp[1]]!=0 && map[temp[0]][temp[1]]!=count){
                    smallestBridge = Math.min(smallestBridge,currentCount);
                    return;
                }
                for (int i = 0; i < directions.length; i++) {
                    int[] next = {temp[0]+directions[i][0], temp[1]+directions[i][1]};
                    if(borderCheck(next) && map[next[0]][next[1]]!=count && distance[next[0]][next[1]]>currentCount){
                        tempQue.add(next);
                        distance[next[0]][next[1]] = currentCount;
                    }
                }
            }
            queue = tempQue;
        }
    }
    static void mapLands(int x, int y, int count){
        Queue<int[]> queue = new LinkedList<>();
        map[x][y] = count;
        queue.add(new int[] {x,y});
        while (!queue.isEmpty()){
            int[] temp = queue.poll();
            for (int i = 0; i < directions.length; i++) {
                int[] next = {temp[0]+directions[i][0], temp[1]+directions[i][1]};
                if(borderCheck(next) && map[next[0]][next[1]]==1){
                    map[next[0]][next[1]] = count;
                    queue.add(next);
                }
            }
        }
    }
    static boolean endLandCheck(int x, int y){
        int count = 0;
        for (int i = 0; i < directions.length; i++) {
            int[] next = {x+directions[i][0],y+directions[i][1]};
            if(borderCheck(next) && map[next[0]][next[1]]!=map[x][y]){
                count++;
            }
        }
        if(count>1){
            return true;
        }
        return false;
    }
    static boolean borderCheck(int[] next){
        if(next[0]>=0 && next[0]< map.length && next[1]>=0 && next[1]< map.length){
            return true;
        }
        return false;
    }
}
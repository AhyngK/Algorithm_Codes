import java.io.*;
import java.util.*;

public class Main {
    static int[][] directions = {{1,0},{0,1},{-1,0},{0,-1}};
    static int sharkSize = 2;
    static int fishNum = 0;
    static int time = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int mapsize = Integer.parseInt(br.readLine());
        int[][] map = new int[mapsize][mapsize];
        for (int i = 0; i < map.length; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int[] shark = findShark(map);

        moveShark(shark,map);
        System.out.println(time);
    }
    static void moveShark(int[] shark, int[][] map){
        while (true){
            map[shark[0]][shark[1]] = 0;
            int[] sharkNext = selectCoordinate(shark[0],shark[1],map);
            if(sharkNext[0]==-1){
                break;
            }
            time+=sharkNext[2];
            shark = sharkNext;
            fishNum+=1;
            if(sharkSize==fishNum){
                sharkSize+=1;
                fishNum = 0;
            }
        }
    }
    static int[] selectCoordinate(int x, int y, int[][] map){
        List<int[]> coordinates = new ArrayList<>();
        int[][] visit = new int[map.length][map.length];
        int count = 0;

        Queue<int[]> que = new LinkedList<>();
        que.add(new int[] {x,y});

        while (!que.isEmpty()){
            count+=1;
            Queue<int[]> queTemp = new LinkedList<>();
            while (!que.isEmpty()){
                int[] temp = que.poll();
                for (int i = 0; i < directions.length; i++) {
                    int[] next = {temp[0]+ directions[i][0], temp[1]+directions[i][1]};
                    if(next[0]>=0 && next[1]>=0 && next[0]< map.length && next[1]<map[0].length && visit[next[0]][next[1]]==0){
                        if(map[next[0]][next[1]]>0 && map[next[0]][next[1]]<sharkSize){
                            coordinates.add(new int[] {next[0],next[1],count});
                        }
                        else if(map[next[0]][next[1]]<=sharkSize) {
                            queTemp.add(next);
                        }
                        visit[next[0]][next[1]] = 1;
                    }
                }
            }
            if(!coordinates.isEmpty()){
                break;
            }
            que = queTemp;
        }
        return sortList(coordinates);
    }
    static int[] sortList(List<int[]> coordinates){
        int[] nextCoor = {-1,-1,-1};

        Comparator<int[]> comparator = new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0]==o2[0]){
                    return o1[1]-o2[1];
                }
                else {
                    return o1[0]-o2[0];
                }
            }
        };

        if(coordinates.size()!=0){
            Collections.sort(coordinates,comparator);
            nextCoor = coordinates.get(0);
        }
        return nextCoor;
    }
    static int[] findShark(int[][] map){
        int[] currentCoor = new int[2];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if(map[i][j]==9){
                    currentCoor[0] = i;
                    currentCoor[1] = j;
                }
            }
        }
        return currentCoor;
    }
}

import java.io.*;
import java.util.*;

public class Main {
    static List<String> doors = new ArrayList<>();
    static List<String> doorKeys = new ArrayList<>();
    static int[][] directions = {{0,1},{1,0},{0,-1},{-1,0}};
    static String[][] map;
    static boolean check = false;

    public static void main(String[] args) throws IOException {
        doors = new LinkedList<>();
        doors.add("A");
        doors.add("B");
        doors.add("C");
        doors.add("D");
        doors.add("E");
        doors.add("F");
        doorKeys.add("a");
        doorKeys.add("b");
        doorKeys.add("c");
        doorKeys.add("d");
        doorKeys.add("e");
        doorKeys.add("f");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int rows = Integer.parseInt(st.nextToken());
        int columns = Integer.parseInt(st.nextToken());
        map = new String[rows][columns];
        for (int i = 0; i < map.length; i++) {
            map[i] = br.readLine().split("");
        }
        int[][] coors = findCoor();
        int[] start = coors[0];
        int[] end = coors[1];
        int total = run(start,end)-1;
        if(check){
            System.out.println(total);
        }
        else {
            System.out.println(-1);
        }
    }
    static int run(int[] start, int[] end){
        int[][][][][][][][] visited = new int[map.length][map[0].length][2][2][2][2][2][2];
        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited[i].length; j++) {
                for (int k = 0; k < visited[i][j].length; k++) {
                    for (int l = 0; l < visited[i][j][k].length; l++) {
                        for (int m = 0; m < visited[i][j][k][l].length; m++) {
                            for (int n = 0; n < visited[i][j][k][l][m].length; n++) {
                                for (int o = 0; o < visited[i][j][k][l][m][n].length; o++) {
                                    Arrays.fill(visited[i][j][k][l][m][n][o],Integer.MAX_VALUE);
                                }
                            }
                        }
                    }
                }
            }
        }
        visited[start[0]][start[1]][0][0][0][0][0][0] = 0;

        Player startPlayer = new Player(start[0],start[1],new int[6]);
        int count = 0;
        Queue<Player> que = new LinkedList<>();
        que.add(startPlayer);

        label : while (!que.isEmpty()){
            Queue<Player> tempQue = new LinkedList<>();
            count++;
            while (!que.isEmpty()){
                Player currentPlayer = que.poll();
                //System.out.println(currentPlayer.toString());
                if(map[currentPlayer.currentX][currentPlayer.currentY].equals("1")){
                    check = true;
                    break label;
                }
                for (int i = 0; i < directions.length; i++) {
                    int nextX = currentPlayer.currentX+directions[i][0];
                    int nextY = currentPlayer.currentY+directions[i][1];
                    int[] nextKeys = currentPlayer.keys.clone();
                    if(borderCheck(nextX,nextY) && !map[nextX][nextY].equals("#")){
                        if(doors.contains(map[nextX][nextY]) && currentPlayer.keys[doors.indexOf(map[nextX][nextY])]==0){
                            continue;
                        }
                        if(doorKeys.contains(map[nextX][nextY])){
                            nextKeys[doorKeys.indexOf(map[nextX][nextY])] = 1;
                        }
                        if(visited[nextX][nextY][nextKeys[0]][nextKeys[1]][nextKeys[2]][nextKeys[3]][nextKeys[4]][nextKeys[5]] > count){
                            visited[nextX][nextY][nextKeys[0]][nextKeys[1]][nextKeys[2]][nextKeys[3]][nextKeys[4]][nextKeys[5]] = count;
                            Player nextPlayer = new Player(nextX,nextY,nextKeys);
                            tempQue.add(nextPlayer);
                        }
                    }
                }
            }
            que = tempQue;
        }
        return count;
    }
    static boolean borderCheck(int x, int y){
        if(x>=0 && x< map.length && y>=0 && y<map[0].length){
            return true;
        }
        return false;
    }
    static class Player{
        int currentX;
        int currentY;
        int[] keys;

        public Player(int currentX, int currentY, int[] keys) {
            this.currentX = currentX;
            this.currentY = currentY;
            this.keys = keys;
        }

        @Override
        public String toString() {
            return "Player{" +
                    "currentX=" + currentX +
                    ", currentY=" + currentY +
                    ", keys=" + Arrays.toString(keys) +
                    '}';
        }
    }
    static int[][] findCoor(){
        int[][] coor = new int[2][2];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if(map[i][j].equals("0")){
                    coor[0][0] = i;
                    coor[0][1] = j;
                }
                if(map[i][j].equals("1")){
                    coor[1][0] = i;
                    coor[1][1] = j;
                }
            }
        }
        return coor;
    }
}
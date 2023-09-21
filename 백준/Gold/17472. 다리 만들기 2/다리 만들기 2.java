import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] map;
    static List<Bridge>[][] connected;
    static List<Bridge> bridges;
    static int[] roots;
    static int[][] directions = {{0,1},{1,0},{0,-1},{-1,0}};
    static List<Integer>[] selected;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int rows = Integer.parseInt(st.nextToken());
        int columns = Integer.parseInt(st.nextToken());
        map = new int[rows][columns];
        connected = new List[rows][columns];

        for (int i = 0; i < map.length; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int count = 2;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                connected[i][j] = new ArrayList<>();
                if(map[i][j] == 1){
                    mapIslands(i,j,count);
                    count++;
                }
            }
        }
        bridges = new ArrayList<>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if(map[i][j]>0 && edgeCheck(i,j)){
                    findBridges(i,j);
                }
            }
        }

        roots = new int[count];
        for (int i = 0; i < roots.length; i++) {
            roots[i] = i;
        }
        selected = new List[roots.length];
        for (int i = 0; i < selected.length; i++) {
            selected[i] = new ArrayList<>();
        }

        int ans = search();
        if(connected()){
            System.out.println(ans);
        }
        else {
            System.out.println(-1);
        }
    }
    static boolean connected(){
        boolean[] check = new boolean[roots.length];
        check[2] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(2);
        while (!queue.isEmpty()){
            int current = queue.poll();
            for (int i = 0; i < selected[current].size(); i++) {
                if(!check[selected[current].get(i)]){
                    check[selected[current].get(i)] = true;
                    queue.add(selected[current].get(i));
                }
            }
        }
        boolean toReturn = true;
        for (int i = 2; i < check.length; i++) {
            if(!check[i]){
                toReturn = false;
                break;
            }
        }
        return toReturn;
    }
    static int search(){
        Collections.sort(bridges);
        int totalLength = 0;
        for (int i = 0; i < bridges.size(); i++) {
            int isl1 = map[bridges.get(i).x1][bridges.get(i).y1];
            int isl2 = map[bridges.get(i).x2][bridges.get(i).y2];
            int root1 = find(isl1);
            int root2 = find(isl2);
            if(root1 < root2){
                roots[root2] = root1;
                selected[isl1].add(isl2);
                selected[isl2].add(isl1);
                totalLength+=bridges.get(i).length;
            }
            else if (root2 > root1) {
                roots[root1] = root2;
                selected[isl1].add(isl2);
                selected[isl2].add(isl1);
                totalLength+=bridges.get(i).length;
            }
        }
        return totalLength;
    }
    static int find(int isl){
        if(roots[isl] == isl){
            return isl;
        }
        return find(roots[isl]);
    }

    static void findBridges(int x, int y){
        for (int k = 0; k < directions.length; k++) {
            int nextX = x+directions[k][0];
            int nextY = y + directions[k][1];;
            int length = 1;
            while (borderCheck(nextX,nextY)){
                if(map[nextX][nextY] != 0){
                    length--;
                    break;
                }
                length++;
                nextX+=directions[k][0];
                nextY+=directions[k][1];
            }
            if(length>1 && borderCheck(nextX,nextY) && map[nextX][nextY]!=map[x][y]){
                Bridge temp = new Bridge(x,y,nextX,nextY,length);
                Bridge temp1 = new Bridge(nextX,nextY,x,y,length);
                if(!alreadyMade(temp)){
                    bridges.add(temp);
                    connected[temp.x1][temp.y1].add(temp);
                    connected[temp1.x1][temp1.y1].add(temp);
                }
            }
        }
    }
    static boolean alreadyMade(Bridge bridge){
        for (int i = 0; i < connected[bridge.x1][bridge.y1].size(); i++) {
            if(connected[bridge.x1][bridge.y1].get(i).x2 == bridge.x2 && connected[bridge.x1][bridge.y1].get(i).y2 == bridge.y2){
                return true;
            }
        }
        return false;
    }

    static void mapIslands(int i, int j, int count){
        map[i][j] = count;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {i,j});
        while (!queue.isEmpty()){
            int[] temp = queue.poll();
            for (int k = 0; k < directions.length; k++) {
                int x = temp[0]+directions[k][0];
                int y = temp[1]+directions[k][1];
                if(borderCheck(x,y) && map[x][y] == 1){
                    map[x][y] = count;
                    queue.add(new int[] {x,y});
                }
            }
        }
    }
    static boolean edgeCheck(int x, int y){
        boolean check = false;
        for (int k = 0; k < directions.length; k++) {
            int i = x+directions[k][0];
            int j = y+directions[k][1];
            if(borderCheck(i,j) && map[i][j] == 0){
                check = true;
                break;
            }
        }
        return check;
    }
    static boolean borderCheck (int x, int y){
        if(x>=0 && x< map.length && y>=0 && y<map[0].length){
            return true;
        }
        return false;
    }
    static class Bridge implements Comparable<Bridge>{
        int x1;
        int y1;
        int x2;
        int y2;
        int length;

        public Bridge(int x1, int y1, int x2, int y2, int length) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.length = length;
        }

        @Override
        public int compareTo(Bridge o) {
            return this.length - o.length;
        }

        @Override
        public String toString() {
            return "Bridge{" +
                    "x1=" + x1 +
                    ", y1=" + y1 +
                    ", x2=" + x2 +
                    ", y2=" + y2 +
                    ", length=" + length +
                    '}';
        }
    }
}
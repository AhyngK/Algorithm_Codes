import java.io.*;
import java.util.*;

public class Main {
    static int[][] map;
    static List<int[]> houses = new ArrayList<>();
    static List<int[]> chickens = new ArrayList<>();
    static List<int[]>[] eachLength;
    static int[][] directions = {{0,1},{1,0},{0,-1},{-1,0}};
    static int smallestCityDis = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int size = Integer.parseInt(st.nextToken());
        int surviveNum = Integer.parseInt(st.nextToken());

        map = new int[size][size];
        for (int i = 0; i < map.length; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        findHouseChicken();

        eachLength = new List[houses.size()];
        for (int i = 0; i < eachLength.length; i++) {
            eachLength[i] = new ArrayList<>();
        }
        eachDistance();
        sortDistance();

        int removeNum = chickens.size()-surviveNum;
        List<Integer> selected = new ArrayList<>();
        selection(0,removeNum,selected,-1);
        System.out.println(smallestCityDis);
    }

    // Decide which chicken not to close
    static void selection(int index, int removeNum, List<Integer> selected, int beforeSelectedIndex){
        // End Point : selection complete
        if(index==removeNum){
            checkCityDistance(selected);
            return;
        }

        for (int i = beforeSelectedIndex+1; i < chickens.size(); i++) {
            selected.add(i);
            selection(index+1, removeNum, selected, i);
            selected.remove(selected.size()-1);
        }
    }
    static void checkCityDistance(List<Integer> selected){
        int distanceSum = 0;
        for (int i = 0; i < eachLength.length; i++) {
            for (int j = 0; j < eachLength[i].size(); j++) {
                if(!selected.contains(eachLength[i].get(j)[0])){
                    distanceSum += eachLength[i].get(j)[1];
                    break;
                }
            }
        }
        smallestCityDis = Math.min(smallestCityDis,distanceSum);
    }

    // Map all smallest chicken distance from each house
    static void eachDistance(){
        for (int i = 0; i < houses.size(); i++) {
            int count = 0;
            Queue<int[]> que = new LinkedList<>();
            boolean[][] visit = new boolean[map.length][map.length];
            visit[houses.get(i)[0]][houses.get(i)[1]] = true;
            que.add(new int[] {houses.get(i)[0], houses.get(i)[1], 0});

            while (!que.isEmpty() || count<chickens.size()){
                int[] temp = que.poll();
                for (int j = 0; j < chickens.size(); j++) {
                    if(temp[0] == chickens.get(j)[0] && temp[1] == chickens.get(j)[1]){
                        eachLength[i].add(new int[] {j,temp[2]});
                        count+=1;
                    }
                }

                for (int j = 0; j < directions.length; j++) {
                    int[] next = {temp[0]+directions[j][0],temp[1]+directions[j][1],temp[2]+1};
                    if(borderCheck(next) && !visit[next[0]][next[1]]){
                        visit[next[0]][next[1]] = true;
                        que.add(next);
                    }
                }
            }
        }
    }
    // Sort Each House Distance by distance [chickenIndex, distance]
    static void sortDistance(){
        Comparator<int[]> comparator = new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1]-o2[1];
            }
        };
        for (int i = 0; i < eachLength.length; i++) {
            Collections.sort(eachLength[i],comparator);
        }
    }
    static void findHouseChicken(){
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if(map[i][j]==1){
                    houses.add(new int[] {i,j});
                }
                if(map[i][j]==2){
                    chickens.add(new int[] {i,j});
                }
            }
        }
    }
    static boolean borderCheck(int[] next){
        if(next[0]>=0 && next[0]< map.length && next[1]>=0 && next[1]< map.length){
            return true;
        }
        return false;
    }
}
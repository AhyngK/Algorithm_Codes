import java.io.*;
import java.util.*;

public class Solution {
    static int[][] map;
    static int[][] directions = {{1,0},{-1,0}};
    static List<int[]>[] toSmash;
    static int smallestBrickLeft = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int testCase = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= testCase; tc++) {
            smallestBrickLeft = Integer.MAX_VALUE;
            st = new StringTokenizer(br.readLine());
            int totalShot = Integer.parseInt(st.nextToken());
            int width = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken());

            map = new int[height][width];
            for (int i = 0; i < map.length; i++) {
                map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }

            List<Integer>[]  listMap = new List[width];
            for (int i = 0; i < map[0].length; i++) {
                listMap[i] = new ArrayList<>();
                for (int j = map.length-1; j >= 0; j--) {
                    if(map[j][i]!=0){
                        listMap[i].add(map[j][i]);
                    }
                }
            }

            int[] selected = new int[totalShot];
            selectWhereToHit(0,selected, listMap);
            System.out.println("#"+tc+" "+smallestBrickLeft);
        }
    }
    static void selectWhereToHit(int index, int[] selected, List<Integer>[]  listMap){
        if(index == selected.length){
            makeTempList(selected,listMap);
            return;
        }
        for (int i = 0; i < map[0].length; i++) {
            selected[index] = i;
            selectWhereToHit(index+1, selected, listMap);
        }
    }
    static void makeTempList(int[] selected, List<Integer>[]  listMap){
        List<Integer>[] tempMap = new List[listMap.length];
        for (int i = 0; i < tempMap.length; i++) {
            tempMap[i] = new ArrayList<>(listMap[i]);
        }

        for (int i = 0; i < selected.length; i++) {
                toSmash = new List[tempMap.length];
                for (int j = 0; j < toSmash.length; j++) {
                    toSmash[j] = new ArrayList<>();
                }

                int currentY = tempMap[selected[i]].size()-1;
                if(currentY>-1){
                    toSmash[selected[i]].add(new int[] {selected[i],tempMap[selected[i]].size()-1,1});
                }
                else {
                    continue;
                }

                addBrick(tempMap, selected[i], tempMap[selected[i]].size()-1);
                while (true){
                    boolean check = true;
                    for (int k = 0; k < toSmash.length; k++) {
                        for (int j = 0; j < toSmash[k].size(); j++) {
                            if(toSmash[k].get(j)[2] == 0){
                                check = false;
                                toSmash[k].get(j)[2] = 1;
                                addBrick(tempMap,toSmash[k].get(j)[0],toSmash[k].get(j)[1]);
                            }
                        }
                    }

                    if(check){
                        break;
                    }
                }
                removeBrick(tempMap);
        }

        countLeftBricks(tempMap);
    }
    static void addBrick(List<Integer>[] tempMap, int x, int y){
        // Add X coordinates
        int brickNum  = tempMap[x].get(y);
        for (int j = 0; j < directions.length; j++) {
            for (int k = 1; k <= brickNum - 1; k++) {
                int nextX = x + directions[j][0] * k;
                int nextY = y + directions[j][1] * k;
                if (borderCheck(nextX, nextY, tempMap) && !containCheck(nextX,nextY)) {
                    toSmash[nextX].add(new int[]{nextX, nextY, 0});
                }
            }
        }
        // Add Y coordinates
        for (int i = 1; i < brickNum; i++) {
            int nextX = x;
            int nextY = y - i;
            int nextY2 = y + i;
            if (borderCheck(nextX, nextY, tempMap) && !containCheck(nextX,nextY)) {
                toSmash[nextX].add(new int[]{nextX, nextY, 0});
            }
            if (borderCheck(nextX, nextY2, tempMap) && !containCheck(nextX,nextY2)) {
                toSmash[nextX].add(new int[]{nextX, nextY2, 0});
            }
        }
    }
    static void removeBrick(List<Integer>[]  listMap){
        Comparator<int[]> comparator = new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1]-o1[1];
            }
        };

        for (int i = 0; i < toSmash.length; i++) {
            Collections.sort(toSmash[i],comparator);
            for (int j = 0; j < toSmash[i].size(); j++) {
                listMap[i].remove(toSmash[i].get(j)[1]);
            }
        }
    }
    static void countLeftBricks(List<Integer>[] tempMap){
        int count = 0;
        for (int i = 0; i < tempMap.length; i++) {
            count+=tempMap[i].size();
        }
        smallestBrickLeft = Math.min(smallestBrickLeft,count);
    }
    static boolean containCheck(int x, int y){
        for (int i = 0; i < toSmash[x].size(); i++) {
            if(toSmash[x].get(i)[1]==y){
                return true;
            }
        }
        return false;
    }
    static boolean borderCheck(int x, int y, List<Integer>[]  listMap){
        if(x>=0 && x<listMap.length && y>=0 && y<listMap[x].size()){
            return true;
        }
        return false;
    }
}
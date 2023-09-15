import java.io.*;
import java.util.*;

public class Solution {
    static List<Micro>[][] map;
    static int totalTime;
    static Queue<Micro> microQue;
    static int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int testCase = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= testCase; tc++) {
            st = new StringTokenizer(br.readLine());
            int mapSize = Integer.parseInt(st.nextToken());
            totalTime = Integer.parseInt(st.nextToken());
            int microNum = Integer.parseInt(st.nextToken());

            map = new List[mapSize][mapSize];
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    map[i][j] = new ArrayList<>();
                }
            }

            microQue = new LinkedList<>();
            for (int i = 0; i < microNum; i++) {
                st = new StringTokenizer(br.readLine());
                Micro micro = new Micro();
                micro.coorX = Integer.parseInt(st.nextToken());
                micro.coorY = Integer.parseInt(st.nextToken());
                micro.size = Integer.parseInt(st.nextToken());
                micro.dir = Integer.parseInt(st.nextToken())-1;
                map[micro.coorX][micro.coorY].add(micro);
                microQue.add(micro);
            }

            for (int i = 0; i < totalTime; i++) {
                moveMicro();
            }
            System.out.println("#"+tc+" "+totalSize());
        }
    }
    static void moveMicro(){
        List<Micro>[][] tempMap = new List[map.length][map[0].length];
        for (int i = 0; i < tempMap.length; i++) {
            for (int j = 0; j < tempMap[0].length; j++) {
                tempMap[i][j] = new ArrayList<>();
            }
        }

        while (!microQue.isEmpty()){
            Micro temp = microQue.poll();
            temp.coorX += directions[temp.dir][0];
            temp.coorY += directions[temp.dir][1];
            temp = metEnd(temp);
            if(temp.size != 0){
                tempMap[temp.coorX][temp.coorY].add(temp);
            }
        }
        mergeMicro(tempMap);
    }
    static void mergeMicro(List<Micro>[][] tempMap){
        for (int i = 0; i < tempMap.length; i++) {
            for (int j = 0; j < tempMap[0].length; j++) {
                if(tempMap[i][j].size()>1){
                    Collections.sort(tempMap[i][j]);
                    Micro merged = new Micro();
                    int mergeSize = 0;
                    for (int k = 0; k < tempMap[i][j].size(); k++) {
                        mergeSize += tempMap[i][j].get(k).size;
                    }
                    merged.coorX = i;
                    merged.coorY = j;
                    merged.size = mergeSize;
                    merged.dir = tempMap[i][j].get(0).dir;
                    tempMap[i][j] = new ArrayList<>();
                    tempMap[i][j].add(merged);
                    microQue.add(merged);
                }
                else if (!tempMap[i][j].isEmpty()) {
                    microQue.add(tempMap[i][j].get(tempMap[i][j].size()-1));
                }
            }
        }
    }
    static Micro metEnd(Micro micro){
        if(micro.coorX == map.length-1 || micro.coorX == 0 || micro.coorY == map[0].length-1 || micro.coorY == 0){
            micro.size /= 2;
            micro.dir = reverseDir(micro.dir);
        }
        return micro;
    }
    static int totalSize(){
        int total = 0;
        while (!microQue.isEmpty()){
            total+=microQue.poll().size;
        }
        return total;
    }
    static int reverseDir(int curDir){
        switch (curDir){
            case 0 : curDir = 1; break;
            case 1 : curDir = 0; break;
            case 2 : curDir = 3; break;
            case 3 : curDir = 2; break;
        }
        return curDir;
    }

    static class Micro implements Comparable<Micro> {
        int coorX;
        int coorY;
        int size;
        int dir;

        @Override
        public String toString() {
            return "Micro{" +
                    "coorX=" + coorX +
                    ", coorY=" + coorY +
                    ", size=" + size +
                    ", dir=" + dir +
                    '}';
        }

        @Override
        public int compareTo(Micro o) {
            return o.size - this.size;
        }
    }
}
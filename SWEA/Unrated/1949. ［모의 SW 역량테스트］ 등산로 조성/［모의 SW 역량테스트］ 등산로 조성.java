import java.io.*;
import java.util.*;

public class Solution {
    static int[][] originalMap;
    static int[][] directions = {{0,1},{1,0},{0,-1},{-1,0}};
    static List<int[]> startCoors;
    static int maxCut;
    static int maxLength = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int testCase = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= testCase; tc++) {
            st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());
            maxCut = Integer.parseInt(st.nextToken());
            maxLength = Integer.MIN_VALUE;

            originalMap = new int[size][size];
            int maxHeight = 0;
            for (int i = 0; i < originalMap.length; i++) {
                originalMap[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                for (int j = 0; j < originalMap[i].length; j++) {
                    maxHeight = Math.max(maxHeight,originalMap[i][j]);
                }
            }
            findMaxCoors(maxHeight);
            for (int i = 0; i < startCoors.size(); i++) {
                run(startCoors.get(i));
            }
            System.out.println("#"+tc+" "+maxLength);
        }
    }
    static void run(int[] start){
        Queue<Node> queue = new LinkedList<>();
        // 0: curX, 1: curY, 2: cutted, 3: beforeX, 4: beforeY, 5: dis, 6:cuttedX, 7:cuttedY, 8: cuttedHeight
        Node first = new Node(start[0], start[1], 1,0,new ArrayList<>());
        first.visited.add(new int[] {start[0], start[1]});
        queue.add(first);

        while (!queue.isEmpty()){
            Node temp = queue.poll();
            maxLength = Math.max(temp.dis,maxLength);

            for (int i = 0; i < directions.length; i++) {
                Node next = new Node(temp.x+ directions[i][0], temp.y+directions[i][1], temp.dis+1,temp.cut,new ArrayList<>(temp.visited));
                next.cuttedHeight = temp.cuttedHeight;
                next.cut = temp.cut > 0 ? 1 : 0;
                int beforeHeight = temp.cut == 3? temp.cuttedHeight : originalMap[temp.x][temp.y];

                if(borderCheck(next) && vischeck(next)){
                    if (beforeHeight > originalMap[next.x][next.y]){
                        next.visited.add(new int[] {next.x,next.y});
                        queue.add(next);
                    }
                    else if (beforeHeight <= originalMap[next.x][next.y] && next.cut == 0) {
                        int height = originalMap[temp.x][temp.y] - 1;
                        int cut = originalMap[next.x][next.y] - height;
                        if(cut<=maxCut){
                            next.cut = 3;
                            next.cuttedHeight = height;
                            next.visited.add(new int[] {next.x,next.y});
                            queue.add(next);
                        }
                    }
                }
            }
        }
    }
    static boolean vischeck(Node node){
        for (int i = 0; i < node.visited.size(); i++) {
            if(node.visited.get(i)[0] == node.x && node.visited.get(i)[1] == node.y){
                return false;
            }
        }
        return true;
    }
    static class Node{
        int x;
        int y;
        int dis;
        int cut;
        int cuttedHeight;
        List<int[]> visited;

        public Node() {
        }

        public Node(int x, int y, int dis, int cut, List<int[]> visited) {
            this.x = x;
            this.y = y;
            this.dis = dis;
            this.cut = cut;
            this.visited = visited;
        }
    }
    static boolean borderCheck(Node coor){
        if(coor.x>=0 && coor.x< originalMap.length && coor.y>=0 && coor.y<originalMap[0].length){
            return true;
        }
        return false;
    }
    static void findMaxCoors(int maxHeight ){
        startCoors = new ArrayList<>();
        for (int i = 0; i < originalMap.length; i++) {
            for (int j = 0; j < originalMap[0].length; j++) {
                if(originalMap[i][j] == maxHeight){
                    startCoors.add(new int[] {i,j});
                }
            }
        }
    }
}
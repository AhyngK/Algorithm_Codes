import java.io.*;
import java.util.*;

public class Main {
    static List<int[]>[] roads;
    static int smallest = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int shortCuts = Integer.parseInt(st.nextToken());
        int totalLength = Integer.parseInt(st.nextToken());

        roads = new List[totalLength+1];
        for (int i = 0; i < shortCuts; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int last = Integer.parseInt(st.nextToken());
            int shortCutDistance = Integer.parseInt(st.nextToken());
            if(start>=roads.length){
                continue;
            }
            if (roads[start] == null) {
                roads[start] = new ArrayList<>();
            }
            roads[start].add(new int[]{last, shortCutDistance});
        }
        move();
        System.out.println(smallest);
    }
    static void move(){
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[] {0,0});

        while (!que.isEmpty()){
            int[] temp = que.poll();
            if(temp[0] == roads.length-1){
                smallest = Math.min(smallest,temp[1]);
                continue;
            }

            if(roads[temp[0]]!=null){
                for (int i = 0; i < roads[temp[0]].size(); i++) {
                    int[] next = {roads[temp[0]].get(i)[0], temp[1]+roads[temp[0]].get(i)[1]};
                    if(next[0]< roads.length){
                        que.add(next);
                    }
                }
            }
            int[] next = {temp[0]+1, temp[1]+1};
            if(next[0]< roads.length){
                que.add(next);
            }
        }
    }
}

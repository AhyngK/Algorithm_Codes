import java.io.*;
import java.util.*;

public class Main {
    static List<Integer>[] graph;
    static int findDistance;
    static StringBuilder sb = new StringBuilder();
    static int[] eachDistance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int nodes = Integer.parseInt(st.nextToken());
        int roads = Integer.parseInt(st.nextToken());
        findDistance = Integer.parseInt(st.nextToken());
        int startNode = Integer.parseInt(st.nextToken());

        graph = new List[nodes+1];
        for (int i = 0; i < roads; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            if(graph[start] == null){
                graph[start] = new ArrayList<>();
            }
            graph[start].add(end);
        }

        eachDistance = new int[graph.length];
        Arrays.fill(eachDistance,Integer.MAX_VALUE);
        search(startNode);
        if(findNodeByDistance()){
            System.out.print(sb);
        }
        else {
            System.out.println(-1);
        }
    }
    static void search(int startNode){
        Comparator<int[]> comparator = new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1]-o2[1];
            }
        };

        PriorityQueue<int[]> que = new PriorityQueue<>(comparator);
        que.add(new int[] {startNode,0});
        eachDistance[startNode] = 0;
        while (!que.isEmpty()) {
            int[] current = que.poll();
            if (graph[current[0]] != null) {
                for (int i = 0; i < graph[current[0]].size(); i++) {
                    int[] next = {graph[current[0]].get(i), current[1] + 1};
                    if (eachDistance[next[0]] > next[1]) {
                        eachDistance[next[0]] = next[1];
                        que.add(next);
                    }
                }
            }
        }
    }
    static boolean findNodeByDistance(){
        for (int i = 0; i < eachDistance.length; i++) {
            if(eachDistance[i]==findDistance){
                sb.append(i).append("\n");
            }
        }

        if(sb.length()==0){
            return false;
        }
        return true;
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<int[]>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int nodes = Integer.parseInt(st.nextToken());
        int edges = Integer.parseInt(st.nextToken());
        int place = Integer.parseInt(st.nextToken());

        graph = new List[nodes+1];
        for (int i = 0; i <graph.length ; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < edges; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());
            graph[start].add(new int[] {end,distance});
        }

        int max = 0;
        for (int i = 1; i < graph.length; i++) {
            if(i!=place){
                int travelDis = run(i,place) + run(place,i);
                max = Math.max(travelDis,max);
            }
        }
        System.out.println(max);
    }
    static int run (int start, int end){
        int[] distance = new int[graph.length];
        Arrays.fill(distance,Integer.MAX_VALUE);
        distance[start] = 0;

        PriorityQueue<int[]> queue = new PriorityQueue<>(
                new Comparator<int[]>() {
                    @Override
                    public int compare(int[] o1, int[] o2) {
                        return o1[1] - o2[1];
                    }
                }
        );
        queue.add(new int[] {start,0});

        while (!queue.isEmpty()){
            int[] current = queue.poll();
            if(current[0] == end){
                break;
            }
            for (int i = 0; i < graph[current[0]].size(); i++) {
                int[] next = {graph[current[0]].get(i)[0], current[1]+graph[current[0]].get(i)[1]};
                if(distance[next[0]] > next[1]){
                    distance[next[0]] = next[1];
                    queue.add(next);
                }
            }
        }
        return distance[end];
    }
}
import java.io.*;
import java.util.*;

public class Main {
    static List<int[]>[] graph;
    static int smallest = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int nodes = Integer.parseInt(st.nextToken());
        int edges = Integer.parseInt(st.nextToken());
        graph = new List[nodes+1];
        for (int i = 0; i < edges; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cows = Integer.parseInt(st.nextToken());

            if(graph[start] == null){
                graph[start] = new ArrayList<>();
            }
            if(graph[end] == null){
                graph[end] = new ArrayList<>();
            }

            graph[start].add(new int[] {end,cows});
            graph[end].add(new int[] {start,cows});
        }
        search();
        System.out.println(smallest);

    }
    static void search(){
        Comparator<Tour> comparator = new Comparator<Tour>() {
            @Override
            public int compare(Tour o1, Tour o2) {
                return o1.distance-o2.distance;
            }
        };

        PriorityQueue<Tour> queue = new PriorityQueue<>(comparator);
        Tour first = new Tour(graph.length-1, 0, -1);
        queue.add(first);

        int[] distances = new int[graph.length];
        Arrays.fill(distances,-1);
        distances[distances.length-1] = 0;

        while (!queue.isEmpty()){
            Tour temp = queue.poll();

            if(temp.currentNode == 1){
                smallest = Math.min(smallest,temp.distance);
                break;
            }

            for (int i = 0; i < graph[temp.currentNode].size(); i++) {
                if(temp.visited != graph[temp.currentNode].get(i)[0]) {
                    Tour next = new Tour();
                    next.currentNode = graph[temp.currentNode].get(i)[0];
                    next.distance = temp.distance+graph[temp.currentNode].get(i)[1];
                    next.visited = temp.currentNode;
                    if(distances[next.currentNode] == -1 || distances[next.currentNode]> next.distance){
                        distances[next.currentNode] = next.distance;
                        queue.add(next);
                    }
                }
            }
        }
    }
    static class Tour{
        int currentNode;
        int distance;
        int visited;

        public Tour() {
        }

        public Tour(int currentNode, int distance, int visited) {
            this.currentNode = currentNode;
            this.distance = distance;
            this.visited = visited;
        }

        @Override
        public String toString() {
            return "Tour{" +
                    "currentNode=" + currentNode +
                    ", distance=" + distance +
                    ", visited=" + visited +
                    '}';
        }
    }
}
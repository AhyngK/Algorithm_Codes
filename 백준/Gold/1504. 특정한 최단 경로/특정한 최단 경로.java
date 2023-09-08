import java.io.*;
import java.util.*;

public class Main {
    static List<int[]>[] graph;
    static int totalCount = 0;

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
            int distance = Integer.parseInt(st.nextToken());

            if(graph[start] == null){
                graph[start] = new ArrayList<>();
            }
            if(graph[end] == null){
                graph[end] = new ArrayList<>();
            }
            graph[start].add(new int[] {end,distance});
            graph[end].add(new int[] {start,distance});
        }
        st = new StringTokenizer(br.readLine());
        int mustNode1 = Integer.parseInt(st.nextToken());
        int mustNode2 = Integer.parseInt(st.nextToken());

        int case1 = 0;
        int case1First = search(1,mustNode1);
        int case1Second = search(mustNode1,mustNode2);
        int case1Third = search(mustNode2,graph.length-1);

        if(case1First == Integer.MAX_VALUE || case1Second == Integer.MAX_VALUE || case1Third == Integer.MAX_VALUE){
            case1 = -1;
        }
        else {
            case1 = case1First + case1Second + case1Third;
        }

        int case2 = 0;
        int case2First = search(1,mustNode2);
        int case2Second = search(mustNode2,mustNode1);
        int case2Third = search(mustNode1,graph.length-1);

        if(case2First == Integer.MAX_VALUE || case2Second == Integer.MAX_VALUE || case2Third == Integer.MAX_VALUE){
            case2 = -1;
        }
        else {
            case2 = case2First + case2Second + case2Third;
        }

        if(case1!=-1 && case2!=-1){
            totalCount = Math.min(case1,case2);
        }
        else if(case1!=-1 || case2!=-1){
            totalCount = Math.max(case1,case2);
        }
        else {
            totalCount = -1;
        }
        System.out.println(totalCount);
    }
    static int search(int startN, int lastN){
        int[] visited = new int[graph.length];
        Arrays.fill(visited,Integer.MAX_VALUE);
        visited[startN] = 0;
        PriorityQueue<EachNode> que = new PriorityQueue<>();
        EachNode start = new EachNode();
        start.currentNode = startN;
        start.distanceSum = 0;
        que.add(start);


        while (!que.isEmpty()){
            EachNode temp = que.poll();
            if(graph[temp.currentNode]!=null){
                for (int i = 0; i < graph[temp.currentNode].size(); i++) {
                    EachNode next = new EachNode();
                    next.currentNode = graph[temp.currentNode].get(i)[0];
                    next.distanceSum = graph[temp.currentNode].get(i)[1]+temp.distanceSum;
                    if(visited[next.currentNode]>next.distanceSum){
                        visited[next.currentNode] = next.distanceSum;
                        que.add(next);
                    }
                }
            }
        }

        return visited[lastN];
    }
    static class EachNode implements Comparable{
        int currentNode;
        int distanceSum;

        @Override
        public int compareTo(Object o) {
            EachNode node = (EachNode) o;
            return this.distanceSum - node.distanceSum;
        }

        @Override
        public String toString() {
            return "EachNode{" +
                    "currentNode=" + currentNode +
                    ", distanceSum=" + distanceSum +
                    '}';
        }
    }
}

import java.io.*;
import java.util.*;

public class Main {
    static int[] unionFind;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int nodes = Integer.parseInt(br.readLine());
        int edges = Integer.parseInt(br.readLine());

        unionFind = new int[nodes+1];
        for (int i = 0; i < unionFind.length; i++) {
            unionFind[i] = i;
        }

        Comparator<int[]> comparator = new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2]-o2[2];
            }
        };

        PriorityQueue<int[]> queue = new PriorityQueue<>(comparator);
        for (int i = 0; i < edges; i++) {
            int[] oneEdge = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            queue.add(oneEdge);
        }

        int edgeSum = 0;
        while (!queue.isEmpty()){
            int[] temp = queue.poll();
            if(union(temp[0],temp[1])){
                edgeSum+=temp[2];
            }
        }
        System.out.println(edgeSum);
    }
    static boolean union(int node1, int node2){
        int node1Parent = findParent(node1);
        int node2Parent = findParent(node2);

        if(node1Parent==node2Parent){
            return false;
        }

        if(node1Parent<node2Parent){
            unionFind[node2Parent] = node1Parent;
        }
        else {
            unionFind[node1Parent] = node2Parent;
        }
        return true;
    }
    static int findParent(int node){
        if(unionFind[node]==node){
            return unionFind[node];
        }
        return findParent(unionFind[node]);
    }
}

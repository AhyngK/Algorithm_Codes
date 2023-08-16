import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] distance;
    static int count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int nodes = Integer.parseInt(st.nextToken());
        int edges = Integer.parseInt(st.nextToken());
        int startNode = Integer.parseInt(br.readLine());

        distance = new int[nodes+1][2];
        for (int i = 0; i < distance.length; i++) {
            distance[i][0] = Integer.MAX_VALUE;
        }
        distance[startNode][0] = 0;


        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= nodes; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < edges; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int[] edge = {Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())};
            graph.get(start).add(edge);
        }

        BFSSearch(startNode,graph);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < distance.length; i++) {
            if(distance[i][0]==Integer.MAX_VALUE){
                sb.append("INF").append("\n");
                continue;
            }
            sb.append(distance[i][0]).append("\n");
        }
        System.out.println(sb);
    }

    static void BFSSearch(int startNode, List<List<int[]>> graph){

        Comparator<int[]> compare = new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1]-o2[1];
            }
        };

        PriorityQueue<int[]> queue = new PriorityQueue<>(compare);
        queue.add(new int[] {startNode,0});


        while (!queue.isEmpty()){
            if(count == distance.length-2){
                break;
            }

            int[] currentNode = queue.poll();

            // 뺀 노드가 더 큰 값을 가진 경우
            if(distance[currentNode[0]][0]<currentNode[1]){
                continue;
            }
            if(distance[currentNode[0]][1]==1){
                continue;
            }

            distance[currentNode[0]][1] = 1;
            count++;
            for (int i = 0; i < graph.get(currentNode[0]).size(); i++) {
                if(distance[graph.get(currentNode[0]).get(i)[0]][1]==1){
                    graph.get(currentNode[0]).remove(i);
                    i--;
                    continue;
                }
                if(distance[graph.get(currentNode[0]).get(i)[0]][0] > distance[currentNode[0]][0]+graph.get(currentNode[0]).get(i)[1]){
                    distance[graph.get(currentNode[0]).get(i)[0]][0] = distance[currentNode[0]][0]+graph.get(currentNode[0]).get(i)[1];
                    int[] nextNode = {graph.get(currentNode[0]).get(i)[0],distance[graph.get(currentNode[0]).get(i)[0]][0]};
                    queue.add(nextNode);
                }
            }
        }
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int nodes = Integer.parseInt(br.readLine());
        int[][] graph = new int[nodes][nodes];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int[][] answer = new int[nodes][nodes];
        for (int i = 0; i < answer.length; i++) {
            BFS(i,graph,answer);
        }

        for (int i = 0; i < answer.length ; i++) {
            for (int j = 0; j < answer.length; j++) {
                sb.append(answer[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
    static void BFS(int x, int[][] graph, int[][] answer){
        Queue<Integer> que = new LinkedList<>();
        boolean[] check = new boolean[graph.length];
        que.add(x);
        while (!que.isEmpty()){
            int temp = que.poll();
            for (int j = 0; j < graph.length; j++) {
                if(graph[temp][j]==1 && check[j]!=true){
                    answer[x][j] = 1;
                    check[j] = true;
                    que.add(j);
                }
            }
        }
    }
}

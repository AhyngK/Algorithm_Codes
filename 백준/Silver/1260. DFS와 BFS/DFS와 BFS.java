import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static StringBuilder sbForDFS = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int nodes = Integer.parseInt(st.nextToken());
        int Edges = Integer.parseInt(st.nextToken());
        int startNode = Integer.parseInt(st.nextToken());

        List<List<Integer>> Nodes = new ArrayList<>();
        for (int w = 0; w <= nodes; w++) {
            List<Integer> oneNode = new ArrayList<>();
            Nodes.add(oneNode);
        }
        for (int i = 0; i < Edges; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            Nodes.get(a).add(b);
            Nodes.get(b).add(a);
        }
        for (int i = 0; i < Nodes.size(); i++) {
            Collections.sort(Nodes.get(i));
        }

        boolean[] check = new boolean[Nodes.size()];
        Arrays.fill(check,false);
        check[startNode] = true;
        check[0] = true;
        DFS(Nodes,startNode,check);
        System.out.println(sbForDFS);
        System.out.println(BFS(Nodes,startNode));
    }
    static String BFS(List<List<Integer>> Nodes, int startNode){
        Queue<Integer> que = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        boolean[] check = new boolean[Nodes.size()];
        Arrays.fill(check,false);

        que.add(startNode);
        check[startNode] = true;
        check[0] = true;
        sb.append(startNode).append(" ");

        while (!que.isEmpty()){
            int thisIndex = que.poll();
            for (int i = 0; i < Nodes.get(thisIndex).size(); i++) {
                if(!check[Nodes.get(thisIndex).get(i)]){
                    que.add(Nodes.get(thisIndex).get(i));
                    check[Nodes.get(thisIndex).get(i)] = true;
                    sb.append(Nodes.get(thisIndex).get(i)).append(" ");
                }
            }
        }
        sb.append("\n");
        return sb.toString();
    }
    static void DFS(List<List<Integer>> Nodes, int startNode, boolean[] check){
        sbForDFS.append(startNode).append(" ");
        for (int i = 0; i < Nodes.get(startNode).size(); i++) {
            if(!check[Nodes.get(startNode).get(i)]){
                check[Nodes.get(startNode).get(i)] = true;
                DFS(Nodes,Nodes.get(startNode).get(i),check);
            }
        }
    }
}

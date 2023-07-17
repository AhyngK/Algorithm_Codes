import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nodes = sc.nextInt();
        int edges = sc.nextInt();
        int firstNode = sc.nextInt();
        sc.nextLine();

        // 배열 생성
        int[][] graph = new int[nodes+1][nodes+1];
        for (int i = 0; i < graph.length; i++) {
            Arrays.fill(graph[i],0);
        }
        // 리스트 생성
        List<List<Integer>> graphList = new ArrayList<>();
        for (int i = 0; i < nodes+1; i++) {
            List<Integer> temp = new ArrayList<>();
            graphList.add(temp);
        }
        // 입력 그래프 배열, 리스트에 저장
        for (int i = 0; i < edges; i++) {
            int node1 = sc.nextInt();
            int node2 = sc.nextInt();
            sc.nextLine();

            // 배열에 저장
            graph[node1][node2] = 1;
            graph[node2][node1] = 1;

            // 리스트에 저장
            graphList.get(node1).add(node2);
            graphList.get(node2).add(node1);
        }

        // 방문 여부 체크할 배열
        boolean[] check = new boolean[nodes+1];

        // DFS 탐색 호출
        Arrays.fill(check,false);
        DFSRecursionArray(graph,firstNode,check);
        System.out.println();

//        Arrays.fill(check,false);
//        DFSRecursionList(graphList,firstNode,check);
//        System.out.println();

        // BFS 탐색 호출
        BFSArray(graph,firstNode);
//        System.out.println();
//        BFSList(graphList,firstNode);

        // 테스트 출력
//        for (int i = 0; i < graph.length; i++) {
//            System.out.println(Arrays.toString(graph[i]));
//        }
//        System.out.println();
//        for (int i = 0; i < graphList.size(); i++) {
//            System.out.println(graphList.get(i));
//        }
    }

    // DFS
    static void DFSRecursionArray(int[][] graph, int start, boolean[] check){
        check[start] = true;
        System.out.print(start+" ");

        for (int i = 0; i < graph[start].length; i++) {
            if(graph[start][i]==1 && check[i]==false){
                DFSRecursionArray(graph,i,check);
            }
        }
    }
    static void DFSRecursionList(List<List<Integer>> graphList, int start, boolean[] check){
        check[start] = true;
        System.out.print(start+" ");
        // list에는 연결된 노드가 바로 들어있기 때문에 그 부분을 유의
        for (int i = 0; i < graphList.get(start).size(); i++) {
            if(check[graphList.get(start).get(i)] == false){
                DFSRecursionList(graphList,graphList.get(start).get(i),check);
            }
        }
    }

    static void BFSArray(int[][] graph, int start){
        Queue<Integer> que = new LinkedList<>();
        que.add(start);

        boolean[] check = new boolean[graph.length];
        Arrays.fill(check,false);
        check[start] = true;

        while (!que.isEmpty()){
            int first = que.poll();
            System.out.print(first+" ");
            for (int i = 0; i < graph[first].length; i++) {
                if(graph[first][i]==1 && check[i]==false){
                    check[i] = true;
                    que.add(i);
                }
            }
        }
    }
    static void BFSList(List<List<Integer>> graphList, int start){
        Queue<Integer> que = new LinkedList<>();
        que.add(start);

        boolean[] check = new boolean[graphList.size()];
        Arrays.fill(check,false);
        check[start] = true;

        while (!que.isEmpty()){
            int current = que.poll();
            System.out.print(current+" ");

            for (int i = 0; i < graphList.get(current).size(); i++) {
                if(check[graphList.get(current).get(i)] == false){
                    check[graphList.get(current).get(i)] = true;
                    que.add(graphList.get(current).get(i));
                }
            }
        }
    }
}

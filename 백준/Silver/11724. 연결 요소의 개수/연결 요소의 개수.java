import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nodes = sc.nextInt();
        int edges = sc.nextInt();
        sc.nextLine();

        List<List<Integer>> graphList = new ArrayList<>();
        for (int i=0; i<nodes+1; i++) {
            List<Integer> a = new ArrayList<>();
            graphList.add(a);
        }

        for (int i = 0; i < edges; i++) {
            int node1 = sc.nextInt();
            int node2 = sc.nextInt();
            sc.nextLine();
            graphList.get(node1).add(node2);
            graphList.get(node2).add(node1);
        }

        boolean[] check = new boolean[nodes+1];
        Arrays.fill(check,false);
        check[0] = true;
        check[1] = true;

        // BFS할 큐
        Queue<Integer> que = new LinkedList<>();
        que.add(1);

        // 0에서 시작해 BFS 한번 진행 후 방문하지 않은 노드가 있다면 그 노드를 시작점으로 다시 탐색
        int count = 0; // 개수 셀 변수
        boolean allRound = false; // 모든 노드를 다 봤는지 확인할 변수

        while(true){
            count++;
            // BFS 1회 진행 (한 노드에서 시작한 탐색)
            while (!que.isEmpty()){
                int temp = que.poll();

                for (int i = 0; i < graphList.get(temp).size(); i++) {
                    if(!check[graphList.get(temp).get(i)]){
                        que.add(graphList.get(temp).get(i));
                        check[graphList.get(temp).get(i)]= true;
                    }
                }
            }

            for(int i=1; i<check.length; i++){
                if(!check[i]){
                    que.add(i);
                    check[i] = true;
                    break;
                }
                else if(i== check.length-1 && check[i]){
                    allRound = true;
                }
            }

            // 전체 반복문 중단, 추후 라벨로 변경 가능할 것 같음
            if(allRound){
                break;
            }
        }

        System.out.println(count);


    }
}

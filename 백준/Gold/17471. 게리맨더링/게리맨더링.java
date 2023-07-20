import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int min = 1000000;
    public static void main(String[] args) throws IOException {

        // 먼저 가능한 가짓수를 보고, 그래프가 연결되어있는지 확인?
        // 가짓수 찾기 -> 2^n 개
        // 인접 행렬로 해두면, 각 지점에서 연결된게 맞는지 보는건 복잡도 1?

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int lands = Integer.parseInt(br.readLine());
        int[] landPeople = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        // 그래프 인접행렬 생성, 0으로 초기화 후 연결된 노드는 1로 변경
        int[][] landGraph = new int[lands+1][lands+1];
        for (int i = 0; i < landGraph.length; i++) {
            Arrays.fill(landGraph[i],0);
        }
        for (int i = 1; i <landGraph.length; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int temp = Integer.parseInt(st.nextToken());
            while (st.hasMoreTokens()){
                landGraph[i][Integer.parseInt(st.nextToken())] = 1;
            }
        }
        List<Integer> group1 = new ArrayList<>();
        List<Integer> group2 = new ArrayList<>();
        recursion(1, landGraph,landPeople,group1,group2);
        if(min!=1000000){
            System.out.println(min);
        }
        else {
            System.out.println(-1);
        }
    }
    // 두 그룹으로 지역을 나누는 것
    static void recursion(int index, int[][]landGraph, int[] landPeople, List<Integer> group1, List<Integer> group2){
        if(index==landGraph.length){
//            System.out.println("-----one Cycle-----");
//            System.out.println(group1);
//            System.out.println(group2);

            if(group1.isEmpty() || group2.isEmpty()){
                return;
            }
            else {
                if(checkConnection(group1,landGraph) && checkConnection(group2,landGraph)){
                    int temp = Math.abs(peopleSum(group1,landPeople)-peopleSum(group2,landPeople));
                    if(temp<min){
                        min = temp;
                    }
                }
                return;
            }
        }

        // group 1
        group1.add(index);
        recursion(index+1,landGraph,landPeople,group1,group2);
        group1.remove(new Integer(index));

        // group2
        group2.add(index);
        recursion(index+1,landGraph,landPeople,group1,group2);
        group2.remove(new Integer(index));
    }
    // BFS로 지역 연결확인
    static boolean checkConnection(List<Integer> group, int[][]landGraph) {
        Queue<Integer> que = new LinkedList<>();
        que.add(group.get(0));

        boolean[] check = new boolean[group.size()];
        Arrays.fill(check,false);
        check[0] = true;

        while (!que.isEmpty()){
            int temp = que.poll();
            for (int i = 1; i < group.size(); i++) {
                if(landGraph[temp][group.get(i)]==1 && !check[i]){
                    que.add(group.get(i));
                    check[i] = true;
                }
            }
        }

        boolean answer = true;
        for (int i = 0; i <check.length ; i++) {
            if(!check[i]){
                answer = false;
            }
        }
        return answer;
    }

    static int peopleSum(List<Integer> group, int[] landPeople){
        int sum = 0;
        for (int i = 0; i < group.size(); i++) {
            sum+=landPeople[group.get(i)-1];
        }
        return sum;
    }
}

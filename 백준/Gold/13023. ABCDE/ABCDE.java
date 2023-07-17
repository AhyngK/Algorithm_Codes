import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    static int biggest = 0;
    public static void main(String[] args) {
        // 입력 받기
        Scanner sc =  new Scanner(System.in);
        int peopleNum = sc.nextInt();
        int relationNum = sc.nextInt();
        sc.nextLine();

        // 그래프 를 저장할 리스트 선언(리스트를 원소로 가지는 리스트), 초기화
        List<List<Integer>> graph = new ArrayList<List<Integer>>();
        for (int i = 0; i < peopleNum ; i++) {
            List<Integer> temp = new ArrayList<>();
            graph.add(temp);
        }
        // 사람 번호를 인덱스로 어떤 사람과 연결되어 있는지를 리스트 안에 담기
        for (int i = 0; i < relationNum; i++) {
            int temp1 = sc.nextInt();
            int temp2 = sc.nextInt();
            sc.nextLine();
            graph.get(temp1).add(temp2);
            graph.get(temp2).add(temp1);
        }

        for (int i = 0; i < graph.size(); i++) {
            boolean[] check = new boolean[graph.size()];
            Arrays.fill(check,false);
            check[i] = true;
            DFSRecursion(graph,i,check,1);
            if(biggest>=5){
                break;
            }
        }

        if(biggest>=5){
            System.out.println(1);
        }
        else {
            System.out.println(0);
        }
    }
    static void DFSRecursion(List<List<Integer>> graph, int start, boolean[] check, int sum){
        if(biggest<sum){
            biggest=sum;
        }
        if(sum>=5){
            return;
        }

//        System.out.print(start+" ");
        for (int a: graph.get(start)) {
            if(check[a]==false){
                check[a]=true;
                DFSRecursion(graph,a,check,sum+1);
                check[a]=false;
            }
        }
    }
}

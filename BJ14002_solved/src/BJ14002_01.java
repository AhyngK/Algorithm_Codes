import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class BJ14002_01 {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        int ASize = sc.nextInt();
        sc.nextLine();
        int[] A = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int i = 0; i <A.length; i++) {
            ArrayList<Integer> temp = new ArrayList<>();
            if(i==0){
                temp.add(A[i]);
                list.add(temp);
                continue;
            }

            int biggest =-1;
            int index1 =-1;
            for (int j = 0; j <i; j++) {
                if(A[j]<A[i]){
                    if(list.get(j).size()>biggest){
                        biggest = list.get(j).size();
                        index1 = j;
                    }
                }
            }

            if(index1==-1){
                temp.add(A[i]);
                list.add(temp);
            }
            else {
                temp.addAll(list.get(index1));
                temp.add(A[i]);
                list.add(temp);
            }
        }

        int index =0;
        for (int i = 0; i < list.size(); i++) {
            if(list.get(index).size()<list.get(i).size()){
                index = i;
            }
        }

        System.out.println(list.get(index).size());
        for (int i = 0; i < list.get(index).size(); i++) {
            System.out.print(list.get(index).get(i)+" ");
        }
    }
}

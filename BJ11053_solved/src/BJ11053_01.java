import java.util.*;

public class BJ11053_01 {
    public static void main(String[] args) {
        ArrayList<Integer> Ais = new ArrayList<>();
        ArrayList<Integer> counts = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        int ASize = sc.nextInt();
        sc.nextLine();
        int[] A = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int i = 0; i <A.length; i++) {
            if(Ais.size()==0){
                Ais.add(A[i]);
                counts.add(1);
                continue;
            }

            int biggest =-1;
            for (int j = 0; j < Ais.size(); j++) {
                if(Ais.get(j)<A[i] ){
                    if(counts.get(j)>biggest){
                        biggest = counts.get(j);
                    }
                }
            }

            if(biggest==-1){
                Ais.add(A[i]);
                counts.add(1);
            }
            else {
                Ais.add(A[i]);
                counts.add(biggest+1);
            }
        }
        Collections.sort(counts);
        System.out.println(counts.get(counts.size()-1));
    }
}

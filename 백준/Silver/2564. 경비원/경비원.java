import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int columns = Integer.parseInt(st.nextToken());
        int rows = Integer.parseInt(st.nextToken());
        int storeNum = Integer.parseInt(br.readLine());
        int startNum = 0;
        int length = rows*2+columns*2;

        List<Integer> stores = new ArrayList<>();

        for (int i = 1; i <= storeNum+1; i++) {
            st = new StringTokenizer(br.readLine());
            int dir = Integer.parseInt(st.nextToken());
            int cor = Integer.parseInt(st.nextToken());
            int cal = 0;

            if(dir == 1){
                cal = cor;
            }
            else if(dir == 2){
                cal = columns*2+rows-cor;
            }
            else if(dir == 3){
                cal = columns*2+rows*2-cor;
            }
            else {
                cal = columns+cor;
            }

            if(i==storeNum+1){
                startNum = cal;
            }
            else {
                stores.add(cal);
            }
        }
        int sum = 0;
        for (int i = 0; i < stores.size(); i++) {
            int small = Math.min(stores.get(i),startNum);
            int big = Math.max(stores.get(i),startNum);

            int distance1 = big-small;
            int distance2 = small+(length-big);
            sum+=Math.min(distance1,distance2);
        }
        System.out.println(sum);
    }
}

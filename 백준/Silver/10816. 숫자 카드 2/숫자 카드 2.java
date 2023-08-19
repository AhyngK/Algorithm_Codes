import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<Long, Long> cards = new HashMap<>();
        StringBuilder sb = new StringBuilder();

        long cardNum = Long.parseLong(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            long current = Long.parseLong(st.nextToken());
            if (cards.containsKey(current)) {
                cards.put(current, cards.get(current) + 1);
                continue;
            }
            cards.put(current, (long)1);
        }

        long findCardNum = Long.parseLong(br.readLine());
        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()){
            long toFind = Long.parseLong(st.nextToken());
            if (cards.containsKey(toFind)) {
                sb.append(cards.get(toFind)).append(" ");
                continue;
            }
            sb.append(0).append(" ");
        }
        System.out.println(sb);
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] distance;
    static int[] count;
    static int start;
    static int last;

    public static void main(String[] args) throws IOException {
        distance = new int[100001];
        count = new int[distance.length];
        Arrays.fill(distance,Integer.MAX_VALUE);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        last = Integer.parseInt(st.nextToken());

        search();
        System.out.println(distance[last]);
        System.out.println(count[last]);
    }
    static void search(){
        Queue<Integer> que = new LinkedList<>();
        que.add(start);
        distance[start] = 0;
        count[start] = 1;

        while (!que.isEmpty()){
            int current = que.poll();
            int[] next = {current*2, current+1, current-1};

            for (int i = 0; i < next.length; i++) {
                if (next[i]>=0 && next[i]<distance.length && distance[next[i]]>=distance[current]+1){
                    if(distance[next[i]] == distance[current]+1){
                        count[next[i]]++;
                        que.add(next[i]);
                    }
                    else {
                        distance[next[i]] = distance[current]+1;
                        count[next[i]] = 1;
                        que.add(next[i]);
                    }
                }
            }
        }
    }
}

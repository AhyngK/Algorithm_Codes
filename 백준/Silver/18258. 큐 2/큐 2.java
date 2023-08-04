import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    static String[] words = {"push","pop","size","empty","front","back"};
    static int pushed = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int totalNum = Integer.parseInt(br.readLine());

        // Que for Function
        Queue<Integer> que = new LinkedList<>();
        StringBuilder sb = new StringBuilder();

        for (int tc = 0; tc < totalNum; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String currentWord = st.nextToken();
            int currentWordIndex = 0;
            int currentNum = 0;
            // finding which word
            for (int i = 0; i < words.length; i++) {
                if(currentWord.equals(words[i])){
                    currentWordIndex = i;
                    break;
                }
            }
            // it word is"push", get additional number
            if(currentWordIndex==0){
                currentNum = Integer.parseInt(st.nextToken());
                pushed = currentNum;
            }

            switch (currentWordIndex){
                case 0 : push(que,currentNum);
                break;
                case 1 : sb.append(pop(que)).append("\n");
                break;
                case 2 : sb.append(size(que)).append("\n");
                break;
                case 3 : sb.append(empty(que)).append("\n");
                break;
                case 4 : sb.append(front(que)).append("\n");
                break;
                case 5 : sb.append(back(que)).append("\n");
                break;
            }
        }
        System.out.println(sb);

    }
    static void push(Queue<Integer> que, int num){
        que.add(num);
    }
    static int pop(Queue<Integer> que){
        return empty(que)==1 ? -1 : que.poll();
    }
    static int size(Queue<Integer> que){
        return que.size();
    }
    static int empty(Queue<Integer> que){
        return size(que) == 0 ? 1:0;
    }
    static int front(Queue<Integer> que){
        return empty(que)==1 ? -1 : que.peek();
    }
    static int back(Queue<Integer> que){
        if(size(que)==0 || pushed==-1){
            return -1;
        }
        return pushed;
    }
}

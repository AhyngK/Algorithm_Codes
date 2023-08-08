import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    // Direction : 0(up), 1(right), 2(down), 3(left)
    static int direction = 1;
    static int[][] directionArr = {{-1,0},{0,1},{1,0},{0,-1}};
    static int boardSize = 0;
    static int time = 0;
    static boolean ended = false;
    public static void main(String[] args) throws IOException {
        // Read Board Size and reset Time
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boardSize = Integer.parseInt(br.readLine());
        time = 0;
        // First Snake
        List<int[]> snake = new ArrayList<>();
        snake.add(new int[] {0,0});
        // Include the Apples
        int appleNum = Integer.parseInt(br.readLine());
        List<int[]> apples = new ArrayList<>();
        for (int i = 0; i < appleNum; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] apple = new int[2];
            apple[0] = Integer.parseInt(st.nextToken())-1;
            apple[1] = Integer.parseInt(st.nextToken())-1;
            apples.add(apple);
        }

        // Each Movement Function
        int movementNum = Integer.parseInt(br.readLine());
        for (int i = 0; i < movementNum; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(st.nextToken());
            String turn = st.nextToken();
            moveOnce(snake,apples,count,turn);
            if(ended){
                break;
            }
        }
        int countTime = time+1;
        while (true){
            if(ended){
                break;
            }
            moveOnce(snake,apples,countTime,"F");
            countTime+=1;
        }
        System.out.println(time);
    }
    static void moveOnce(List<int[]> snake, List<int[]> apples, int count, String turn){
        // Move in same Direction for given time
        int currentTime = time;
        for (int i = 0; i < count-currentTime; i++) {
            time+=1; // Time Passes
            // Figure Out where Next Head is
            int[] nextHead = new int[2];
            nextHead[0] = snake.get(0)[0]+directionArr[direction][0];
            nextHead[1] = snake.get(0)[1]+directionArr[direction][1];
            // Did the game end?
            if(hasEnded(nextHead,snake)){
                ended = true;
                return;
            }
            snake.add(0,nextHead);
            // Snake Moves, and if didn't met apple, tail dies
            int appleIndex = isApple(nextHead,apples);
            if(appleIndex!=-1){
                apples.remove(appleIndex);
            }
            else {
                snake.remove(snake.size()-1);
            }
        }
        turn(turn);
    }
    static boolean hasEnded(int[] nextHead, List<int[]> snake){
        if( nextHead[0]==-1 || nextHead[0]==boardSize || nextHead[1]==-1 || nextHead[1]==boardSize){
            return true;
        }
        for (int i = 0; i < snake.size(); i++) {
            if(snake.get(i)[0]==nextHead[0] && snake.get(i)[1]==nextHead[1]){
                return true;
            }
        }
        return false;
    }
    static int isApple(int[] nextHead, List<int[]> apples){
        int index = -1;
        for (int i = 0; i < apples.size(); i++) {
            if(apples.get(i)[0]==nextHead[0] && apples.get(i)[1]==nextHead[1]){
                index = i;
            }
        }
        return index;
    }
    static void turn(String turn){
        if(turn.equals("D")){
            direction = direction+1>3 ? 0 : direction+1;
        }
        else if(turn.equals("L")){
            direction = direction-1<0 ? 3 : direction-1;
        }
    }
}

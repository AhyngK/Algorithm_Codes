import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] eachPlayer;
    static int innings = 0;
    static int maxScore = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        innings = Integer.parseInt(br.readLine());
        eachPlayer = new int[innings][9];
        for (int i = 0; i < eachPlayer.length; i++) {
            eachPlayer[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int[] playerSequence = new int[9];
        Arrays.fill(playerSequence,-1);
        playerSequence[3] = 0;
        boolean[] selected = new boolean[9];
        selected[0] = true;
        selection(0,playerSequence,selected);

        System.out.println(maxScore);
    }
    static void selection(int index, int[] playerSequence, boolean[] selected){
        if(index == playerSequence.length){
            Queue<Integer> playSequence = new LinkedList<>();
            for (int i = 0; i < playerSequence.length; i++) {
                playSequence.add(playerSequence[i]);
            }
            play(playSequence);
            return;
        }

        if(index == 3){
            selection(index+1,playerSequence,selected);
        }
        else {
            for (int i = 1; i < playerSequence.length; i++) {
                if(!selected[i]){
                    playerSequence[index] = i;
                    selected[i] = true;
                    selection(index+1,playerSequence,selected);
                    selected[i] = false;
                }
            }

        }
    }
    static void play(Queue<Integer> playSequence){
        int score = 0;
        for (int i = 0; i < innings; i++) {
            score += eachInning(i,playSequence);
        }
        maxScore = Math.max(score,maxScore);
    }
    static int eachInning(int inning, Queue<Integer> playSequence){
        int currentScore = 0;
        int out = 0;
        int first = 0;
        int second = 0;
        int third = 0;

        label : while (true){
            int currentPlayer = playSequence.poll();
            playSequence.add(currentPlayer);

            switch (eachPlayer[inning][currentPlayer]){
                case 0 :
                    out+=1;
                    if(out == 3){
                        break label;
                    }
                    break;
                case 1 :
                    currentScore += third;
                    third = second;
                    second = first;
                    first = 1;
                    break;
                case 2 :
                    currentScore += third+second;
                    third = first;
                    second = 1;
                    first = 0;
                    break;
                case 3 :
                    currentScore += first+second+third;
                    third = 1;
                    second = 0;
                    first = 0;
                    break;
                case 4 :
                    currentScore += first+second+third+1;
                    first = 0;
                    second = 0;
                    third = 0;
                    break;
            }
        }
        return currentScore;
    }
}
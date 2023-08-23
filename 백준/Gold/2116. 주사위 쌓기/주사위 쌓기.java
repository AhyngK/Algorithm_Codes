import java.io.*;
import java.util.*;

public class Main {
    static Map<Integer, Integer> indexes = new HashMap<>();
    static int[][] dices;
    static int totalSum = Integer.MIN_VALUE;
    static int diceNum;

    public static void main(String[] args) throws IOException {
        indexes.put(0,5);
        indexes.put(1,3);
        indexes.put(2,4);
        indexes.put(3,1);
        indexes.put(4,2);
        indexes.put(5,0);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        diceNum = Integer.parseInt(br.readLine());
        dices = new int[diceNum][6];
        for (int i = 0; i < dices.length; i++) {
            dices[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        for (int i = 0; i < dices[0].length; i++) {
            eachDice(i);
        }
        System.out.println(totalSum);
    }
    static void eachDice(int index){
        int dice = 0;
        int currentIndex = index;
        int sum = 0;

        while (true){
            if(dice==dices.length){
                break;
            }
            sum+=sideSum(dice,currentIndex);

            if(dice!=dices.length-1){
                int nextFloorNum = dices[dice][indexes.get(currentIndex)];
                int nextFloorIndex = nextDiceFloorIndex(dice+1,nextFloorNum);
                currentIndex = nextFloorIndex;
            }
            dice+=1;
        }
        totalSum = Math.max(sum,totalSum);
    }
    static int nextDiceFloorIndex(int dice, int floorNum){
        for (int i = 0; i < dices[dice].length; i++) {
            if(dices[dice][i]==floorNum){
                return i;
            }
        }
        return 0;
    }
    static int sideSum(int dice, int floorIndex){
        List<Integer> temp = new ArrayList<>();
        for (int i = 0; i < dices[0].length; i++) {
            if(i==floorIndex || i==indexes.get(floorIndex)){
                continue;
            }
            temp.add(dices[dice][i]);
        }
        Collections.sort(temp);
        return temp.get(temp.size()-1);
    }
}

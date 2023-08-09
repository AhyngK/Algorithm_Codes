import java.util.*;
import java.io.*;

public class Main {
    static Map<String,String> bracketPairs = new HashMap<>();
    static Map<String,Integer> bracketNums = new HashMap<>();

    public static void main(String[] args) throws IOException {
        // Put bracket pairs into Map
        bracketPairs.put(")","(");
        bracketPairs.put("]","[");
        bracketNums.put(")",2);
        bracketNums.put("]",3);

        // Get input String and split with blank, get it as array
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String[] brackets = input.split("");
        // A Stack for closing brackets, A Stack for number sum, List for total addition
        Stack<Integer> bracketStack = new Stack<>();
        Stack<int[]> sumStack = new Stack<>();

        boolean check = true;
        int answer = 0;

        for (int i = 0; i < brackets.length; i++) {
            // ths Bracket Stack is empty
            if(bracketStack.isEmpty()){
                // Bracket Stack is empty, but current bracket is closing
                if(bracketPairs.containsKey(brackets[i])){
                    check = false;
                    break;
                }
                // One Big Bracket has close, so pop all sums
                while (!sumStack.isEmpty()){
                 answer+=sumStack.pop()[1];
                }
                // If the Bracket is opening, put it in Stack
                bracketStack.push(i);
                continue;
            }

            // the Bracket Stack is Not Empty
            // if Bracket is opening
            if(bracketPairs.containsValue(brackets[i])){
                bracketStack.push(i);
            }
            // if Bracket is closing
            else {
                // if right before Bracket is the Pair
                if(bracketPairs.get(brackets[i]).equals(brackets[i-1])){
                    bracketStack.pop();
                    sumStack.push(new int[] {i-1, bracketNums.get(brackets[i])});
                }
                // needs to close the Other Bracket
                else{
                    // needs to close, but the other bracket is not pair
                    if(!bracketPairs.get(brackets[i]).equals(brackets[bracketStack.peek()])){
                        check = false;
                        break;
                    }
                    // Closing Normally
                    else {
                        int temp = 0;
                        while (!sumStack.isEmpty() && sumStack.peek()[0]>bracketStack.peek()){
                            temp+=sumStack.pop()[1];
                        }
                        temp*=bracketNums.get(brackets[i]);
                        sumStack.push(new int[] {bracketStack.pop(),temp});
                    }
                }
            }
        }

        while (!sumStack.isEmpty()){
            answer+=sumStack.pop()[1];
        }
        if(check && bracketStack.isEmpty()){
            System.out.println(answer);
        }
        else {
            System.out.println(0);
        }
    }
}
